
package com.xinyue.bliblidanmu.control;

import com.xinyue.bliblidanmu.entity.MessageRequest;
import com.xinyue.bliblidanmu.service.MessageService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.time.*;
import java.util.*;


public class MessageController {
    private final MessageService messageService;


    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }
    public void sendMessage() {
        List<String> listText = Arrays.asList("666", "主播真厉害", "牛逼！！！");
        String roomid = "1972367563";
        MessageRequest request = createMessageRequest(listText, roomid);

        Map<String, String> headers = createHeaders();
        // 使Map不可变
        Map<String, String> immutableHeaders = Collections.unmodifiableMap(headers);

        messageService.sendPostRequest(request, immutableHeaders, new Callback() {

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
    }

    private MessageRequest createMessageRequest(List<String> listText, String roomid) {
        Random random = new Random();
        String sendMeg = listText.get(random.nextInt(listText.size()));
        long timestamp = Instant.now().toEpochMilli();
        int ti = (int) (timestamp / 1000);

        MessageRequest request = new MessageRequest();
        request.setMsg(sendMeg);
        request.setRnd(String.valueOf(ti));
        request.setRoomid(roomid);
        request.setColor("16777215");
        request.setFontsize("25");
        request.setMode("1");
        request.setBubble("0");
        request.setCsrf("c38ab58a7e58a7d1051ccffb73d40cfb");
        request.setCsrfToken("c38ab58a7e58a7d1051ccffb73d40cfb");
        return request;
    }

    private Map<String, String> createHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", "your_cookie_here");
        headers.put("origin", "https://live.bilibili.com");
        headers.put("referer", "https://live.bilibili.com/blanc/1029?liteVersion=true");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");
        return headers;
    }
}