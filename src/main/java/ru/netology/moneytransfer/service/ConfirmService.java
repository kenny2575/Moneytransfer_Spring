package ru.netology.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.netology.moneytransfer.exception.InvalidDataException;
import ru.netology.moneytransfer.exception.TransactionErrorException;
import ru.netology.moneytransfer.logger.Logger;
import ru.netology.moneytransfer.model.operation.Operation;
import ru.netology.moneytransfer.model.transaction.TransactionData;
import ru.netology.moneytransfer.repository.TransactionRepository;

@Service
public class ConfirmService {
    private static Logger logger = Logger.getLogger();
    private TransactionRepository transactionRepository;

    public ConfirmService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public Operation checkOperation(Operation operation){
        logger.log("Begin confirming operation");
        var transactionData = getTransactionData(operation);
        checkOperationCode(operation, transactionData);
        return operation;
    }

    TransactionData getTransactionData(Operation operation){
        var transaction = transactionRepository.getTransaction(operation.getOperationId());
        if (transaction.isEmpty()){
            logger.log("Incorrect transaction data");
            throw new InvalidDataException("Incorrect transaction data");
        }
        return transaction.get();
    }

    public boolean checkOperationCode(Operation confirmOperation, TransactionData transaction) {
        if (!transaction.getCode().equals(confirmOperation.getCode())) {
            logger.log("Wrong confirmation code");
            rollBackTransaction(transaction);
            throw new TransactionErrorException("Wrong confirmation code");
        }
        return true;
    }

    public void rollBackTransaction(TransactionData transaction){
        var cardFrom = transaction.getCardFrom();
        cardFrom.getBalance().accumulateAndGet(
                transaction.getValue() + transaction.getCommission(),
                (x, y) -> x + y);
    }
}
