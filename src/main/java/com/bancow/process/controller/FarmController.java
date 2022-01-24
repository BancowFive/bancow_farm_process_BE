package com.bancow.process.controller;

import com.bancow.process.Test;
import com.bancow.process.domain.Farm;
import com.bancow.process.repository.FarmRepository;
import com.bancow.process.service.CertificationService;
import com.bancow.process.service.FarmService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor

public class FarmController {

    private final FarmService farmService;
    private final CertificationService certificationService;
    private final Test test;


    @PostMapping("/login")
    public void sendUsername(@RequestParam String userName){
        farmService.join(userName);
    }

    @GetMapping("/check/sendSMS")
    public String sendSMS(@RequestBody String phoneNumber) {

        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        System.out.println("수신자 번호 : " + phoneNumber);
        System.out.println("인증번호 : " + numStr);
        certificationService.certifiedPhoneNumber(phoneNumber,numStr);
        return numStr;
    }
    @GetMapping("/test")
    public void test(){
        test.sendSms();
    }


}
