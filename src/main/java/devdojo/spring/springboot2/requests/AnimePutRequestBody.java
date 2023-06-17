package devdojo.spring.springboot2.requests;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnimePutRequestBody { //Classe DTO
    private Long id;
    private String name;
}
