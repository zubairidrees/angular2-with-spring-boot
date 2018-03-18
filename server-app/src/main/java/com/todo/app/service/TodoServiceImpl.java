package com.todo.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.todo.app.common.Constants.Status;
import com.todo.app.model.Todo;
import com.todo.app.repository.TodoRepository;
/**
 * TodoServiceImpl - This class is used to do all CRUD services for todo app
 * @author Zubair Idrees 
 */
@Service
public class TodoServiceImpl implements TodoServiceI{

	@Autowired
	public TodoRepository todoRepository;
	
	@Override
	public Page<Todo> findAll(Pageable page) {
		return todoRepository.findAll(page);
	}

	@Override
	public void save(String text) {
		Todo todo = new Todo();
		todo.setText(text);
		todo.setStatus(Status.PENDING.getId());
		todoRepository.save(todo);
		
	}

	@Override
	public void delete(String todoId) {
		todoRepository.delete(todoId);
		
	}

	@Override
	public void updateStatus(String todoId, Integer statusId) {
		Todo todo = todoRepository.findOne(todoId);
		todo.setStatus(statusId);
		todoRepository.save(todo);
		
	}

}
