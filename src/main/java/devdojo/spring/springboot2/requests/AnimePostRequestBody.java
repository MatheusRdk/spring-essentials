package devdojo.spring.springboot2.requests;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody { //Classe DTO
    public String name; //Faz só o nome, pq o post do anime nao precisa receber um id.
}
