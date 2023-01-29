package ru.netology.moneytransfer;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.netology.moneytransfer.model.card.Card;
import ru.netology.moneytransfer.repository.CardsRepository;
import ru.netology.moneytransfer.repository.TransactionRepository;
import ru.netology.moneytransfer.service.ConfirmService;

import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MoneyTransferApplicationTests {

    public CardsRepository cards;
    public TransactionRepository transactions;

    public ConfirmService confirmService;

    @Before
    void prepare() {

    }

    @Test
    void emptyCardTest() {
        cards = new CardsRepository();
        Optional<Card> card = cards.getCardByPan("4276441544144411");
        Assertions.assertNull(card.orElse(null));
    }

    @Test
    void findCardTest() {
        cards = new CardsRepository();
        Optional<Card> card = cards.getCardByPan("4276441544144413");
        Assertions.assertNotNull(card.orElse(null));
    }

    @Test
    void emptyRepositoryTest() {
        transactions = new TransactionRepository();
        Assertions.assertNull(transactions.getTransaction("99438").orElse(null));
    }
}