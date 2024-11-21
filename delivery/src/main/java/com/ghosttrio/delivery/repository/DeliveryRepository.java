package com.ghosttrio.delivery.repository;

import com.ghosttrio.delivery.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
}
