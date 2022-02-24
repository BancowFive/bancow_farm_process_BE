package com.bancow.process.domain.farm.dto.request;

import com.bancow.process.domain.model.InProgress;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class InProgressUpdateRequestDto {

    @NotNull
    private Long pageNum;

    @NotNull
    private InProgress inProgress;

}
