package com.bancow.process.domain.farmFile.dto.request;

import com.bancow.process.domain.model.FileType;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
public class FileUpdateRequestDto {

    @NotBlank
    private String originalFileName;

    @NotBlank
    private String changedFileName;

    @NotBlank
    private String fileUrl;

    @NotNull
    private FileType fileType;

}

