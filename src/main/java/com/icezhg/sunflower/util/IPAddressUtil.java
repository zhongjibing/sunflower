package com.icezhg.sunflower.util;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class IPAddressUtil {
    private static final Logger log = LoggerFactory.getLogger(IPAddressUtil.class);

    private static final RestTemplate restTemplate = new RestTemplate();

    private static String ipInfoUrl;

    @Value("${ip.info-url}")
    public void setIpInfoUrl(String ipInfoUrl) {
        IPAddressUtil.ipInfoUrl = ipInfoUrl;
    }

    public static String getLocation(String ip) {
        if (internalIp(ip)) {
            return "内网IP";
        }

        try {
            String response = restTemplate.getForObject(ipInfoUrl + String.format("?ip=%s", ip), String.class);
            JSONObject result = JSONObject.parseObject(response);

            if (result != null && result.getIntValue("code") == HttpStatus.OK.value()) {
                JSONObject adCode = result.getJSONObject("adcode");
                if (adCode != null) {
                    return adCode.getString("n");
                }
            }

            log.error("query ip location parse result error: {}, {}", ip, response);
        } catch (Exception e) {
            log.error("query ip location error: {}", ip);
        }

        return "UNKNOWN";
    }

    public static boolean internalIp(String ip) {
        if (StringUtils.startsWith(ip, "127")) {
            return true;
        }

        return internalIp(textToNumericFormatV4(ip));
    }

    private static boolean internalIp(byte[] addr) {
        if (addr == null || addr.length < 2) {
            return true;
        }

        final byte b0 = addr[0];
        final byte b1 = addr[1];
        // 10.x.x.x/8
        final byte SECTION_1 = 0x0A;
        // 172.16.x.x/12
        final byte SECTION_2 = (byte) 0xAC;
        final byte SECTION_3 = (byte) 0x10;
        final byte SECTION_4 = (byte) 0x1F;
        // 192.168.x.x/16
        final byte SECTION_5 = (byte) 0xC0;
        final byte SECTION_6 = (byte) 0xA8;
        switch (b0) {
            case SECTION_1:
                return true;
            case SECTION_2:
                if (b1 >= SECTION_3 && b1 <= SECTION_4) {
                    return true;
                }
            case SECTION_5:
                if (b1 == SECTION_6) {
                    return true;
                }
            default:
                return false;
        }
    }

    public static byte[] textToNumericFormatV4(String text) {
        if (StringUtils.isBlank(text)) {
            return null;
        }

        byte[] bytes = new byte[4];
        String[] elements = text.split("\\.", -1);
        try {
            long l;
            int i;
            switch (elements.length) {
                case 1 -> {
                    l = Long.parseLong(elements[0]);
                    if ((l < 0L) || (l > 4294967295L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l >> 24 & 0xFF);
                    bytes[1] = (byte) (int) ((l & 0xFFFFFF) >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                }
                case 2 -> {
                    l = Integer.parseInt(elements[0]);
                    if ((l < 0L) || (l > 255L)) {
                        return null;
                    }
                    bytes[0] = (byte) (int) (l & 0xFF);
                    l = Integer.parseInt(elements[1]);
                    if ((l < 0L) || (l > 16777215L)) {
                        return null;
                    }
                    bytes[1] = (byte) (int) (l >> 16 & 0xFF);
                    bytes[2] = (byte) (int) ((l & 0xFFFF) >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                }
                case 3 -> {
                    for (i = 0; i < 2; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                    l = Integer.parseInt(elements[2]);
                    if ((l < 0L) || (l > 65535L)) {
                        return null;
                    }
                    bytes[2] = (byte) (int) (l >> 8 & 0xFF);
                    bytes[3] = (byte) (int) (l & 0xFF);
                }
                case 4 -> {
                    for (i = 0; i < 4; ++i) {
                        l = Integer.parseInt(elements[i]);
                        if ((l < 0L) || (l > 255L)) {
                            return null;
                        }
                        bytes[i] = (byte) (int) (l & 0xFF);
                    }
                }
                default -> {
                    return null;
                }
            }
        } catch (NumberFormatException e) {
            return null;
        }
        return bytes;
    }
}
