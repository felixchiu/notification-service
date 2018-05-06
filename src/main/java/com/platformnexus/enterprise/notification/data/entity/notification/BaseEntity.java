package com.platformnexus.enterprise.notification.data.entity.notification;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by Felix Chiu on 5/5/18.
 */

@MappedSuperclass
@EntityListeners(EntityListener.class)
@Data
public class BaseEntity implements Serializable {

    @JsonProperty("id")
    @Id
    @Column(name = "id", unique = true)
    @GeneratedValue(generator = "token-uuid")
    @GenericGenerator(name = "token-uuid", strategy = "com.platformnexus.enterprise.notification.util.InquisitiveUUIDGenerator")
    protected String id;

    @JsonProperty("updated_datetime")
    @Column(name="updated_datetime")
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    protected Timestamp updatedDatetime;

    @JsonProperty("updated_by")
    @Column(name="updated_by")
    protected String updatedBy;

    @NotNull
    @JsonProperty("created_datetime")
    @Column(name="created_datetime", updatable = false)
    @JsonFormat(shape= JsonFormat.Shape.STRING)
    @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
    protected Timestamp createdDatetime;

    @NotBlank
    @JsonProperty("created_by")
    @Column(name="created_by", updatable = false)
    protected String createdBy;

    @JsonProperty("active_record_indicator")
    @Column(name = "active_record_indicator")
    protected boolean activeRecord = true;

    @Version
    @JsonProperty("version")
    @Column(name = "version")
    protected Integer version;

}