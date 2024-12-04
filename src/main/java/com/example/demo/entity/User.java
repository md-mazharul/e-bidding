package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user-data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String name;
    private Long phone_number;
    private String status;
    private Long suspend_number;

    private Double[] rating;
    private String[] favorite;
    private Long[] purchase;
    private Long[] recent_view;
    @OneToMany(cascade = CascadeType.ALL)
    private List<BidHistory> blogList;





    /*
    private List<Double> rating;
    private List<String> favorite;
    private List<Long> purchase;
    private List<Long> recent_view;

     */

}
