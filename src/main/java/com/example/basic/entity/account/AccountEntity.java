package com.example.basic.entity.account;

import com.example.basic.entity.common.BaseDateEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "account")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccountEntity extends BaseDateEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long idx;

  @Column(length = 100, nullable = false)
  private String id;

  @Column(length = 500, nullable = false)
  private String password;

  private String role;
}
