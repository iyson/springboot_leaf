package com.example.demo.web.login.vo;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserVo {
    private Integer seq;
    private String userId;
    private String password;
    private String tel;
    private Integer tryCnt;
    private Integer smsTryCnt;
    private String accountLockYn;
    private String activateYn;
    private LocalDateTime modDate;
    private String teamId;
    private String name;
    private String resetPasswordYn;
    private LocalDateTime regDate;
    private Integer modUser;
    private Integer maxConsultingCnt;
    private String centerId;
    private LocalDateTime loginDate;
    private String compCode;
    private String deptCode;
    private Long gradeSeq;
    private String managerScope;
    private Long managerSeq;
    private String hp;
    private String email;
    private Integer regUser;
    private String saltKey;
    private String status;
    private LocalDateTime passwordUpdateDate;
    private String captchaCheck;
    private String mqttConnectYn;
    private LocalDateTime mqttDisconnectDate;
    private String mqttSessionId;
    private String isJoin;
    private LocalDateTime heartbeatDate;
    private String distributionPauseYn;
    private LocalDateTime distributionPauseDate;
    private String swingId;
    private String passwordHistory;
    private String smsVerification;
}