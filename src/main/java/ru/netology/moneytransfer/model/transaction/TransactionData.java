package ru.netology.moneytransfer.model.transaction;

import ru.netology.moneytransfer.model.card.Card;

import java.util.Objects;

public class TransactionData {
    private Card cardFrom;
    private Card cardTo;
    private int value;
    private int commission;
    private String code;

    public TransactionData(Card cardFrom, Card cardTo, int value, int commission, String code) {
        this.cardFrom = cardFrom;
        this.cardTo = cardTo;
        this.value = value;
        this.commission = commission;
        this.code = code;
    }

    public Card getCardFrom() {
        return cardFrom;
    }

    public void setCardFrom(Card cardFrom) {
        this.cardFrom = cardFrom;
    }

    public Card getCardTo() {
        return cardTo;
    }

    public void setCardTo(Card cardTo) {
        this.cardTo = cardTo;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) {
        this.commission = commission;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionData that = (TransactionData) o;
        return getValue() == that.getValue() && getCommission() == that.getCommission() && Objects.equals(getCardFrom(), that.getCardFrom()) && Objects.equals(getCardTo(), that.getCardTo()) && Objects.equals(getCode(), that.getCode());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCardFrom(), getCardTo(), getValue(), getCommission(), getCode());
    }
}
