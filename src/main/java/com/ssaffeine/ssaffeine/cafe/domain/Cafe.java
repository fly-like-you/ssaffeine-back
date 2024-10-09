package com.ssaffeine.ssaffeine.cafe.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "cafes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Integer cafeId;

    @Column(name = "cafe_name", length = 50)
    private String cafeName;

    @Column(name = "cafe_number", length = 20)
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "Invalid cafe number format. Expected format: XXX-XXXX-XXXX")
    private String cafeNumber;

    @Column(name = "address", length = 100)
    private String address;
}
