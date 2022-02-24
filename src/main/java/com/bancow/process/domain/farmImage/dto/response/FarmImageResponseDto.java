package com.bancow.process.domain.farmImage.dto.response;

import com.bancow.process.domain.model.ImageType;

import com.bancow.process.domain.farmImage.domain.FarmImage;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class FarmImageResponseDto {

    private Long farmImageId;
    private String originalImageName;
    private String changedImageName;
    private String imageUrl;
    private ImageType imageType;

    public FarmImageResponseDto(FarmImage farmImage) {
        this.farmImageId = farmImage.getId();
        this.originalImageName = farmImage.getOriginalImageName();
        this.changedImageName = farmImage.getChangedImageName();
        this.imageUrl = farmImage.getImageUrl();
        this.imageType = farmImage.getImageType();
    }

}
