package com.xinyue.bliblidanmu.entity;

import lombok.Data;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * 用于封装消息请求的信息
 */
@Data
public class MessageRequest {

    @NotNull(message = "sendNumber cannot be null")
    private int sendNumber;

    private String color;

    private String fontsize; // 已改为float类型以支持小数

    private String mode;

    @NotNull(message = "msg cannot be null")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9\\s]+$", message = "Invalid message format")
    private String msg;

    private String rnd;

    @NotNull(message = "roomid cannot be null")
    private String roomid; // 已改为Integer以支持整数

    private String bubble;

    @NotNull(message = "csrfToken cannot be null")
    private String csrfToken;

    @NotNull(message = "csrf cannot be null")
    private String csrf;

    @NotNull(message = "csrf cannot be null")
    @Pattern(regexp = "^[\\u4e00-\\u9fa5a-zA-Z0-9\\s]+$", message = "Invalid message format")
    private String cookie;

}
