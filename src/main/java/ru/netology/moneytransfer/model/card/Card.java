package ru.netology.moneytransfer.model.card;

import ru.netology.moneytransfer.logger.Logger;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class Card {
    private String pan;
    private String cvv;
    private String validTo;
    private AtomicInteger balance;
    private Logger logger = Logger.getLogger();

    public Card(String pan, String cvv, String validTo, int balance) {
        this.pan = pan;
        this.cvv = cvv;
        this.validTo = validTo;
        this.balance = new AtomicInteger(balance);
    }

    public String getPan() {
        return pan;
    }

    public String getCvv() {
        return cvv;
    }

    public String getValidTo() {
        return validTo;
    }

    public AtomicInteger getBalance() {
        return balance;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardNumber='" + pan + '\'' +
                ", balance=" + balance +
                '}';
    }
    public boolean validateCard(String pan, String cvv, String validTo){
        Card validCard = new Card(pan, cvv, validTo, 0);
        return validCard.equals(this);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(pan, card.pan) && Objects.equals(cvv, card.cvv) && Objects.equals(validTo, card.validTo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pan, cvv, validTo);
    }
}
