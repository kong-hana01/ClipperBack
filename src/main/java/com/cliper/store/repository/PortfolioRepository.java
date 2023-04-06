package com.cliper.store.repository;

import com.cliper.store.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioRepository extends JpaRepository<Gallery, Integer> {
}
