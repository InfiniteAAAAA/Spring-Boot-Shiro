package com.infinite.pojo;

/**
 * 响应统一规范代码
 * 支持方法,
 * 返回成功值,
 * 返回指定错误代码,从ResponseCodeEnum中获取
 *
 * @param <T> data数据泛型
 * @author liyuanmin
 */
public class ResponseBase<T> {
    /**
     * 响应代码
     */
    private String responseCode;
    /**
     * 错误描述
     */
    private String error;
    /**
     * 响应数据
     */
    private T data;

    /**
     * 响应时间
     */
    private String timeStrip;

    /**
     * 设定成功的数据和状态
     * 可以统一从ResponseCodeEnum中进行统一码和响应字符串的获取传递
     * 响应时间为默认当前毫秒数
     *
     * @param data 泛型数据对象
     */
    public ResponseBase<T> setSuccessStatusAndData(T data) {
        this.data = data;
        this.setResponseCode("0000");
        this.setTimeStrip(String.valueOf(System.currentTimeMillis()));
        return this;
    }

    /**
     * 设定成功的数据和状态
     * data数据为空情况下,不需要返回数据对象情况下调用
     * 可以统一从ResponseCodeEnum中进行统一码和响应字符串的获取传递
     * 响应时间为默认当前毫秒数
     */
    public ResponseBase<T> setSuccessStatusAndData() {
        this.setSuccessStatusAndData(null);
        return this;
    }

    /**
     * 错误设定码
     * 可以统一从ResponseCodeEnum中进行统一码和响应字符串的获取传递
     * 例子:setErrorResponse(responseParam, ResponseCodeEnum.IBE_INVOKE_UNKNOWN_ERROR.getMessage(), ResponseCodeEnum.IBE_INVOKE_UNKNOWN_ERROR.getCode());
     *
     * @param errorString 错误描述
     * @param errorCode   错误码
     */
    public ResponseBase<T> setErrorResponse(String errorString, String errorCode) {
        this.setError(errorString);
        this.setResponseCode(errorCode);
        this.setTimeStrip(String.valueOf(System.currentTimeMillis()));
        return this;
    }

    /**
     * 错误设定码
     * 可以统一从ResponseCodeEnum中进行统一码和响应字符串的获取传递
     * 例子:setErrorResponse(responseParam, ResponseCodeEnum.IBE_INVOKE_UNKNOWN_ERROR.getMessage(), ResponseCodeEnum.IBE_INVOKE_UNKNOWN_ERROR.getCode());
     *
     * @param errorString 错误描述
     * @param errorCode   错误码
     */
    public ResponseBase<T> setErrorResponse(String errorString, String errorCode, String cause) {
        this.setError(errorString + cause);
        this.setResponseCode(errorCode);
        this.setTimeStrip(String.valueOf(System.currentTimeMillis()));
        return this;
    }

    /*getter/setter*/

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getTimeStrip() {
        return timeStrip;
    }

    public void setTimeStrip(String timeStrip) {
        this.timeStrip = timeStrip;
    }
}
