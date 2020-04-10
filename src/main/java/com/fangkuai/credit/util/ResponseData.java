package com.fangkuai.credit.util;

/**
 * @创建人 lin
 * @创建时间 2020/4/8
 * @描述
 */

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 响应
 *
 * @author lin
 * @date : 2018-08-08
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseData implements Serializable {

    private static final long serialVersionUID = -6936648847780505144L;

    /**
     * 状态码
     */
    public Integer code;

    /**
     * 返回的消息
     */
    public String message;

    /**
     * 返回的数据
     */
    public Object data;

    @Override
    public String toString() {
        return "ResponseData{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }

    public static ResponseData success() {
        return success("request succeeded");
    }

    public static ResponseData success(Object data) {
        return success("request succeeded", data);
    }

    public static ResponseData success(String message, Object data) {
        return success(HTTP.SC_OK, message, data);
    }

    public static ResponseData success(Integer code, String message, Object data) {
        return build(code, message, data);
    }

    public static ResponseData error(Integer code) {
        return error(code, "request fail");
    }

    public static ResponseData error(Integer code, String message) {
        return error(code, message, null);
    }

    public static ResponseData error(Integer code, String message, Object data) {
        return build(code, message, data);
    }

    public static ResponseData build(HTTP.Status status) {
        return build(status, null);
    }

    public static ResponseData build(HTTP.Status status, Object data) {
        return build(status.value(), status.getReasonPhrase(), data);
    }

    public static ResponseData build(Integer code, String message, Object data) {
        return new ResponseData(code, message, data);
    }
}
