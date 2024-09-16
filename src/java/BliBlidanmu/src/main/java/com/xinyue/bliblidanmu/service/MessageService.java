package com.xinyue.bliblidanmu.service;

import com.google.gson.Gson;
import com.xinyue.bliblidanmu.entity.JsonResponse;
import com.xinyue.bliblidanmu.entity.MessageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import okhttp3.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class MessageService {

    @Value("${bilibili.api.url}")
    private String URL;

    private static final OkHttpClient CLIENT = new OkHttpClient();

    public void sendPostRequest(MessageRequest request, Map<String, String> headers, Callback callback) {
        try {
            // 构建请求体
            RequestBody requestBody = RequestBody.create(
                    MediaType.parse("application/x-www-form-urlencoded; charset=utf-8"),
                    createFormData(request)
            );

            // 构建请求
            Request.Builder requestBuilder = new Request.Builder()
                    .url(URL)
                    .post(requestBody);
            // 添加请求头
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                requestBuilder.addHeader(entry.getKey(), entry.getValue());
            }

            Request requestToSent = requestBuilder.build();
            System.out.println("++++++++++++++++++");
            System.out.println("请求体"+requestToSent.toString());
            System.out.println("++++++++++++++++++");

            // 发送请求
            CLIENT.newCall(requestToSent).enqueue(callback);
        } catch (Exception e) {
            // 异常处理
            System.err.println("Error sending POST request: " + e.getMessage());
            e.printStackTrace();
        }
    }

    String createFormData(MessageRequest request) throws UnsupportedEncodingException {
        return "color=" + request.getColor() +
                "&fontsize=" + request.getFontsize() +
                "&mode=" + request.getMode() +
                "&msg=" + request.getMsg() +
                "&rnd=" + request.getRnd() +
                "&roomid=" + request.getRoomid() +
                "&bubble=" + request.getBubble() +
                "&csrf_token=" + request.getCsrfToken()+
                "&csrf=" + request.getCsrf();
    }


    public MessageRequest createMessageRequest(MessageRequest request) {
        MessageRequest BliRequest = new MessageRequest();

        ArrayList<String> listText = new ArrayList<>();
        if ("".equals(request.getMsg()) ) {
            listText.add("默认消息");
        } else {
            listText=splitTextIntoArray(request.getMsg());
        }
        Random random = new Random();
        System.out.println(listText.size());
        String sendMeg = listText.get(random.nextInt(listText.size()));
        long timestamp = Instant.now().toEpochMilli();
        int ti = (int) (timestamp / 1000);

        BliRequest.setMsg(sendMeg);
        BliRequest.setRnd(String.valueOf(ti));
        BliRequest.setRoomid(request.getRoomid());
        BliRequest.setColor("16777215");
        BliRequest.setFontsize("25");
        BliRequest.setMode("1");
        BliRequest.setBubble("0");
        BliRequest.setCsrf(request.getCsrf());
        BliRequest.setCsrfToken(request.getCsrfToken());
        return BliRequest;
    }
    public ArrayList<String> msgList(){
        return null;
    }

    public Map<String, String> createHeaders(String cookie) {
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", cookie);
        headers.put("origin", "https://live.bilibili.com");
        headers.put("referer", "https://live.bilibili.com/1972367563?live_from=71002&visit_id=1v9j3pfvaiqo");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/126.0.0.0 Safari/537.36");
        return headers;
    }

    public ResponseEntity<JsonResponse> jsonResponse(String msg) {
        // 构建包含消息和当前时间的对象
        LocalDateTime currentTime = LocalDateTime.now();
        JsonResponse jsonResponseObject = new JsonResponse();
        jsonResponseObject.setMsg(msg);
        jsonResponseObject.setTime(currentTime);
        System.out.println("请求失败，响应码: " + jsonResponseObject.toString());
        // 直接返回对象
        ResponseEntity<JsonResponse>  response=new ResponseEntity<>(jsonResponseObject, HttpStatus.OK);
        System.out.println("请求失败，响应码: " + response.toString());
        return response;
    }



    public ArrayList<String> splitTextIntoArray(String text) {
        String[] list = text.split(";");
        ArrayList<String> listText = new ArrayList<>();
        for (String s : list) {
            listText.add(s);
        }
        return listText;
    }

}
