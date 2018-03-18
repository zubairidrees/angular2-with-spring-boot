package com.todo.app;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.todo.app.common.Constants.Status;
import com.todo.app.model.Todo;
import com.todo.app.mvc.TodoController;
import com.todo.app.repository.TodoRepository;

/**
 * TestTodoServiceImpl - This class is used to test all CRUD services for todo
 * app
 * 
 * @author Zubair Idrees
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoAppApplication.class)
public class TodoServiceImplTest {

	@MockBean
	private TodoRepository todoRepository;

	@Mock
	Page<Todo> todoList;

	@Mock
	Pageable page;


	@Test
	public void testFindAll() throws Exception {
		
		
		when(todoRepository.findAll(page)).thenReturn(todoList);
		assertEquals(false, todoRepository.findAll(page).hasNext());
	}

	@Test
	public void testSave() throws Exception {
		Todo todo = new Todo();
		todo.setId("1");
		todo.setText("test");
		todo.setStatus(Status.PENDING.getId());
		
		when(todoRepository.save(todo)).thenReturn(todo);
		
		assertEquals("1", todoRepository.save(todo).getId());
	}

	@Test
	public void testSaveFail() throws Exception {
		Todo todo = new Todo();
		todo.setId("1");
		todo.setText("test");
		todo.setStatus(Status.PENDING.getId());
		
		when(todoRepository.save(todo)).thenReturn(todo);
		
		assertEquals("1", todoRepository.save(todo).getId());
	}

	@Test
	public void testDelete() throws Exception {
		todoRepository.delete("SomeId");
	}

	@Test
	public void testUpdateStatus() throws Exception {
		Todo todo = new Todo();
		todo.setId("1");
		todo.setStatus(1);
		todo.setText("Task Created");
		
		when(todoRepository.findOne("SomeId")).thenReturn(todo);
		
		Todo todoTest = todoRepository.findOne("SomeId");
		
		assertEquals(todo, todoTest);
		
		todoTest.setStatus(todoTest.getStatus());
		
		when(todoRepository.save(todo)).thenReturn(todo);
		
		assertEquals("1", todoRepository.save(todoTest).getId());
	}

}
