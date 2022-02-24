package com.bancow.process.domain.model;

import java.util.List;
import java.util.ArrayList;

public enum InProgress {
    STEP1_IN_PROGRESS,              // 1차 작성중
    STEP1_COMPLETED,                // 1차 제출 완료
    STEP2_START,                    // 2차 시작
    STEP2_IN_PROGRESS,              // 2차 작성중
    STEP2_COMPLETED,                // 2차 제출 완료
    STEP3_START,                    // 3차 시작
    STEP3_IN_PROGRESS,              // 3차 작성중
    STEP3_COMPLETED,                // 3차 제출 완료
    PROCESS_DONE;                   // 입점 완료

    public static List<InProgress> getStep1InProgressList() {
        List<InProgress> step1ProgressList = new ArrayList<>();
        step1ProgressList.add(STEP1_IN_PROGRESS);
        step1ProgressList.add(STEP1_COMPLETED);
        return step1ProgressList;
    }

    public static List<InProgress> getStep2InProgressList() {
        List<InProgress> step2ProgressList = new ArrayList<>();
        step2ProgressList.add(STEP2_IN_PROGRESS);
        step2ProgressList.add(STEP2_COMPLETED);
        step2ProgressList.add(STEP2_START);
        return step2ProgressList;
    }

}
