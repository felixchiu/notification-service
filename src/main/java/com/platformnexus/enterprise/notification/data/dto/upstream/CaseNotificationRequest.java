package com.platformnexus.enterprise.notification.data.dto.upstream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CaseNotificationRequest {

    private Long caseId;
    private String event;

    private String testType;
    private Date opened;
    private Date closed;

    private List<ParentKitInfo> parentKits;
}
