package com.bancow.process.repository;

import com.bancow.process.domain.farm.repository.FarmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
class FarmRepositoryTest {

    @Autowired
    FarmRepository farmRepository;

//    @Test
//    public void test(){
//        Farm farm = new Farm();
////        farm.setFarmName("농장명");
//        farmRepository.save(farm);
//
//        System.out.println(farmRepository.findAll());
//
//    }

}