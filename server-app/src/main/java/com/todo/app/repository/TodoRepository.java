package com.todo.app.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.todo.app.model.Todo;

@Repository
public interface TodoRepository extends MongoRepository<Todo, String>{

}
