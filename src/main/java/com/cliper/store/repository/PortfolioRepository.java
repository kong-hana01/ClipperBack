package com.cliper.store.repository;

import com.cliper.store.domain.Gallery;
import com.cliper.store.domain.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
    List<Portfolio> findAllByStatusEqualsOrderByDateDesc(int status);
}
