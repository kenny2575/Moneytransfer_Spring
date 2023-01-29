package ru.netology.moneytransfer.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransfer.logger.Logger;
import ru.netology.moneytransfer.model.operation.Operation;
import ru.netology.moneytransfer.model.operation.OperationId;
import ru.netology.moneytransfer.model.transaction.Transaction;
import ru.netology.moneytransfer.service.ConfirmService;
import ru.netology.moneytransfer.service.TransferService;

@RestController
@RequestMapping("/")
public class TransferController {
    private static Logger logger = Logger.getLogger();
    private TransferService transferService;
    private ConfirmService confirmService;

    public TransferController(TransferService transferService, ConfirmService confirmService){
        this.transferService = transferService;
        this.confirmService = confirmService;
    }

    @CrossOrigin
    @PostMapping("transfer")
    public ResponseEntity<OperationId> getResult(@RequestBody Transaction body){
        logger.log("Call transfer mapping");
        logger.log(body.toString());
        return ResponseEntity.ok(transferService.checkTransfer(body));
    }

    @CrossOrigin
    @PostMapping("confirmOperation")
    public ResponseEntity<Operation> getConfirm(@RequestBody Operation body){
        logger.log("Call confirm mapping");
        logger.log(body.toString());
        return ResponseEntity.ok(confirmService.checkOperation(body));
    }
}
