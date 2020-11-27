package com.protech.hackathon.toDoListManagement.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.protech.hackathon.toDoListManagement.exceptions.ResourceNotFoundException;
import com.protech.hackathon.toDoListManagement.dto.ToDoTaskDTO;

import com.protech.hackathon.toDoListManagement.model.ToDoTask;
import com.protech.hackathon.toDoListManagement.repositories.ToDoTaskJPARepository;

import static com.protech.hackathon.toDoListManagement.dto.ToDoTaskMapper.*;


@Service
public class ToDoApplicationService {
	
	@Autowired
	private ToDoTaskJPARepository toDoTaskJPARepository;
	
	
	@Transactional
	public ToDoTaskDTO createToDoTask(ToDoTaskDTO dto) {
		ToDoTask toDoTask = new ToDoTask();
		toDoTask.setCompleted(false);
		mapToDoDomain(dto, toDoTask);
		return mapToDtoTask(toDoTaskJPARepository.save(toDoTask));
	}
	
	@Transactional(readOnly = true)
	public List<ToDoTaskDTO> getToDoList() {
		return mapToDoTasks(toDoTaskJPARepository.findAll());
	}
	
	@Transactional(readOnly = true)
	public ToDoTaskDTO getTaskById(Long id) {
		ToDoTask toDoTask = toDoTaskJPARepository.findById(id)
		.orElseThrow(() -> new ResourceNotFoundException("ToDo Task", "id", id));
		return mapToDtoTask(toDoTask);
	}
	
	@Transactional(readOnly = true)
	public ToDoTaskDTO updateTaskStatus(ToDoTaskDTO toDoTaskDTO) {
		ToDoTask toDoTask = toDoTaskJPARepository.findById(toDoTaskDTO.getId())
		.orElseThrow(() -> new ResourceNotFoundException("ToDo Task", "id", toDoTaskDTO.getId()));
		toDoTask.setCompleted(toDoTaskDTO.isCompleted());
		
		return mapToDtoTask(toDoTaskJPARepository.save(toDoTask));
	}
	
	@Transactional
	public void delete(Long id) {
		ToDoTask toDoTask = toDoTaskJPARepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("ToDo Task", "id", id));
			
		toDoTaskJPARepository.delete(toDoTask);
	}
}