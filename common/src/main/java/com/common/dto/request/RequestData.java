package com.common.dto.request;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * @since  2019-07-16
 * @author ONEC
 */

@Data
public class RequestData implements Serializable {

    private static final long serialVersionUID = -8008271239921313489L;

    @NotEmpty(message = "version必传")
    private String version;

    @NotEmpty(message = "charset必传")
    private String charset;

    @NotEmpty(message = "sign必传")
    private String sign;

    private String source;

    @NotEmpty(message = "orgNo必传")
    private String orgNo;

    @NotEmpty(message = "service必传")
    private String service;

    private String bizContent;

    @NotEmpty(message = "timestamp必传")
    private String timestamp;

}
