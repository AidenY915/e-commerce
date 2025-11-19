package com.ecommerce.core.member;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "member", schema = "public")
@AllArgsConstructor
@NoArgsConstructor
public class Member {
    @Id
    private UUID id;
    @Schema(description = "유저의 이메일")
    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(length = 20)
    private String name;

    @Column(length = 100)
    private String password;

    @Column(length = 20, unique = true)
    private String phone;

    @Column(name = "reg_id", unique = true)
    private UUID regId;

    @Column(name = "reg_dt", nullable = false)
    private LocalDateTime regDt;

    @Schema(description = "수정한 사람의 ID")
    @Column(name = "modify_id")
    private UUID modifyId;

    @Column(name = "modify_dt")
    private LocalDateTime midifyDt;

    @Column(name = "salt_key", length = 14, nullable = false)
    private String saltKey;

    private String flag;

    public Member(UUID id,
                 String email,
                 String name,
                 String password,
                 String phone,
                 String saltKey,
                 String flag) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.saltKey = saltKey;
        this.flag = flag;
    }
}
