package com.protech.hackathon.toDoListManagement.dto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.protech.hackathon.toDoListManagement.model.ToDoTask;

public class ToDoTaskMapper {

	public static List<ToDoTaskDTO> mapToDoTasks(List<ToDoTask> toDoTasks) {
		if(!toDoTasks.isEmpty()) {
			return toDoTasks.stream().map(toDoTask -> mapToDtoTask(toDoTask)).collect(Collectors.toList());
			}
		return Collections.emptyList();
	}

	public static ToDoTaskDTO mapToDtoTask(ToDoTask domain) {
		ToDoTaskDTO dto = new ToDoTaskDTO();
		dto.setId(domain.getId());
		dto.setTaskName(domain.getTaskName());
		dto.setTaskDescription(domain.getTaskDescription());
		dto.setCompleted(domain.getCompleted());
		
		return dto;
	}

	public static ToDoTask mapToDoDomain(ToDoTaskDTO dto, ToDoTask domain) {
		 domain.setTaskName(dto.getTaskName());
		 domain.setTaskDescription(dto.getTaskDescription());
		 return domain;
	}
}
