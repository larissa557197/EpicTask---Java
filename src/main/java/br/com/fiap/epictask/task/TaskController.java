package br.com.fiap.epictask.task;

// requisições da ‘web’
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.bind.Bindable;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// vai mapear as requisiões para o endereço específico
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final MessageSource messageSource;


    // GET - busca todas as tarefas e envia para a view index
    @GetMapping
    public String index(Model model){
        var tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "index";
    }


    // GET - prepara a view form e garante um Task no Model
    // útil ao reexibir o formulário com erros
    @GetMapping("/form")
    public String form(){
        return "form";
    }


    // POST - valida, salva via serviço, coloca mensagem i18n como flash e redireciona para /task
    // PRG -> evita reenvio em refresh
    @PostMapping("/form")
    public String create(@Valid Task task, BindingResult result, RedirectAttributes redirect ){ //biding

        if(result.hasErrors()) return "form";

        var message = messageSource.getMessage("task.create.success", null, LocaleContextHolder.getLocale());
        taskService.save(task);
        redirect.addFlashAttribute("message", message);
        return "redirect:/task"; //302
    }
}