package com.example.learn.boot.unittest.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Data
@Table(name = "employee")
@EqualsAndHashCode(callSuper = false)
public class PersistentEmployeeEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String address;

    @Column(nullable = false)
    private String email;

    private String phone;

}
