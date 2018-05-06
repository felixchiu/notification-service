package com.platformnexus.enterprise.notification.data.service;

import com.platformnexus.enterprise.notification.data.entity.notification.Event;
import com.platformnexus.enterprise.notification.data.repository.notification.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Felix Chiu on 5/6/18.
 */
@Service
public class EventDataService {

    @Autowired
    private EventRepository repository;

    public Event createEvent(Event event) {
        return repository.saveAndFlush(event);
    }

}
