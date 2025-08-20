package br.com.fiap.epictask.task;

// salvar no banco de dados
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;

// sem precisar fzr construtores
import lombok.Builder;

// n precisar fzr manual get set, etc
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    // regras de negocio


    // Identificador: usa a chave primária Long como auto-incremento no banco (IDENTITY)
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // titulo: não pode ser vazio/branco
    // mensagem internacionaliza via chave: task.title.notblank
    @NotBlank(message = "{task.title.notblank}")
    private String title;

    // campo pro usuario escrever a tarefa
    // description: deve ter entre 20 e 255 caracters
    // mensagem via chave: task.description.size
    @Size(min = 20, max = 255, message = "{task.description.size}")
    private String description;

    // elementos de gameficação - pontos de conclusão de tarefa
    // score no intervalo [1..100]
    // mensagens via task.score.min e task.score.max
    @Min(value = 1, message = "{task.score.min}")
    @Max(value = 100, message = "{task.score.max}")
    private int score;

    // estatistica - status da tarefa ( de 0 a 100%)
    @Min(0) @Max(100)
    private int status;
}
