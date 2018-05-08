package com.platformnexus.enterprise.notification.data.entity.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by Felix Chiu on 2/26/18.
 */
@Entity(name="event")
@Table(name = "event", indexes = {
        @Index(name = "idx_event_token", columnList="token"),
        @Index(name = "idx_event_entity_name", columnList="entity_name"),
        @Index(name = "idx_event_created_by", columnList="created_by")
}
)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Event extends BaseEntity {

    @Column(name="entity_name")
    private String entityName;

    @Column(name="token")
    private String token;

    @Lob
    @Column(name="request_body")
    private String requestBody;
}
