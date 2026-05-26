package com.telecom.billing_system.Service;


import com.telecom.billing_system.Dto.BillingResponseDTO;
import com.telecom.billing_system.Entity.CdrRecords;
import com.telecom.billing_system.Repository.CdrRecordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CdrRecordsService {

    @Autowired
    private CdrRecordsRepository cdrRecordsRepository;


    //SAVE records
    public CdrRecords saveRecords(CdrRecords cdrRecords) {
        return cdrRecordsRepository.save(cdrRecords);

    }

    // Get All records
    public List<CdrRecords> getAllRecords() {
        return cdrRecordsRepository.findAll();
    }

    //Bill calliculation
    public BillingResponseDTO calculateBill(Long id){

        CdrRecords cdrRecords = cdrRecordsRepository.findById(id).orElseThrow();

        BillingResponseDTO billingResponseDTO = new BillingResponseDTO();

        double callRate = 0.50;
        double smsRate = 0.10;
        double dataRate = 0.05;

        double callCharges =
                cdrRecords.getDurationSeconds() * callRate;

        double smsCharges =
                cdrRecords.getSmsCount() * smsRate;

        double dataCharge =
                cdrRecords.getDataUsageMb() * dataRate;

        double total =
                callCharges + smsCharges + dataCharge;

        billingResponseDTO.setCallCharges(callCharges);

        billingResponseDTO.setSmsCharges(smsCharges);

        billingResponseDTO.setDataCharges(dataCharge);

        billingResponseDTO.setTotalBill(total);

        return billingResponseDTO;
    }

    public Double caluculateReSellerBill(String resellerName){

        List<CdrRecords> cdrRecordsList = cdrRecordsRepository.findByResellerName(resellerName);

        double totalBill = 0;

        for(CdrRecords cdrRecords : cdrRecordsList){

            double callCharges = cdrRecords.getDurationSeconds() * 0.50;

            double smsCharges = cdrRecords.getSmsCount() * 0.10;

            double datacharge = cdrRecords.getDataUsageMb() * 0.05;

            totalBill += callCharges + smsCharges + datacharge;

        }

    return totalBill;

    }




}
