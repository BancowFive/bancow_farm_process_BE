package com.bancow.process.domain.farm.dto.response;

import com.bancow.process.domain.model.InProgress;
import lombok.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class LoginResponseDto {

    private Long id;
    private String phoneNumber;
    private InProgress inProgress;

}
