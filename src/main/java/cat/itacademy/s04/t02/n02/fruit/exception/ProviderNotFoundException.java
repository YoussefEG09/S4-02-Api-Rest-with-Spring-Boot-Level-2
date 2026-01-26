package cat.itacademy.s04.t02.n02.fruit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProviderNotFoundException extends RuntimeException {
    public ProviderNotFoundException(Long id) {
        super("Provider with id " + id + " not found");
    }
}
