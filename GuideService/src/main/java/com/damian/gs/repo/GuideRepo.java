package com.damian.gs.repo;

import com.damian.gs.entity.Guide;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GuideRepo extends MongoRepository<Guide,String> {
}
