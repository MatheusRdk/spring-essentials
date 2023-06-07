package devdojo.spring.springboot2.service;

import devdojo.spring.springboot2.domain.Anime;
import devdojo.spring.springboot2.exception.BadRequestException;
import devdojo.spring.springboot2.mapper.AnimeMapper;
import devdojo.spring.springboot2.repository.AnimeRepository;
import devdojo.spring.springboot2.requests.AnimePostRequestBody;
import devdojo.spring.springboot2.requests.AnimePutRequestBody;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service  //Faz com que essa classe seja um spring bean.
@RequiredArgsConstructor //Spring faz a injeção de dependencias no repository.
public class AnimeService {
    private final AnimeRepository animeRepository;


    public Page<Anime> listAll(Pageable pageable) {
        return animeRepository.findAll(pageable);
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime findByIdOrThrowBadRequestException(long id) {
        return animeRepository.findById(id)
                        .orElseThrow(() -> new BadRequestException("Anime not found."));
//                animes.stream()
//                .filter(anime -> anime.getId().equals(id))
//                .findFirst()
//                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found.")); //Muitos usam tambem um retorno do erro 404.
    }

    @Transactional//(rollbackFor = Exception.class) //Quando vc usa isso, se houver uma exceção no meio do método, ele nao será executado por completo. Por ex.: Se vc salvar
    // um anime com esse metodo e der uma exceção (que nao impessa a criação do objeto anime no banco de dados), ele vai ser criado igual. Se tiver essa anotação,
    // nao será salvo. Ele "cancela" a transação. O rollbackFor com exception.class faz isso funcionar pra excecoes do tipo checked tambem.
    public Anime save(AnimePostRequestBody animePostRequestBody){
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowBadRequestException(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowBadRequestException(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }
}
