package com.xinyue.bliblidanmu.control;

import com.xinyue.bliblidanmu.entity.JsonResponse;
import com.xinyue.bliblidanmu.entity.MessageRequest;
import com.xinyue.bliblidanmu.service.MessageService;
import io.swagger.annotations.ApiOperation;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.time.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

import com.google.gson.Gson;

@CrossOrigin(origins = "http://192.168.1.12:8080")
@Configuration
@RestController
public class MessageController {
    private final MessageService messageService;
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    @ApiOperation(value = "数据接收接口")
    @PostMapping("/api/v1/danmu")
    public ResponseEntity<?> sendMessage (@RequestBody MessageRequest obj) {
        System.out.println(obj.toString());
        MessageRequest request = (MessageRequest)obj;
        MessageRequest blirequest = messageService.createMessageRequest(request);
        Map<String, String> headers = messageService.createHeaders(request.getCookie());
        // 使Map不可变
        Map<String, String> immutableHeaders = Collections.unmodifiableMap(headers);
        final Gson gson = new Gson();
        final AtomicReference<String> jsonResponse = new AtomicReference<>(); // 用于存储响应结果
        messageService.sendPostRequest(blirequest, immutableHeaders, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println("请求失败");
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    System.out.println("请求成功: " + response.body().string());
                } else {
                    System.out.println("请求失败，响应码: " + response.code());
                }
            }
        });

        // 构建包含消息和当前时间的对象
        String msg = blirequest.getMsg();
        return messageService.jsonResponse(msg);
    }
}
