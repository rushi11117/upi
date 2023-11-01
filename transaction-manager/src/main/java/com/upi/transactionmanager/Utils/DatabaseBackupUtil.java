package com.upi.transactionmanager.Utils;

import com.upi.transactionmanager.Entity.TransactionBuffer;
import com.upi.transactionmanager.Entity.Transactions;
import com.upi.transactionmanager.Repositories.TransactionBufferRepository;
import com.upi.transactionmanager.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * The type Database backup util.
 */
@Component
public class DatabaseBackupUtil {

    @Autowired
    private TransactionBufferRepository transactionBufferRepository;


    @Autowired
    private TransactionRepository transactionRepository;

    /**
     * Instantiates a new Database backup util.
     *
     * @param transactionBufferRepository the transaction buffer repository
     * @param transactionRepository       the transaction repository
     */
    public DatabaseBackupUtil(TransactionBufferRepository transactionBufferRepository, TransactionRepository transactionRepository) {
        this.transactionBufferRepository = transactionBufferRepository;
        this.transactionRepository = transactionRepository;
    }

    /**
     * Move data to permanent database.
     */

    @Scheduled(cron = "0 0 0 * * *")
    public void moveDataToPermanentDatabase() {
        List<TransactionBuffer> tmpData = transactionBufferRepository.findAll();

        for (TransactionBuffer transactionBuffer : tmpData) {
            Transactions transactions = new Transactions();
            transactions.setId(transactionBuffer.getId());
            transactions.setSenderUpiId(transactionBuffer.getSenderUpiId());
            transactions.setReciverUpiId(transactionBuffer.getReciverUpiId());
            transactions.setAmmount(transactionBuffer.getAmmount());
            transactions.setTransactionTime(transactionBuffer.getTransactionTime());
            transactions.setTransactionCompletion(transactionBuffer.getTransactionCompletion());
            transactions.setTransactionStatus(transactionBuffer.getTransactionStatus());

            transactionRepository.save(transactions);
        }
    }
}
