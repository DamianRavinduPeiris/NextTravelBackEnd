package com.damian.as.repo;

import com.damian.as.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminRepo extends MongoRepository<Admin,String> {
}
