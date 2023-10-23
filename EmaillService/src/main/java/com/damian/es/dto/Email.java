package com.damian.es.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class Email implements Serializable {
    private String toEmail;
    private String subject;
    private String body;


}
