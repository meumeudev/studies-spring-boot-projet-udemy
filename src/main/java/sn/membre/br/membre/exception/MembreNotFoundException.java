package sn.membre.br.membre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MembreNotFoundException extends RuntimeException{
    public MembreNotFoundException(String message){
        super(message);
    }
}
