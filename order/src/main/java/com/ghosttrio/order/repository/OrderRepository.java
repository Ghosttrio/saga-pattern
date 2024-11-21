package com.ghosttrio.order.repository;

import com.ghosttrio.order.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    void deleteByProductId(Long productId);
}
