package br.com.fiap.epictask.task;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// ser um componente
@Service
public class TaskService {


    // injetar um obj/ dependencias pelo contrutor - lombok (@AllArgs
    private final TaskRepository taskRepository;

    // construtor
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // metodo que retorna todas as tarefas
    public List<Task> getAllTasks(){
        return  taskRepository.findAll();
    }
}
