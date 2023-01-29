package ru.netology.moneytransfer.model.transaction;

public class Amount {
    private String currency;
    private int value;

    public Amount(String currency, int value) {
        this.currency = currency;
        this.value = value;
    }
    public String getCurrency() {
        return currency;
    }

    public int getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "Amount{" +
                "currency='" + currency + '\'' +
                ", value=" + value +
                '}';
    }
}
