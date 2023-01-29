package ru.netology.moneytransfer.exception;

public class TransactionErrorException extends RuntimeException{
    public TransactionErrorException(String msg){
        super(msg);
    }
}
