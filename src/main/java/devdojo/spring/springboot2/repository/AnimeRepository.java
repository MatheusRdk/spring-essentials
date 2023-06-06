package devdojo.spring.springboot2.repository;

import devdojo.spring.springboot2.domain.Anime;

import java.util.List;

public interface AnimeRepository {
    List<Anime> listAll();
}
