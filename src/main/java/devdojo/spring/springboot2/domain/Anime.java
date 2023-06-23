package devdojo.spring.springboot2.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

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
    @NotEmpty(message = "The anime's name cannot be null")
    private String name;
}
