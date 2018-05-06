package com.platformnexus.enterprise.notification.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.platformnexus.enterprise.notification.data.model.AppConstants;
import com.platformnexus.enterprise.notification.data.service.MessageLogDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Service
@Slf4j
public class MessageLogService {

    @Autowired
    private MessageLogDataService dataService;

    @Async("messageLogExecutor")
    public void addMessageLog(String messageId, String entityName, String direction, JsonNode message, String username) {
        dataService.addMessageLog(messageId, entityName, direction, message, username);
    }

    public void addOutgoingMessageLog(String messageId, String entityName, JsonNode message, String username) {
        addMessageLog(messageId, entityName, AppConstants.MESSAGE_DIRECTION_OUTGOING, message, username);
    }

    public void addIncomingMessageLog(String messageId, String entityName, JsonNode message, String username) {
        addMessageLog(messageId, entityName, AppConstants.MESSAGE_DIRECTION_INCOMING, message, username);
    }

}
