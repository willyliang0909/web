package com.willy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "staffinfo")
@ApiModel(description = "員工資料")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ApiModelProperty(value = "信箱", required = true)
    private String email;
    @JsonIgnore
    @ApiModelProperty(value = "密碼")
    private String password;
    private String position;
    private String phone;
    @Column(updatable = false)
    //@JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    @CreatedDate
    private LocalDate createDate;

    @Column(updatable = false)
    @LastModifiedDate
    private LocalDateTime updateDate;

    @CreatedBy
    private String createBy;

    @LastModifiedBy
    private String updateBy;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "detail_id", referencedColumnName = "did")
    //@PrimaryKeyJoinColumn
    private StaffDetail staffDetail;
}
