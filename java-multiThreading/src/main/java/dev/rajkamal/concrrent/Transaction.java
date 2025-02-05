package dev.rajkamal.concrrent;

import java.math.BigDecimal;

public class Transaction implements Runnable{
    private final BankingDemo accont_holder;
    private final BankingDemo receiver;

    private final BigDecimal transactAmount;
    private final boolean transactionType;

    public Transaction(BankingDemo accont_holder,BankingDemo receiver,
                       BigDecimal transactAmount,boolean transactionType){
        this.accont_holder = accont_holder;
        this.receiver = receiver;
        this.transactionType=transactionType;
        this.transactAmount = transactAmount;
    }
    @Override
    public void run() {
        if(transactionType){
            if(accont_holder.credit(transactAmount)){
                receiver.debit(transactAmount);
            }
        }else{
            if(receiver.credit(transactAmount)){
                accont_holder.debit(transactAmount);
            }
        }
    }
}
