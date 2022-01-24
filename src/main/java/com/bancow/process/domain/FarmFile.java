package com.bancow.process.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "farm_file")
@Data
@NoArgsConstructor
public class FarmFile extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 원래 파일 이름
    @Column(name = "original_file_name")
    private String originalFileName;

    // 식별 번호가 적용된 적용된 파일 이름
    @Column(name = "changed_file_name")
    private String changedFileName;

    // 파일 경로
    @Column(name = "file_url")
    private String fileUrl;

    // 파일 타입
    @Enumerated(EnumType.STRING)
    @Column(name = "file_type")
    private FileType fileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id")
    @JsonManagedReference
    private Farm farm;

    @Builder
    public FarmFile(Farm farm, String originalFileName, String changedFileName, String fileUrl, FileType fileType) {
        this.farm = farm;
        this.originalFileName = originalFileName;
        this.changedFileName = changedFileName;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }

    public void updateFile(String originalFileName, String changedFileName, String fileUrl, FileType fileType) {
        this.originalFileName = originalFileName;
        this.changedFileName = changedFileName;
        this.fileUrl = fileUrl;
        this.fileType = fileType;
    }
}