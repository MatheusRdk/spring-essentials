package devdojo.spring.springboot2.client;

import devdojo.spring.springboot2.domain.Anime;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Log4j2
public class springClient {
    public static void main(String[] args) {

        //Métodos get
//        ResponseEntity<Anime> entity = new RestTemplate().getForEntity("http://localhost:8080/animes/4", Anime.class);
//        log.info(entity);

        Anime object = new RestTemplate().getForObject("http://localhost:8080/animes/{id}", Anime.class, 4);
        log.info(object);

//        Anime[] animes = new RestTemplate().getForObject("http://localhost:8080/animes/all", Anime[].class);
//        log.info(Arrays.toString(animes));   Nao e mto atual usar arrays.

        ResponseEntity<List<Anime>> exchange = new RestTemplate().exchange("http://localhost:8080/animes/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                });

        log.info(exchange.getBody());

        //Métodos post

//        Anime kingdom = Anime.builder().name("kingdom").build();  //Um jeito de salvar um objeto
//        Anime kingdomSaved = new RestTemplate().postForObject("http://localhost:8080/animes", kingdom, Anime.class);
//        log.info("saved anime {}", kingdomSaved);

        Anime samurai = Anime.builder().name("Samurai aiai").build();
        ResponseEntity<Anime> samuraiSaved = new RestTemplate().exchange("https://localhost:8080/animes",
                HttpMethod.POST,
                new HttpEntity<>(samurai),
                Anime.class);
        log.info("saved anime {}", samuraiSaved);


        //Métodos put e delete


        Anime animeToBeUpdated = samuraiSaved.getBody();
        animeToBeUpdated.setName("Samurai aiai 2 a volta do");

        ResponseEntity<Void> samuraiUpdated = new RestTemplate().exchange("http://localhost:8080/animes",
                HttpMethod.PUT,
                new HttpEntity<>(animeToBeUpdated, createJsonHeader()),
                Void.class); //Usamos void.class quando nao tem nenhum objeto na requisição.
        log.info(samuraiUpdated);

        ResponseEntity<Void> samuraiDeleted = new RestTemplate().exchange("http://localhost:8080/animes/{id}",
                HttpMethod.DELETE,
                null,
                Void.class, //Usamos void.class quando nao tem nenhum objeto na requisição.
                animeToBeUpdated.getId());
        log.info(samuraiDeleted);
    }

    private static HttpHeaders createJsonHeader(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        return httpHeaders;
    }
}
