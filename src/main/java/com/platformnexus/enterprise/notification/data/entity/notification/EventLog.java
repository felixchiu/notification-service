package com.platformnexus.enterprise.notification.data.entity.notification;

import com.platformnexus.enterprise.notification.data.model.EventStatus;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Felix Chiu on 2/26/18.
 */
@Entity(name="eventLog")
@Table(name = "event_log", indexes = {
        @Index(name = "idx_event_lims_id",  columnList="lims_id"),
        @Index(name = "idx_event_status", columnList="status"),
        @Index(name = "idx_message_log_entity", columnList="entity_name")
}
)
@Data
public class EventLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="lims_id")
    private Long limsId;

    @Column(name="entity_name")
    private String entityName;

    @Column(name="message_id")
    private String messageId;

    @Column(name="log_datetime")
    private Date logDatetime;

    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private EventStatus status;

    @Column(name="created_date")
    private Date createdDate;
}
