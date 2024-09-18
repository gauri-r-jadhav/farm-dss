package com.example.farm.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@AllArgsConstructor
public class FDError {
        private String errorCode;
        private String errorType;
        private String errorMessage;
    }

