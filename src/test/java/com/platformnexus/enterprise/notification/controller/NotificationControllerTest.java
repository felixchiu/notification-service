package com.platformnexus.enterprise.notification.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platformnexus.enterprise.notification.NotificationServiceTest;
import com.platformnexus.enterprise.notification.data.dto.api.NotificationResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.net.URL;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Slf4j
public class NotificationControllerTest extends NotificationServiceTest {

    @Autowired
    private NotificationController controller;

    private final static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void processNotification() throws Exception {

        URL jsonURL = this.getClass().getResource("/test_data/notification_json_01.json");
        HttpServletRequest httpServletRequest = Mockito.mock(HttpServletRequest.class);
        JsonNode data = mapper.readValue(new File(jsonURL.getFile()), JsonNode.class);
        ResponseEntity<NotificationResponse> response = controller.processNotification("sample_entity_name", data, httpServletRequest);
        Assert.assertEquals(true, response.getBody().getNotified());
        Assert.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

}