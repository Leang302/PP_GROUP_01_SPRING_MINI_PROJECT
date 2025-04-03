package model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Profile {
    private UUID appUserId;
    private String username;
    private String password;
    private String email;
    private Integer level;
    private Integer xp;
    private Boolean isVerified;
    private Instant createdAt;
}
