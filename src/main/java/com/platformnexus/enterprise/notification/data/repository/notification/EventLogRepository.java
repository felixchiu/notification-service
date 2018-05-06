package com.platformnexus.enterprise.notification.data.repository.notification;

import com.platformnexus.enterprise.notification.data.entity.notification.EventLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by Felix Chiu on 2/26/18.
 */
@Repository
public interface EventLogRepository extends JpaRepository<EventLog, String> {

    @Query("select distinct e.limsId from eventLog e where e.createdDate<:processingDate and e" +
            ".entityName=:entityName and e.status<>'Processed'")
    public List<Long> getNewLimsIds(@Param("processingDate") Date processingDate, @Param("entityName") String
            entityName);

    @Query("select e from eventLog e where e.createdDate<:processingDate and e" +
            ".entityName=:entityName and e.limsId=:limsId and e.status <> 'Processed'")
    public List<EventLog> findEventLogs(@Param("limsId") Long limsId, @Param("entityName") String
            entityName, @Param("processingDate") Date processingDate);
}
