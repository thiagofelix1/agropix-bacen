package com.agropix.bacen.adapter.in.web.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DomainErrorResponse {
    private Collection<String> errors;
    private int statusCode;
}
