package com.bancow.process.service;

import com.bancow.process.domain.Farm;
import com.bancow.process.repository.FarmRepository;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Data
@Builder
public class FarmService {

    private final FarmRepository farmRepository;
    private final CertificationService certificationService;
    private final PasswordEncoder passwordEncoder;

    public void join( String userName){

        // userName으로 번호가 있는지 조회
         Optional<Farm> user = farmRepository.findByUserName(userName);

        //인증번호 생성
        Random rand  = new Random();
        String numStr = "";
        for(int i=0; i<4; i++) {
            String ran = Integer.toString(rand.nextInt(10));
            numStr+=ran;
        }

        // 생성한 랜덤 인증번호를 인코딩
        String password = passwordEncoder.encode(numStr);

        if(user.isEmpty()) {
            //farm 객체 생성해서 userName과 인코딩한 password 저장
            Farm farm = new Farm();
            farm.setUserName(userName);
            farm.setPassword(password);
            farmRepository.save(farm);

        }else{
            Farm farm = user.get();
            farm.setPassword(password);
            farmRepository.save(farm);

        }

        // userName(폰 번호)과 인증번호 발송
        certificationService.certifiedPhoneNumber(userName, numStr);

    }

}
