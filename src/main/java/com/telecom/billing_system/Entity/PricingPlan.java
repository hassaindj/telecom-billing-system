package com.telecom.billing_system.Entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pricing_plan")
public class PricingPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double callRate;

    private Double smsRate;

    private Double dataRate;
}
