package devdojo.spring.springboot2.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST) //Isso faz com que essa exceção customizada retorne o http bad request.
public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }
}
