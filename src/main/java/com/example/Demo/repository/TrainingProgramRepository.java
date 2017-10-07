package com.example.Demo.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.Demo.model.TrainingProgram;

@Repository
public interface TrainingProgramRepository extends MongoRepository<TrainingProgram,String> {
	
}
