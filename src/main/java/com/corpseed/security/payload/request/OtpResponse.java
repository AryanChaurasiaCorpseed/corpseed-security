package com.corpseed.security.payload.request;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class OtpResponse {

    private String mobile;
    private String otp;
}
