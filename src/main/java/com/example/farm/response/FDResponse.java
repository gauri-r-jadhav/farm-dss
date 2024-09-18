package com.example.farm.response;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FDResponse<T> {
    private String status = "SUCCESS";
    private FDError error;
    private T response;

    public FDResponse(T response) {
        this.response = response;
        this.status = "SUCCESS";
    }

    public FDResponse(FDError error) {
        this.error = error;
        this.status = "ERROR";
    }
}
