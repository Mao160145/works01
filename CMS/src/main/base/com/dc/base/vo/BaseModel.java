package com.dc.base.vo;

import com.wordnik.swagger.annotations.ApiModelProperty;

public class BaseModel {
    @ApiModelProperty(value = "存放消息状态值，0表示正常，非0表示异常")
    private int resultCode;// 正确消息是0；错误是1,未登录9，登录后无权限8
    @ApiModelProperty(value = "存放返回的响应消息")
    private String message = "";// 存放返回的消息（9未登录或会话超时，请重新登录！）
    @ApiModelProperty(value = "存放返回的结果集")
    private Object Data;// 存放查询结果集
    private String token;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return Data;
    }

    public void setData(Object data) {
        Data = data;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseModel{");
        sb.append("resultCode=").append(resultCode);
        sb.append(", message='").append(message).append('\'');
        sb.append(", Data=").append(Data);
        sb.append(", token='").append(token).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
