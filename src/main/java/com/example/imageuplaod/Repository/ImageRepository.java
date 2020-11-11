package com.example.imageuplaod.Repository;

import com.example.imageuplaod.Model.ImageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<ImageModel,Integer> {
}
