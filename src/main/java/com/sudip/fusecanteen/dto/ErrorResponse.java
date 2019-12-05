package com.sudip.fusecanteen.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String message;
    private String calledUrl;
    private String cause;
}
