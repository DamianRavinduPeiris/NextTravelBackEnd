package com.damian.gs.repo;

import com.damian.gs.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface GuideRepo extends MongoRepository<Guide, String> {

    @Query("{ 'guideName' : ?0 }")
    Optional<Guide> findByGuideName(String guideName);
}



