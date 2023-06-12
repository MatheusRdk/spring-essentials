package devdojo.spring.springboot2.util;

import devdojo.spring.springboot2.domain.Anime;

public class AnimeCreator { //Representando tipos de animes pros testes
    public static Anime createAnimeToBeSaved(){  //Para ser salvo precisa so de nome
        return Anime.builder()
                .name("Dark side of the")
                .build();
    }

    public static Anime createValidAnime(){   //Anime completo
        return Anime.builder()
                .name("Dark side of the")
                .id(2L)
                .build();
    }

    public static Anime createValidUpdatedAnime(){ //Mesmo id nome diferente, para update
        return Anime.builder()
                .name("Dark side of the 2")
                .id(2L)
                .build();
    }
}
