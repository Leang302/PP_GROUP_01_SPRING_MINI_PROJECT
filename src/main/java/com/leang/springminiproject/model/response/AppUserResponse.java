package com.leang.springminiproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserResponse {
    private UUID appUserId;
    private String username;
    private String email;
    private Integer level;
    private Integer xp;
    private Boolean isVerified;
    private Instant createdAt;
}
