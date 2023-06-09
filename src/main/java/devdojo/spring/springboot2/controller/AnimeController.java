package devdojo.spring.springboot2.controller;

import devdojo.spring.springboot2.domain.Anime;
import devdojo.spring.springboot2.requests.AnimePostRequestBody;
import devdojo.spring.springboot2.requests.AnimePutRequestBody;
import devdojo.spring.springboot2.service.AnimeService;
import devdojo.spring.springboot2.util.DateUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController //Isso diz que essa classe retorna apenas strings. Isso é o que api's retornam.
@RequestMapping("animes") //Sempre no plural esse que descreve o controle inteiro. Se tiver apenas um get, ele retorna esse. Bem como para post.
@Log4j2
@RequiredArgsConstructor //Cria um construtor pra todos atributos final.
public class AnimeController {
    private final DateUtil dateUtil;  //Vc cria e faz injeção de dependencia, nao inicia um objeto direto aqui.
    private final AnimeService animeService;
    @GetMapping //Isso faz o /animes acessar o retorno desse método, pq é o unico get.
    public ResponseEntity<Page<Anime>> list(Pageable pageable){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAll(pageable), HttpStatus.OK); //Vc esta retornando esse ReponseEntity pq é normal retornar mais algumas informções.
    }

    @GetMapping(path = "/all") //Isso faz o /animes acessar o retorno desse método, pq é o unico get.
    public ResponseEntity<List<Anime>> listAll(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return new ResponseEntity<>(animeService.listAllNonPageable(), HttpStatus.OK); //Vc esta retornando esse ReponseEntity pq é normal retornar mais algumas informções.
    }
    //Com o hotswap depois que vc colocou a dependencia spring-devtools, vc n precisa parar e iniciar a aplicaçao pra ver mudanças, so dar um build(Ctrl+F9)
//    @GetMapping(path = "list2")
//    public List<Anime> list2(){
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//        return List.of(new Anime("DBZ"), new Anime("ZZDB"), new Anime("One pizza"));
//    }


    //La no application.yml ou application.properties vc coloca pra nao printar o stacktrace inteiro no frontend quando der excessão.
    //La vc colocou never, mas pode colocar on_param, para se acessar a url com o id + ?trace=true, ai ele mostra o stacktrace.
    @GetMapping(path = "/{id}") //Assim vc pode fazer o findById, e o numero que colocar de id no fim da url vai ser procurado.
    public ResponseEntity<Anime> findById(@PathVariable long id){
        return ResponseEntity.ok(animeService.findByIdOrThrowBadRequestException(id));
    }

    @GetMapping(path = "/find") //Vc precisa mudar o caminho, pois tem 2 gets. A url fica http://localhost:8080/animes/find?name=OnePizza
    public ResponseEntity <List<Anime>> findByName(@RequestParam(required = false) String name){ //O request param usa o nome que vc der ali pra pesquisar por nome.
        return ResponseEntity.ok(animeService.findByName(name));
    }

    @PostMapping
    public ResponseEntity<Anime> save(@RequestBody @Valid AnimePostRequestBody animePostRequestBody){ //Pra vc fazer o post, vc passa um json so com o nome, e o metodo
        // gera o id e cria o objeto. A anotaçao valid é do spring-boot-starter-validation, pros campos que nao podem ser nulos.
        return new ResponseEntity<>(animeService.save(animePostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}") //Metodo idempotente: nao importa quantas vezes executar no servidor, a resposta é sempre a mesma. O delete pode ser e pode nao ser.
    public ResponseEntity<Void> delete(@PathVariable long id){
        animeService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> replace(@RequestBody AnimePutRequestBody animePutRequestBody){ //Metodo idempotente, sempre vai ter a mesma resposta no servidor independente de quantas vzes for executado.
        animeService.replace(animePutRequestBody);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
