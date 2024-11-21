package com.ghosttrio.stock.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Long stock;

    public void decrease(Long quantity) {
        boolean isRemainStock = stock >= quantity;

        if (isRemainStock) {
            throw new RuntimeException();
        } else {
            stock -= quantity;
        }
    }
}
