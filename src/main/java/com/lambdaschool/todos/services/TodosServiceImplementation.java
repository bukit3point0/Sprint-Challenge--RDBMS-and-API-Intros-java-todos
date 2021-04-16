package com.lambdaschool.todos.services;

import com.lambdaschool.todos.models.Todos;
import com.lambdaschool.todos.models.User;
import com.lambdaschool.todos.repository.TodosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Transactional
@Service(value = "todoservice")
public class TodosServiceImplementation implements TodosService{
    @Autowired
    TodosRepository todosrepo;

    @Transactional
    @Override
    public void markComplete(long id) {
        Todos markTodo = todosrepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(id + " not found"));
        markTodo.setCompleted(true);
    }

    @Transactional
    @Override
    public Todos save(Todos todos) {
        if (todos.getUser().getTodos().isEmpty()) {
            throw new EntityNotFoundException("Todos must be created by a user");
        }
        return todosrepo.save(todos);
    }
}
