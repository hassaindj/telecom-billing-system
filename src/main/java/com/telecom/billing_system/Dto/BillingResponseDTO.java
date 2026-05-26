package com.telecom.billing_system.Dto;

import lombok.Data;

@Data
public class BillingResponseDTO {

    private Double callCharges;

    private Double smsCharges;

    private Double dataCharges;

    private Double totalBill;
}
