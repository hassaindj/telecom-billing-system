package com.telecom.billing_system.Controller;


import com.telecom.billing_system.Dto.BillingResponseDTO;
import com.telecom.billing_system.Entity.CdrRecords;
import com.telecom.billing_system.Service.CdrRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;

import java.io.InputStreamReader;

@RestController
@RequestMapping("/cdr")
public class CdrRecordController {

    @Autowired
    private CdrRecordsService cdrRecordsService;

    @PostMapping
    public CdrRecords saveRecords(@RequestBody CdrRecords cdrRecords) {
        return cdrRecordsService.saveRecords(cdrRecords);
    }


    @GetMapping
    public List<CdrRecords> getAllRecords(){
        return cdrRecordsService.getAllRecords();

   }

   @GetMapping("/bill/{id}")
   public BillingResponseDTO calculateBill(@PathVariable Long id){
        return cdrRecordsService.calculateBill(id);
   }

   @PostMapping("/upload")
   public String uploadFile(@RequestParam("file") MultipartFile file) {
        try{
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.getInputStream()));
            String line;

            //Skip CSV header
            bufferedReader.readLine();

            while((line = bufferedReader.readLine()) != null){

                String[] data = line.split(",");

                CdrRecords cdrRecords  = new CdrRecords();

                cdrRecords.setCallerNumber(data[0]);

                cdrRecords.setReceiverNumber(data[1]);

                cdrRecords.setCallType(data[2]);

                cdrRecords.setDurationSeconds(Integer.parseInt(data[3]));

                cdrRecords.setSmsCount(Integer.parseInt(data[4]));

                cdrRecords.setDataUsageMb(Double.parseDouble(data[5]));

                cdrRecords.setResellerName(data[6]);

                cdrRecordsService.saveRecords(cdrRecords);
            }
            return "CSV File Uploaded Successfully";
        } catch (Exception exception){

            return "Error (or) Uploading File Filed : " + exception.getMessage();
        }
   }
}
