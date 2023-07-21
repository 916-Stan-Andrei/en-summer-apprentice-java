package com.practica.demoPractica.Repository;

import com.practica.demoPractica.Models.TicketCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketCategoryRepository extends JpaRepository<TicketCategory, Integer> {
}
