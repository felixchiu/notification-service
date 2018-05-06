package com.platformnexus.enterprise.notification.data.entity.notification;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Felix Chiu on 5/5/18.
 */
public class EntityListener {
    @PrePersist
    public void prePersist(BaseEntity entity) {
        entity.setCreatedDatetime(new Timestamp(new Date().getTime()));
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        entity.setUpdatedDatetime(new Timestamp(new Date().getTime()));
    }
}
