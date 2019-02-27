package com.roadmap.app.advertisement.repository;

import com.roadmap.app.advertisement.entity.Advertisement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {

    @Override
    List<Advertisement> findAll();
}
