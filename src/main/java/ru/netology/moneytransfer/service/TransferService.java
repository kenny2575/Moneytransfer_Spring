package ru.netology.moneytransfer.service;

import org.springframework.stereotype.Service;
import ru.netology.moneytransfer.exception.InvalidDataException;
import ru.netology.moneytransfer.logger.Logger;
import ru.netology.moneytransfer.model.card.Card;
import ru.netology.moneytransfer.model.operation.OperationId;
import ru.netology.moneytransfer.model.transaction.Amount;
import ru.netology.moneytransfer.model.transaction.Transaction;
import ru.netology.moneytransfer.model.transaction.TransactionData;
import ru.netology.moneytransfer.repository.CardsRepository;
import ru.netology.moneytransfer.repository.TransactionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
public class TransferService {
    private static Logger logger = Logger.getLogger();
    private final int COMMISSION_PERCENT = 1;
    private final String INITIAL_CODE = "0000";
    private CardsRepository cardsRepo;
    private TransactionRepository transactionRepo;

    public TransferService(CardsRepository cardsRepo, TransactionRepository transactionRepo){
        this.cardsRepo = cardsRepo;
        this.transactionRepo = transactionRepo;
    }
    Card getCard(String pan){
        Optional<Card> card = cardsRepo.getCardByPan(pan);

        if (card.isEmpty()){
            logger.log("Transaction failed! No active card with pan " + pan);
            throw new InvalidDataException("Transaction failed! No active card with pan " + pan);
        }
        return card.get();
    }
    public OperationId checkTransfer(Transaction transaction){

        logger.log("searching cards");

        var cardFrom = getCard(transaction.getCardFromNumber());
        logger.log("Card from " + cardFrom);
        if (cardFrom.validateCard(transaction.getCardFromNumber(), transaction.getCardFromCvv(), transaction.getCardFromValidTill())){
            logger.log("Card from validated!");
        }else{
            throw new InvalidDataException("Invalid card credentials " + cardFrom.getPan());
        }
        var cardTo = getCard(transaction.getCardToNumber());
        logger.log("Card to " + cardTo.getPan());

        logger.log("check and hold balance");
        checkAndHoldBalance(cardFrom, transaction.getAmount());

        return saveTransaction(cardFrom, cardTo, transaction.getAmount());
    }

    OperationId saveTransaction(Card cardFrom, Card cardTo, Amount amount){
        TransactionData transactionData = new TransactionData(cardFrom, cardTo, amount.getValue(), calcCommission(amount.getValue()), INITIAL_CODE);
        OperationId operationId = new OperationId();
        transactionRepo.saveTransaction(operationId.getOperationId(), transactionData);
        return operationId;
    }
    void checkAndHoldBalance(Card card, Amount amount){
        card.getBalance().accumulateAndGet(
                amount.getValue() + calcCommission(amount.getValue()),
                (x,y)->
                {
                    if (x-y >= 0){
                        return x-y;
                    }else{
                        logger.log("Insufficient Funds " + card.getPan());
                        throw new InvalidDataException("Insufficient Funds on card " + card.getPan());
                    }
                }
        );
    }

    int calcCommission(int amount){
        int commission = (amount * COMMISSION_PERCENT)/100;
        logger.log("full amount = " + commission);
        return commission;
    }
}
