package com.bancow.process.controller;

import com.bancow.process.domain.Farm;
import com.bancow.process.dto.inputUserName;
import com.bancow.process.repository.FarmRepository;
import com.bancow.process.service.FarmService;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@RequiredArgsConstructor

public class FarmController {

    private final FarmService farmService;
    private final FarmRepository farmRepository;


    @PostMapping("/login")
    public ResponseEntity<Farm> sendUsername(@RequestBody String userName){
        return ResponseEntity.ok(farmService.join(userName));
    }

    @PostMapping("/test")
    public String test(@RequestParam String message){
        Farm farm = new Farm();
        farm.setUserName(message);
        farmRepository.save(farm);
        return message;
    }
}
