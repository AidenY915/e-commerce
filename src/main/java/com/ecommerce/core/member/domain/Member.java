package com.ecommerce.core.member.domain;

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
    private LocalDateTime modifyDt;

    @Column(name = "salt_key", length = 14, nullable = false)
    private String saltKey;

    private String flag;

    private Member(String flag, String saltKey, LocalDateTime modifyDt, UUID modifyId, LocalDateTime regDt, UUID regId, String phone, String password, String name, String email, UUID id) {
        this.flag = flag;
        this.saltKey = saltKey;
        this.modifyDt = modifyDt;
        this.modifyId = modifyId;
        this.regDt = regDt;
        this.regId = regId;
        this.phone = phone;
        this.password = password;
        this.name = name;
        this.email = email;
        this.id = id;
    }

    private Member(UUID id,
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

    private Member(String email, String name, String password, String phone, String saltKey, String flag) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime now = LocalDateTime.now();
        this.id=uuid;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.saltKey = saltKey;
        this.flag = flag;
        this.regId = uuid;
        this.modifyId = uuid;
        this.regDt = now;
        this.modifyDt = now;
    }

    public static Member create(String email, String name, String password, String phone, String saltKey, String flag) {
        return new Member(email, name, password, phone, saltKey, flag);
    }


    public void update(String email, String name, String password, String phone){
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

}
