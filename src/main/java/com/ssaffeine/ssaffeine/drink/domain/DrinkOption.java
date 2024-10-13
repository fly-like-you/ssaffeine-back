package com.ssaffeine.ssaffeine.drink.domain;

import com.ssaffeine.ssaffeine.order.domain.OrderDetail;
import jakarta.persistence.*;
import java.util.List;
import lombok.*;

@Entity
@Table(name = "drink_options")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DrinkOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="option_id")
    private Long drinkOptionId;

    @Column(name="name", nullable = false, length = 50)
    private String name; // 옵션 이름 (예: 샷 추가, 시럽 추가)

    @Column(name="price", nullable = false)
    private int price; // 옵션 가격

}
