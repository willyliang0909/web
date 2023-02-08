package com.willy.model;

import io.swagger.models.auth.In;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "staffdetail")
public class StaffDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer did;
    private String detail;

    //@OneToOne(mappedBy = "staffDetail")
    //private Staff staff;

}
