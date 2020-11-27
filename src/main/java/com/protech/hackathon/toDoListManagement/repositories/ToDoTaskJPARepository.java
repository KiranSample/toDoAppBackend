package com.protech.hackathon.toDoListManagement.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.protech.hackathon.toDoListManagement.model.ToDoTask;

public interface ToDoTaskJPARepository extends JpaRepository<ToDoTask, Long>{

}
