package ru.netology.moneytransfer.repository;

import org.springframework.stereotype.Repository;
import ru.netology.moneytransfer.model.card.Card;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class CardsRepository {
    private static final int BALANCE = 100_000_00;
    private ConcurrentHashMap<String, Card> cardRepo = new ConcurrentHashMap<>();

    public CardsRepository(){
        Card card = new Card("4276441544164417", "675", "03/23", BALANCE);
        cardRepo.put(card.getPan(), card);
        card = new Card("4276441544144413", "879", "05/23", BALANCE);
        cardRepo.put(card.getPan(), card);
    }

    public Optional<Card> getCardByPan(String pan){
        return Optional.ofNullable(cardRepo.getOrDefault(pan, null));
    }
}
