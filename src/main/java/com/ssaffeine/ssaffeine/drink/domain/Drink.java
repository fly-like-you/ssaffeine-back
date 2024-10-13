package com.ssaffeine.ssaffeine.drink.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drinks")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "drink_id")
    private Long drinkId;

    @Column(name="name", nullable = false, length = 50)
    private String name;

    @Column(name="price", nullable = false)
    private int price;

    @Column(name="kcals", nullable = true)
    private Integer kcals; // 칼로리는 nullable 허용

    @Column(name="caffeine_content", nullable = true)
    private Integer caffeineContent; // 카페인 함량도 nullable

    @Column(name="feature", length = 100)
    private String features; // 음료 특징

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    private Category category; // 음료 카테고리

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cafe_id", nullable = false)
    private Cafe cafe; // 음료가 속한 카페
}
