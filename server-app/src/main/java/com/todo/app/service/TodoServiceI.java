package com.todo.app.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.todo.app.model.Todo;
/**
 * TodoServiceI - Interface used to define Todo Services
 * @author Zubair Idrees
 *
 */
public interface TodoServiceI {

	public Page<Todo> findAll(Pageable page);
	public void save(String text);
	public void delete(String todoId);
	public void updateStatus(String todoId,Integer statusId);
}
