package com.example.hiberdeployed.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int universityId;

    private String name;

    private short departments;

    @OneToMany (mappedBy = "university", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Student> studentList;



}
