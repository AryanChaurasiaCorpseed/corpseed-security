package com.corpseed.security.payload.request;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Data
public class OtpRequest {

    private String mobile;

    private String name;

    private String password;


}