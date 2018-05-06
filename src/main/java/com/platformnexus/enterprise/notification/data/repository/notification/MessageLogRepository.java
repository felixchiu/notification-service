package com.platformnexus.enterprise.notification.data.repository.notification;

import com.platformnexus.enterprise.notification.data.entity.notification.MessageLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Repository
public interface MessageLogRepository extends JpaRepository<MessageLog, Long> {

    List<MessageLog> findByMessageId(String messageId);

}
