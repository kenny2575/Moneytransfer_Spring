package ru.netology.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.model.transaction.TransactionData;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class TransactionRepository {
    private ConcurrentHashMap<String, TransactionData> transactionRepo;

    public TransactionRepository(){
        this.transactionRepo = new ConcurrentHashMap<>();
    }

    public Optional<TransactionData> getTransaction(String operationId){
        return Optional.ofNullable(transactionRepo.getOrDefault(operationId, null));
    }

    public void saveTransaction(String operationId, TransactionData transaction){
        transactionRepo.put(operationId, transaction);
    }
}
