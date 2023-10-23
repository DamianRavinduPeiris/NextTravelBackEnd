package com.damian.es.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class EmailDetails implements Serializable {
    private String name;
    private String toEmail;
    private String subject;
    private String body;


}
