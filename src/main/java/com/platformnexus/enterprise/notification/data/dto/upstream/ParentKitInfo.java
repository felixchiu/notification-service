package com.platformnexus.enterprise.notification.data.dto.upstream;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Created by Felix Chiu on 2/23/18.
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ParentKitInfo {

    private long id;

    private String state;

    private String microdelState;

    private String supportState;

    private String reflexState;
}

