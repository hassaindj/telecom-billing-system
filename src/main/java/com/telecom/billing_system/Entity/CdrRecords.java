package com.telecom.billing_system.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cdr_records")
public class CdrRecords {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String callerNumber;

    private String receiverNumber;

    private String callType;

    private Integer durationSeconds;

    private Integer smsCount;

    private Double dataUsageMb;

    private String resellerName;

    private LocalDateTime callDate;
}
