package com.mercadolibre.lannister.charges;

import com.mercadolibre.lannister.api.Paginated;
import io.vavr.collection.List;
import io.vavr.control.Either;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface Controller<T> {
    default ResponseEntity<T> convertToResponse(Either<Throwable, T> res) {
        return res.map(t -> new ResponseEntity<T>(HttpStatus.OK)).getOrElse(new ResponseEntity<T>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
    default ResponseEntity<Paginated<T>> convertToPaginated(Either<Throwable, List<T>> res) {
        return res.map(t -> new ResponseEntity<>(new Paginated<T>(t, 0, 0), HttpStatus.OK)).getOrElse(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    }
}
