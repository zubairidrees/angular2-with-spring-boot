package com.todo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.todo.app.model.Todo;
/**
 * TodoRepository - This interface is used to do all CRUD operations for todo app 
 * using MongoRepository
 * @author Zubair Idrees 
 */
@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{

}
