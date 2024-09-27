package com.icezhg.sunflower.visitor.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

/**
 * Created by zhongjibing on 2023/08/26.
 */
public class WechatMessage {
    @JsonProperty("template_id")
    private String templateId;
    @JsonProperty("touser")
    private String toUser;
    private String page = "/pages/index/index";
    @JsonProperty("miniprogram_state")
    private String miniProgramState = "formal";
    private String lang = "zh_CN";
    private WechatMessageData data = WechatMessageData.EMPTY;

    public WechatMessage() {
    }

    public WechatMessage(String templateId, String toUser, WechatMessageData data) {
        this.templateId = templateId;
        this.toUser = toUser;
        this.data = data;
    }

    public String getTemplateId() {
        return templateId;
    }

    public void setTemplateId(String templateId) {
        this.templateId = templateId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public String getMiniProgramState() {
        return miniProgramState;
    }

    public void setMiniProgramState(String miniProgramState) {
        this.miniProgramState = miniProgramState;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public Map<String, Object> getData() {
        return data.data();
    }

    public void setData(WechatMessageData data) {
        this.data = data;
    }
}
