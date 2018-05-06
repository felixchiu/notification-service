package com.platformnexus.enterprise.notification.data.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platformnexus.enterprise.notification.data.entity.notification.MessageLog;
import com.platformnexus.enterprise.notification.data.repository.notification.MessageLogRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Slf4j
@Service
public class MessageLogDataService {

    @Autowired
    private MessageLogRepository repository;

    private final static ObjectMapper mapper = new ObjectMapper();

    public void addMessageLog(String messageId, String entityName, String direction, JsonNode message) {
        try {
            repository.saveAndFlush(
                    MessageLog.builder()
                    .logDatetime(new Date())
                    .message(mapper.writeValueAsString(message))
                    .messageId(messageId)
                    .entityName(entityName)
                    .direction(direction)
                    .build()
            );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
