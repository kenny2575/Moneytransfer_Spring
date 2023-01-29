package ru.netology.moneytransfer.model.transaction;

public class Transaction {
    private String cardFromNumber;
    private String cardToNumber;
    private String cardFromCVV;
    private String cardFromValidTill;
    private Amount amount;

    public Transaction(String cardFromNumber, String cardToNumber, String cardFromCVV, String cardFromValidTill, Amount amount) {
        this.cardFromNumber = cardFromNumber;
        this.cardToNumber = cardToNumber;
        this.cardFromCVV = cardFromCVV;
        this.cardFromValidTill = cardFromValidTill;
        this.amount = amount;
    }

    public String getCardFromNumber() {
        return cardFromNumber;
    }

    public String getCardToNumber() {
        return cardToNumber;
    }

    public String getCardFromCvv() {
        return cardFromCVV;
    }

    public String getCardFromValidTill() {
        return cardFromValidTill;
    }

    public Amount getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "cardFromNumber='" + cardFromNumber + '\'' +
                ", cardToNumber='" + cardToNumber + '\'' +
                ", cardFromCvv='" + cardFromCVV + '\'' +
                ", cardFromValidTill='" + cardFromValidTill + '\'' +
                ", "+amount.toString() + '\''+
                '}';
    }
}
