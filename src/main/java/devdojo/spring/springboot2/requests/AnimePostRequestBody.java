package devdojo.spring.springboot2.requests;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody { //Classe DTO
    @NotEmpty(message = "The anime name cannot be empty.")  //Anotaçoes do spring-boot-starter-validation pra campos que nao podem ser nulos nem vazios.
    @Schema(description = "This is the anime's name", example = "DBZ")
    private String name; //Faz só o nome, pq o post do anime nao precisa receber um id.
}
