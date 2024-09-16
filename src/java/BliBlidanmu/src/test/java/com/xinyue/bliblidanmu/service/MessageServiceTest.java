package com.xinyue.bliblidanmu.service;

import java.time.Instant;
import java.util.*;
import java.math.*;

import com.xinyue.bliblidanmu.entity.MessageRequest;
import com.xinyue.bliblidanmu.service.MessageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class MessageServiceTest {

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createFormData_ValidRequest_ReturnsExpectedString() throws UnsupportedEncodingException {
        // Arrange
        MessageRequest request = new MessageRequest();
        request.setBubble("1");
        request.setColor("16777215");
        request.setCsrf("csrf");
        request.setCsrfToken("csrfToken");
        request.setFontsize("25");
        request.setMode("1");
        request.setMsg("test message");
        request.setRnd("123456");
        request.setRoomid("27945770");

        String expected = "color=16777215&fontsize=25&mode=1&msg=test+message&rnd=123456&roomid=1&bubble=1&csrf_token=csrfToken&csrf=csrf";

        // Act
        String actual = messageService.createFormData(request);

        // Assert
        assertEquals(expected, actual);
    }

    @Test
    public void createFormData_InvalidRequest_ThrowsUnsupportedEncodingException() {
        // Arrange
        MessageRequest request = new MessageRequest();
        request.setBubble("1");
        request.setColor("16777215");
        request.setCsrf("csrf");
        request.setCsrfToken("csrfToken");
        request.setFontsize("25");
        request.setMode("1");
        request.setMsg("test message");
        request.setRnd("123456");
        request.setRoomid("123456"); // Invalid value

        // Act & Assert

    }
    @Test
    public void splitTextIntoArray() {
        long timestamp = Instant.now().toEpochMilli();
        System.out.println(System.currentTimeMillis());// resultList.toArray(new String[0]);
    }
}

