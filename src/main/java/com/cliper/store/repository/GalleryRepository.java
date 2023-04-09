package com.cliper.store.repository;

import com.cliper.store.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryRepository extends JpaRepository<Gallery, Integer> {
    List<Gallery> findAllByStatusEqualsOrderByDateDesc(int status);
}
