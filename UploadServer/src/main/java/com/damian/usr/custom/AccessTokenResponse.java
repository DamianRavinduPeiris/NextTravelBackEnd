package com.damian.usr.custom;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AccessTokenResponse {
    private String access_token;
    private String expires_in;
    private String scope;
    private String token_type;
}
