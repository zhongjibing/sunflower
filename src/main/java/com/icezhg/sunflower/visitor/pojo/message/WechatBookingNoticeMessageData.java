package com.icezhg.sunflower.visitor.pojo.message;

import com.icezhg.sunflower.visitor.pojo.WechatMessageData;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/26.
 */
@Setter
public class WechatBookingNoticeMessageData implements WechatMessageData {

    private Date date;
    private String resourceName;
    private String contactName;
    private String status;
    private String remark = "";

    @Override
    public Map<String, Object> data() {
        Map<String, Object> result = new HashMap<>();
        result.put("date3", Map.of("value", DateFormatUtils.format(date, "yyyy年M月d日"))); // 预约时间
        result.put("thing13", Map.of("value", resourceName)); // 预约项目
        result.put("phrase14", Map.of("value", status)); // 预约状态
        result.put("thing7", Map.of("value", StringUtils.defaultIfBlank(remark, DateFormatUtils.format(date, "yyyy年M月d日") + resourceName + "预约") )); // 备注
        result.put("name1", Map.of("value", contactName)); // 预约人
        return result;
    }
}
