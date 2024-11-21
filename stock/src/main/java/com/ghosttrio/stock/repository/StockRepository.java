package com.ghosttrio.stock.repository;

import com.ghosttrio.stock.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    Optional<Stock> findByProductId(Long productId);

    void deleteByProductId(Long productId);
}
