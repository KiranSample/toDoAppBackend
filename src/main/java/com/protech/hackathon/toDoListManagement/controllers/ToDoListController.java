package com.protech.hackathon.toDoListManagement.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.protech.hackathon.toDoListManagement.dto.ToDoTaskDTO;
import com.protech.hackathon.toDoListManagement.services.ToDoApplicationService;

@RestController
@RequestMapping("/toDoList")
public class ToDoListController {
	
	@Autowired
	private ToDoApplicationService service;
	
	@GetMapping
	public List<ToDoTaskDTO> getToDoList() {
		return service.getToDoList();
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody ToDoTaskDTO toDoTaskDTO) {
		return new ResponseEntity<ToDoTaskDTO>(service.createToDoTask(toDoTaskDTO),HttpStatus.OK);
	} 

	@GetMapping(path = "/{id}")
	public ResponseEntity<?>  getToDoTask(@PathVariable Long id) {
		return new ResponseEntity<ToDoTaskDTO>(service.getTaskById(id),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> updateToDoTaskStatus(@RequestBody ToDoTaskDTO toDoTaskDTO) {
		return new ResponseEntity<ToDoTaskDTO>(service.updateTaskStatus(toDoTaskDTO),HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public  ResponseEntity<Long> deleteToDoTask(@PathVariable Long id) {
	   service.delete(id);
	   return new ResponseEntity<>(id, HttpStatus.OK);
	}
}
