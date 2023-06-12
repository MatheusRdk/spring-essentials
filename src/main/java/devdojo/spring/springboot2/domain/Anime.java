package devdojo.spring.springboot2.domain;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //Get, set, equals hashcode, tostring...
@AllArgsConstructor //Construtores com todos atributos.
@NoArgsConstructor
@Entity
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //@Column(nullable = false) //Isso faz com que o name nao possa ser nulo. Mas so funciona se ainda nao tiver criado um bd, tem ser um novo.
    //Pra fazer isso no bd que ja existe, precisa da dependencia spring-boot-starter-validation
    @NotEmpty(message = "The anime name cannot be empty.")
    private String name;
}
