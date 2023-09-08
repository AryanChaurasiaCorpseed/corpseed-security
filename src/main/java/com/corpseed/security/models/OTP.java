package com.corpseed.security.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;


import javax.validation.constraints.Size;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@ToString
@Table(name = "otp")
public class OTP {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Size(min = 10,max = 15,message = "Mobile length should be 10-15 digits.")
    private String mobile;

    @Column(name = "otp_code")
    private String otpCode;

    private String password;

    private Long count;

    @Column(name = "is_used")
    private boolean isUsed;

    @Column(name = "created_at")
    private Date created_at;

    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    @Column(name = "expired_at")
    private Date expiredAt;



}
