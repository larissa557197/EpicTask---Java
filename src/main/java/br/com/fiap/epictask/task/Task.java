package br.com.fiap.epictask.task;

// salvar no banco de dados
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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


    // chave primaria
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // titulo
    private String title;

    // campo pro usuario escrever a tarefa
    private String description;

    // elementos de gameficação - pontos de conclusão de tarefa
    private int score;

    // estatistica - status da tarefa ( de 0 a 100%)
    private int status;
}
