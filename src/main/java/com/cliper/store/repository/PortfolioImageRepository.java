package com.cliper.store.repository;

import com.cliper.store.domain.PortfolioImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortfolioImageRepository extends JpaRepository<PortfolioImage, Integer> {
}
