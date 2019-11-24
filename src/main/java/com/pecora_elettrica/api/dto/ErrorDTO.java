package com.pecora_elettrica.api.dto;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 34404L;

    @NonNull
    private HttpStatus status;
    @NonNull
    private String error;

}