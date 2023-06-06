package devdojo.spring.springboot2.requests;


import lombok.Data;

@Data
public class AnimePutRequestBody { //Classe DTO
    private Long id;
    private String name;
}
