package com.platformnexus.enterprise.notification.service;

import com.platformnexus.enterprise.notification.data.dto.message.NotificationMessage;
import com.platformnexus.enterprise.notification.data.entity.notification.Event;
import com.platformnexus.enterprise.notification.data.service.EventDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EventService {

    @Autowired
    private EventDataService dataService;

    @Async("eventExecutor")
    public Event createEvent(NotificationMessage message) {
        Event event = Event.builder()
                .entityName(message.getEntityName())
                .requestBody(message.getMessage().toString())
                .token(message.getToken()).build();
        event.setCreatedBy(message.getPrincipal()!=null ? message.getPrincipal().getName() : "UNKNOWN");
        return dataService.createEvent(event);
    }


}
