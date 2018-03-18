package com.todo.app.mvc;

import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.todo.app.model.Todo;
import com.todo.app.service.TodoServiceI;
/**
 * TodoController - This class acts as rest controller for todo app 
 * @author Zubair Idrees 
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/api/todo")
public class TodoController {

	final static Logger LOG = LoggerFactory.getLogger(TodoController.class);

	@Autowired
	TodoServiceI todoService;

	/**
	 * 
	 * @param page
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Page<Todo>> findAllTodo(Pageable page,HttpServletRequest req) {
		Page<Todo> list = todoService.findAll(page);
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * 
	 * @param text
	 */
	@RequestMapping(value = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public void createTodo(@RequestBody String text) {
		todoService.save(text);
	}

	/**
	 * 
	 * @param id
	 * @param statusId
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public void updateStatus(@PathVariable String id, @RequestBody Integer statusId) {
		todoService.updateStatus(id, statusId);
	}

	/**
	 * 
	 * @param id
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@PathVariable String id) {
		todoService.delete(id);
	}

}
