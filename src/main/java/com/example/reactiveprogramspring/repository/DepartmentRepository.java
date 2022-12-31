package com.example.reactiveprogramspring.repository;

import com.example.reactiveprogramspring.model.Department;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 4:06 PM 31-Dec-22
 * Long Tran
 */
@Repository
public interface DepartmentRepository extends ReactiveMongoRepository<Department, String> {

}
