package com.platformnexus.enterprise.notification.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.platformnexus.enterprise.notification.data.dto.api.NotificationResponse;
import com.platformnexus.enterprise.notification.data.dto.message.NotificationMessage;
import com.platformnexus.enterprise.notification.data.dto.api.ServiceError;
import com.platformnexus.enterprise.notification.service.EventService;
import com.platformnexus.enterprise.notification.service.MessageSender;
import com.platformnexus.enterprise.notification.util.TokenGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class NotificationController {

    @Autowired
    private TokenGenerator tokenGenerator;

    @Autowired
    private MessageSender sender;

    @Autowired
    private EventService eventService;

    @RequestMapping(
            value = {
                    "/platform/layer/{entityName}",
                    "/api/v1/notification/{entityName}"
            },
            method = RequestMethod.POST)
    public ResponseEntity<NotificationResponse> processNotification(@PathVariable String entityName, @RequestBody JsonNode message, HttpServletRequest httpServletRequest) {

        NotificationMessage notificationMessage = NotificationMessage
                .builder()
                .token(tokenGenerator.nextString())
                .entityName(entityName)
                .message(message)
                .principal(httpServletRequest!=null ? httpServletRequest.getUserPrincipal() : null)
                .build();
        try {
            eventService.createEvent(notificationMessage);
            sender.send(notificationMessage);
            return new ResponseEntity<>(NotificationResponse.builder().token(notificationMessage.getToken()).notified(true).build(), HttpStatus.ACCEPTED);
        } catch (JsonProcessingException e) {
            log.error("Notification failure with entity {}. Message {}: ", entityName, message);
            e.printStackTrace();
            List<ServiceError> errors = new ArrayList<>();
            errors.add(ServiceError.builder().errorMessage(e.getMessage()).build());
            return new ResponseEntity<>(NotificationResponse.builder().token(notificationMessage.getToken()).notified(false).serviceErrors(errors).build(), HttpStatus.BAD_REQUEST);
        }

    }

}
