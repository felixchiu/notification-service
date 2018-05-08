package com.platformnexus.enterprise.notification.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.platformnexus.enterprise.notification.data.dto.message.NotificationMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;



/**
 * Created by Felix Chiu on 2/22/18.
 */
@Slf4j
@Service
public class MessageSender {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private MessageLogService messageLogService;

    @Value("${redis.config.queue:notification_service}")
    private String queue;

    private final static ObjectMapper mapper = new ObjectMapper();

    public void send(NotificationMessage message) throws JsonProcessingException {
        messageLogService.addOutgoingMessageLog(message.getToken(), message.getEntityName(), message.getMessage(),
                message.getPrincipal() !=null ? message.getPrincipal().getName() : "UNKNOWN");
        String messageBody = mapper.writeValueAsString(message);
        log.debug("Message: {}", messageBody);
        template.convertAndSend(queue, messageBody);
    }

}
