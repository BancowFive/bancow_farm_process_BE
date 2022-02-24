package com.bancow.process.domain.farmImage.repository;


import com.bancow.process.domain.model.ImageType;
import com.bancow.process.domain.farmImage.domain.FarmImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FarmImageRepository extends JpaRepository<FarmImage, Long> {

    @Query("select fi from FarmImage fi " +
           "where fi.farm.id = :id and fi.imageType = :imageType")
    FarmImage findImage(@Param("id") Long id, @Param("imageType") ImageType imageType);

    List<FarmImage> findByFarmId(Long id);


}

