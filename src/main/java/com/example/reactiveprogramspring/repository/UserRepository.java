package com.example.reactiveprogramspring.repository;

import com.example.reactiveprogramspring.model.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 4:05 PM 31-Dec-22
 * Long Tran
 */
@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {
}
