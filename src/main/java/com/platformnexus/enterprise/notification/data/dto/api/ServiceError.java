package com.platformnexus.enterprise.notification.data.dto.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

/**
 * Created by fchiu on 7/13/16.
 */
@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceError {

    private String errorType;

    private String errorCode;

    private String errorDescription;

    private String errorMessage;

}
