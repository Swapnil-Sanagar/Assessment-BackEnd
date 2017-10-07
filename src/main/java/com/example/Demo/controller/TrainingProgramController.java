package com.example.Demo.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Demo.model.TrainingProgram;
import com.example.Demo.repository.TrainingProgramRepository;

@RestController
@RequestMapping("/trainingprogram")
@CrossOrigin("*")
public class TrainingProgramController {
	
	@Autowired
	TrainingProgramRepository trainingProgramRepository;
	
	@GetMapping("/list")
	public List<TrainingProgram> getTrainingPrograms() {
		List<TrainingProgram> returnList = new ArrayList<TrainingProgram>();
		try {
			List<TrainingProgram> trainingProgramList = trainingProgramRepository.findAll();
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			for(TrainingProgram trainingProgram : trainingProgramList){
				trainingProgram.setScheduledDate(formatter.parse(formatter.format(trainingProgram.getScheduledDate())));
				returnList.add(trainingProgram);
			}
		} catch(Exception e){
			System.out.println("Error occured while fetching training programs");
			e.printStackTrace();
		}
		return returnList;
	}
	
	@PostMapping("/add")
	public TrainingProgram addTrainingProgram(@Valid @RequestBody TrainingProgram trainingProgram){
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy"); 
			String scheduledDate= formatter.format(trainingProgram.getScheduledDate());
			Date scheduleDateObj = formatter.parse(scheduledDate);
			trainingProgram.setScheduledDate(scheduleDateObj);
		} catch(Exception e){
			System.out.println("Error occured while parsing date...");
			e.printStackTrace();
		}
		return trainingProgramRepository.save(trainingProgram);
	}
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<TrainingProgram> updateTrainingProgram(@PathVariable("id") String id, 
            @Valid @RequestBody TrainingProgram trainingProgram) {
		TrainingProgram program = trainingProgramRepository.findOne(id);
		if(program == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
		program.setStatusVal(trainingProgram.getStatusVal());
		
		TrainingProgram updatedProgram = trainingProgramRepository.save(program);
		return new ResponseEntity<>(updatedProgram, HttpStatus.OK);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void deleteTrainingProgram(@PathVariable("id") String id){
		trainingProgramRepository.delete(id);
	}
}
