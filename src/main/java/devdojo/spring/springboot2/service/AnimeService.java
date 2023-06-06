package devdojo.spring.springboot2.service;

import devdojo.spring.springboot2.domain.Anime;
import devdojo.spring.springboot2.exception.BadRequestException;
import devdojo.spring.springboot2.mapper.AnimeMapper;
import devdojo.spring.springboot2.repository.AnimeRepository;
import devdojo.spring.springboot2.requests.AnimePostRequestBody;
import devdojo.spring.springboot2.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service  //Faz com que essa classe seja um spring bean.
@RequiredArgsConstructor //Spring faz a injeção de dependencias no repository.
public class AnimeService {
    private final AnimeRepository animeRepository;


    public List<Anime> listAll() {
        return animeRepository.findAll();
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
