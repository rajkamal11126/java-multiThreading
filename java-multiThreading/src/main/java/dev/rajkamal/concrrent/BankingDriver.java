package dev.rajkamal.concrrent;

import java.math.BigDecimal;
import java.util.concurrent.locks.ReentrantLock;

public class BankingDriver {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        BankingDemo mahesh = new BankingDemo("mahesh",lock,1000);

        BankingDemo himanshu = new BankingDemo("himanshu",lock,1000);
        Thread th1 = new Thread(new Transaction(mahesh,himanshu, BigDecimal.valueOf(10),true));

        Thread th2 = new Thread(new Transaction(mahesh,himanshu, BigDecimal.valueOf(5),false));

        System.out.println(mahesh.getAmount());
        System.out.println(himanshu.getAmount());

        th1.start();
        th2.start();

        th1.join();
        th2.join();

        System.out.println("after the transaction got completed");

        System.out.println(mahesh.getAmount());
        System.out.println(himanshu.getAmount());
    }
}
