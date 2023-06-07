package devdojo.spring.springboot2.requests;


import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AnimePostRequestBody { //Classe DTO
    @NotEmpty(message = "The anime name cannot be empty.")  //Anotaçoes do spring-boot-starter-validation pra campos que nao podem ser nulos nem vazios.
    @NotNull(message = "The anime name cannot be null.")
    private String name; //Faz só o nome, pq o post do anime nao precisa receber um id.
}
