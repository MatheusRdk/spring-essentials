package devdojo.spring.springboot2.requests;


import lombok.Data;

@Data
public class AnimePostRequestBody { //Classe DTO
    private String name; //Faz só o nome, pq o post do anime nao precisa receber um id.
}
