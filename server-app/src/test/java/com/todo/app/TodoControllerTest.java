package com.todo.app;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.todo.app.model.Todo;
import com.todo.app.mvc.TodoController;
import com.todo.app.service.TodoServiceI;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TodoAppApplication.class)
@WebAppConfiguration
public class TodoControllerTest {

	private final int PAGE_NUMBER = 1;
    private final int PAGE_SIZE = 5;

	@Mock
	private TodoServiceI todoService;
	
    Pageable page;
	
	@Mock
	Page<Todo> todoList;

	private static String CONTENTS;

	@InjectMocks
	private TodoController todoController;
	
	private MockMvc mockMvc;

	@Before
	public void setup() throws JSONException {
		this.mockMvc = MockMvcBuilders.standaloneSetup(todoController).build();

		page = new PageRequest(PAGE_NUMBER, PAGE_SIZE);
		
		Todo todo = new Todo();
		todo.setId("1");
		todo.setStatus(1);
		todo.setText("Task Created");

		JSONObject outerObject = new JSONObject();
		JSONArray outerArray = new JSONArray();
		JSONObject innerObject = new JSONObject();
		JSONArray innerArray = new JSONArray();

		innerArray.put("test");
		innerArray.put("test1");
		innerArray.put("7676");

		innerObject.put("test2", "2");
		innerObject.put("data", innerArray);
		outerArray.put(innerObject);
		outerObject.put("rows", outerArray);
		    
		CONTENTS = outerObject.toString();
		
	}


	// Unit test negative scenario -testFindAllTodo
	@Test
	public void testFindAllTodoNotExist() throws Exception {
		this.mockMvc
				.perform(get("/api/todo")
						.contentType(MediaType.APPLICATION_FORM_URLENCODED).accept(
								MediaType.APPLICATION_FORM_URLENCODED))
						.andExpect(status().is4xxClientError());
	}

	// Unit test positive scenario -testcreateTodo
	@Test
	public void testcreateTodo() throws Exception {
		
		doNothing().when(todoService).save(anyString());
		
		this.mockMvc
				.perform(post("/api/todo")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(CONTENTS)
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is2xxSuccessful());

	}

	// Unit test negative scenario -testcreateTodo
	@Test
	public void testcreateTodoFail() throws Exception {
		
		this.mockMvc
				.perform(post("/api/todo")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content("")
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is4xxClientError());
		
	}

	// Unit test positive scenario -testupdateStatus
	@Test
	public void testupdateStatus() throws Exception {
		this.mockMvc
				.perform(put(
						"/api/todo/{id}", "id")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(CONTENTS)
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is2xxSuccessful());

	}

	// Unit test negative scenario -testupdateStatus
	@Test
	public void testupdateStatusFail() throws Exception {
		this.mockMvc
				.perform(put(
						"/api/todo/{id}", "id")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(CONTENTS)
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is2xxSuccessful());

	}

	// Unit test positive scenario -testdelete
	@Test
	public void testdelete() throws Exception {
		this.mockMvc
				.perform(put(
						"/api/todo/{id}", "1")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is2xxSuccessful());

	}

	// Unit test negative scenario -testdelete
	@Test
	public void testdeleteFail() throws Exception {
		this.mockMvc
				.perform(put(
						"/api/todo/{id}", "id")
						.contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(CONTENTS)
						.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().is2xxSuccessful());

	}
	
	

}
