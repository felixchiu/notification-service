package com.platformnexus.enterprise.notification.data.repository.notification;

import com.platformnexus.enterprise.notification.data.entity.notification.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Felix Chiu on 2/26/18.
 */
@Repository
public interface EventRepository extends JpaRepository<Event, String> {

}
