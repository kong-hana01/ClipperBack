package com.cliper.store.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@Getter
@Setter
@DiscriminatorColumn
@NoArgsConstructor
@DynamicInsert
public abstract class BaseEntity {
    @CreatedDate
    @Column(updatable = false, name = "create_at", nullable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @LastModifiedDate
    @Column(updatable = true, name = "update_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;

    @Column(name = "status", columnDefinition = "int default 1")
    private int status = 1;

    public void delete() {
        this.status = 0;
    }

    // 삭제된 데이터 복구
    public void restore() {
        this.status = 1;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", status=" + status +
                '}';
    }
}
