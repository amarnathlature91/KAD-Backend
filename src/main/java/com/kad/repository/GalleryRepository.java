package com.kad.repository;

import com.kad.entity.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GalleryRepository extends JpaRepository<Gallery,Long> {
}
