package com.bancow.process.global.security.auth;

import com.bancow.process.domain.farm.domain.Farm;
import com.bancow.process.domain.farm.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@Service
public class PrincipalDetailsService implements UserDetailsService {

    @Autowired
    private FarmRepository farmRepository;

    public PrincipalDetailsService(FarmRepository farmRepository) {
        this.farmRepository = farmRepository;
    }

    // 파라미터로 받아온 userName을 통해 db에서 userName이 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        Farm userEntity = farmRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new UsernameNotFoundException(phoneNumber + "을 찾을 수 없습니다. "));
        return new PrincipalDetails(userEntity);
    }
}
