package com.platformnexus.enterprise.notification.data.entity.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
/**
 * Created by Felix Chiu on 2/23/18.
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="message_log", indexes = {
        @Index(name = "idx_message_log_message_id",  columnList="message_id"),
        @Index(name = "idx_message_log_log_datetime", columnList="log_datetime"),
        @Index(name = "idx_message_log_entity", columnList="entity_name")
}
)
@Entity(name="messageLog")
public class MessageLog {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="message_id")
    private String messageId;

    @Column(name="entity_name")
    private String entityName;

    @Column(name="log_datetime")
    private Date logDatetime;

    @Column(name="direction")
    private String direction;

    @Lob
    @Column(name="message")
    private String message;

}
