package com.platformnexus.enterprise.notification.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platformnexus.enterprise.notification.NotificationServiceTest;
import com.platformnexus.enterprise.notification.data.dto.message.NotificationMessage;
import com.platformnexus.enterprise.notification.data.entity.notification.MessageLog;
import com.platformnexus.enterprise.notification.data.repository.notification.MessageLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.net.URL;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Slf4j
public class MessageSenderTest extends NotificationServiceTest {

    @Autowired
    private MessageSender sender;

    @Autowired
    private MessageLogRepository repository;

    private final static ObjectMapper mapper = new ObjectMapper();

    @Test
    public void send() throws Exception {

        JsonNode data = mapper.readValue(getFile("test_data/notification_json_01.json"), JsonNode.class);

        log.info("Json value: {}", data);
        sender.send(NotificationMessage
                .builder()
                .token("A1234")
                .entityName("ENTITY_NAME")
                .message(data)
                .build());
        List<MessageLog> messageLogs = repository.findByMessageId("A1234");
        assertTrue(messageLogs.size()> 0);
    }

    private File getFile(String path) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }


}