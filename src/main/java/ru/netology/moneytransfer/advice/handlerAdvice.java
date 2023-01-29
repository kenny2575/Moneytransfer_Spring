package ru.netology.moneytransfer.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransfer.exception.InvalidDataException;
import ru.netology.moneytransfer.exception.TransactionErrorException;
import ru.netology.moneytransfer.model.card.CardErrorAnswer;

@RestControllerAdvice
public class handlerAdvice {
    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<CardErrorAnswer> ideHandler(InvalidDataException e) {
        return new ResponseEntity<>(new CardErrorAnswer(e.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(TransactionErrorException.class)
    public ResponseEntity<CardErrorAnswer> treHandler(TransactionErrorException e) {
        return new ResponseEntity<>(new CardErrorAnswer(e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
