package com.damian.usr.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor

@Data
public class GoogleResponseData {
    private String kind;
    private String id;
    private String name;
    private String mimeType;
}
