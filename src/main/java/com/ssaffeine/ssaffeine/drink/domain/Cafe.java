package com.ssaffeine.ssaffeine.drink.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import javax.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@Entity
@Table(name = "cafes")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cafe_id")
    private Integer cafeId;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="number", length = 20)
    @Pattern(regexp = "^\\d{3}-\\d{4}-\\d{4}$", message = "Invalid cafe number format. Expected format: XXX-XXXX-XXXX")
    private String number;

    @Column(name = "address", length = 100)
    private String address; // 주소는 필수 아님

}
