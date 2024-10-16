package com.ssaffeine.ssaffeine.order.domain;

import com.ssaffeine.ssaffeine.drink.domain.Drink;
import com.ssaffeine.ssaffeine.drink.domain.DrinkOptionMapping;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long orderDetailId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "weekday", nullable = false)
    private Integer weekday;

    @Column(name = "received", nullable = true)
    private LocalDateTime received;

    @OneToMany(mappedBy = "orderDetail", fetch = FetchType.LAZY)
    private List<DrinkOptionMapping> optionMappings = new ArrayList<>();

}
