package com.ssaffeine.ssaffeine.cafe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cafes")
@Data
@NoArgsConstructor
public class Cafe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Integer cafeId;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "cafe_number", length = 20, nullable = false)
    private String cafeNumber;
}