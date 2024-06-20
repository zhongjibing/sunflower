package com.icezhg.sunflower.server;


import com.icezhg.sunflower.server.entity.Cpu;
import com.icezhg.sunflower.server.entity.FileSys;
import com.icezhg.sunflower.server.entity.Jvm;
import com.icezhg.sunflower.server.entity.Mem;
import com.icezhg.sunflower.server.entity.Sys;
import com.icezhg.sunflower.util.Arith;
import com.icezhg.sunflower.util.IpUtil;
import lombok.Getter;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.CentralProcessor.TickType;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.FileSystem;
import oshi.software.os.OSFileStore;
import oshi.software.os.OperatingSystem;
import oshi.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.function.Supplier;

/**
 * 服务器相关信息
 */
@Getter
public class Server {
    private static final long OSHI_WAIT_MILLIS = 1000L;

    public static final String CPU = "cpu";
    public static final String MEM = "mem";
    public static final String JVM = "jvm";
    public static final String SYS = "sys";
    public static final String FILESYS = "fileSys";
    public static final String ALL = "all";

    private static final Map<String, Supplier<?>> SUPPLIERS = Map.of(
            CPU, Server::getCpuInfo,
            MEM, Server::getMemInfo,
            JVM, Server::getJvmInfo,
            SYS, Server::getSysInfo,
            FILESYS, Server::getFileSysInfo,
            ALL, () -> Map.of(
                    CPU, getCpuInfo(),
                    MEM, getMemInfo(),
                    JVM, getJvmInfo(),
                    SYS, getSysInfo(),
                    FILESYS, getFileSysInfo()
            )
    );

    public static Object getInfo(String name) {
        return SUPPLIERS.getOrDefault(name, SUPPLIERS.get(ALL)).get();
    }


    public static Cpu getCpuInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        CentralProcessor processor = hal.getProcessor();

        Cpu cpu = new Cpu();
        long[] prevTicks = processor.getSystemCpuLoadTicks();
        Util.sleep(OSHI_WAIT_MILLIS);
        long[] ticks = processor.getSystemCpuLoadTicks();
        long nice = ticks[TickType.NICE.getIndex()] - prevTicks[TickType.NICE.getIndex()];
        long irq = ticks[TickType.IRQ.getIndex()] - prevTicks[TickType.IRQ.getIndex()];
        long softirq = ticks[TickType.SOFTIRQ.getIndex()] - prevTicks[TickType.SOFTIRQ.getIndex()];
        long steal = ticks[TickType.STEAL.getIndex()] - prevTicks[TickType.STEAL.getIndex()];
        long cSys = ticks[TickType.SYSTEM.getIndex()] - prevTicks[TickType.SYSTEM.getIndex()];
        long user = ticks[TickType.USER.getIndex()] - prevTicks[TickType.USER.getIndex()];
        long iowait = ticks[TickType.IOWAIT.getIndex()] - prevTicks[TickType.IOWAIT.getIndex()];
        long idle = ticks[TickType.IDLE.getIndex()] - prevTicks[TickType.IDLE.getIndex()];
        long totalCpu = user + nice + cSys + idle + iowait + irq + softirq + steal;
        cpu.setCpuNum(processor.getLogicalProcessorCount());
        cpu.setTotal(totalCpu);
        cpu.setSys(cSys);
        cpu.setUsed(user);
        cpu.setWait(iowait);
        cpu.setFree(idle);
        return cpu;
    }

    public static Mem getMemInfo() {
        SystemInfo si = new SystemInfo();
        HardwareAbstractionLayer hal = si.getHardware();
        GlobalMemory memory = hal.getMemory();

        Mem mem = new Mem();
        mem.setTotal(memory.getTotal());
        mem.setUsed(memory.getTotal() - memory.getAvailable());
        mem.setFree(memory.getAvailable());
        return mem;
    }

    public static Jvm getJvmInfo() {
        Jvm jvm = new Jvm();
        jvm.setTotal(Runtime.getRuntime().totalMemory());
        jvm.setMax(Runtime.getRuntime().maxMemory());
        jvm.setFree(Runtime.getRuntime().freeMemory());
        Properties props = System.getProperties();
        jvm.setVersion(props.getProperty("java.version"));
        jvm.setHome(props.getProperty("java.home"));
        return jvm;
    }

    public static Sys getSysInfo() {
        Sys sys = new Sys();
        sys.setComputerName(IpUtil.getHostName());
        sys.setComputerIp(IpUtil.getHostIp());
        Properties props = System.getProperties();
        sys.setOsName(props.getProperty("os.name"));
        sys.setOsArch(props.getProperty("os.arch"));
        sys.setUserDir(props.getProperty("user.dir"));
        return sys;
    }

    public static List<FileSys> getFileSysInfo() {
        SystemInfo si = new SystemInfo();
        OperatingSystem os = si.getOperatingSystem();
        FileSystem fileSystem = os.getFileSystem();
        List<OSFileStore> fileStores = fileSystem.getFileStores();

        List<FileSys> fileSysList = new ArrayList<>();
        for (OSFileStore fs : fileStores) {
            long free = fs.getUsableSpace();
            long total = fs.getTotalSpace();
            long used = total - free;
            FileSys fileSys = new FileSys();
            fileSys.setDirName(fs.getMount());
            fileSys.setSysTypeName(fs.getType());
            fileSys.setTypeName(fs.getName());
            fileSys.setTotal(convertFileSize(total));
            fileSys.setFree(convertFileSize(free));
            fileSys.setUsed(convertFileSize(used));
            fileSys.setUsage(Arith.multiply(Arith.divide(used, total, 4), 100));
            fileSysList.add(fileSys);
        }
        return fileSysList;
    }

    private static String convertFileSize(long size) {
        long kb = 1024;
        long mb = kb * 1024;
        long gb = mb * 1024;
        if (size >= gb) {
            return String.format("%.1f GB", (float) size / gb);
        } else if (size >= mb) {
            float f = (float) size / mb;
            return String.format(f > 100 ? "%.0f MB" : "%.1f MB", f);
        } else if (size >= kb) {
            float f = (float) size / kb;
            return String.format(f > 100 ? "%.0f KB" : "%.1f KB", f);
        } else {
            return String.format("%d B", size);
        }
    }
}
