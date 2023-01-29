package ru.netology.moneytransfer.model.operation;

public class Operation {
    private String operationId;
    private String code;

    public String getOperationId() {
        return operationId;
    }

    public String getCode() {
        return code;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "ConfirmOperation{" +
                "operationId=" + operationId +
                ", code=" + code +
                '}';
    }
}
