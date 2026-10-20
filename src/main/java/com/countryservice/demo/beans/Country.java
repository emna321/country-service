package com.countryservice.demo.beans;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "country")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "country_name")
    private String countryName;

    @Column(name = "capital")
    private String capital;
}