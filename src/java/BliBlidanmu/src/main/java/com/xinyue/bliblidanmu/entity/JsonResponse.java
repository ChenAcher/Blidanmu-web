package com.xinyue.bliblidanmu.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class JsonResponse {
    private String msg;
    private LocalDateTime time;
}
