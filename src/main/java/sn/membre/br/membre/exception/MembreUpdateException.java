package sn.membre.br.membre.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MembreUpdateException extends     RuntimeException {
    public MembreUpdateException(String s) {
        super(s);
    }
}
