package com.example.board.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@ToString
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditingFields {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @CreatedDate
    @Column(name = "createdAt", updatable = false)
    private LocalDateTime createdAt; // 생성일시 auditing

    @CreatedBy
    @Column(name = "createdBy", updatable = false, length = 100)
    String createdBy; // 생성자

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @LastModifiedDate
    @Column(name = "modifiedAt")
    private LocalDateTime modifiedAt; // 수정일시

    @LastModifiedBy
    @Column(name = "modifiedBy", length = 100)
    String modifiedBy; // 수정자
}
