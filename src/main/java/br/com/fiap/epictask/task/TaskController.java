package br.com.fiap.epictask.task;

// requisições da ‘web’
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

// vai mapear as requisiões para o endereço específico
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET -
    @GetMapping
    public String index(Model model){
        var tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        // página que vai ser exibida
        return "index";
    }
}
