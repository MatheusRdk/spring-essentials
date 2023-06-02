package devdojo.spring.springboot2.controller;

import devdojo.spring.springboot2.domain.Anime;
import devdojo.spring.springboot2.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController //Isso diz que essa classe retorna apenas strings. Isso é o que api's retornam.
@RequestMapping("anime")
@Log4j2
@RequiredArgsConstructor //Cria um construtor pra todos atributos final.
public class AnimeController {
    private final DateUtil dateUtil;  //Vc cria e faz injeção de dependencia, nao inicia um objeto direto aqui.
    @GetMapping(path = "list") //Isso faz o /list acessar o retorno desse método.
    public List<Anime> list(){
        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
        return List.of(new Anime("DBZ"), new Anime("ZZDB"));
    }

    //Com o hotswap depois que vc colocou a dependencia spring-devtools, vc n precisa parar e iniciar a aplicaçao pra ver mudanças, so dar um build(Ctrl+F9)
//    @GetMapping(path = "list2")
//    public List<Anime> list2(){
//        log.info(dateUtil.formatLocalDateTimeToDatabaseStyle(LocalDateTime.now()));
//        return List.of(new Anime("DBZ"), new Anime("ZZDB"), new Anime("One pizza"));
//    }
}
