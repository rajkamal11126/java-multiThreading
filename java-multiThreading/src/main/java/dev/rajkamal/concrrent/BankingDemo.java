package dev.rajkamal.concrrent;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.ReentrantLock;

public class BankingDemo {
    private final String userName;
    private final AtomicReference<BigDecimal> balance;
    private final ReentrantLock lock;
    private final long waitTime;
    private boolean locked;
    public BankingDemo(String userName,ReentrantLock lock,long waitTime){
        balance = new AtomicReference<>(BigDecimal.TEN.add(BigDecimal.TEN).add(BigDecimal.TEN));
        this.lock = lock;
        this.waitTime = waitTime;
        locked = false;
        this.userName = userName;
    }
    public boolean credit(BigDecimal amount){
        try{
            locked = lock.tryLock(waitTime, TimeUnit.MILLISECONDS);
            if(locked){
                if(amount.compareTo(amount) < 0)
                    return false;
                this.balance.updateAndGet(bal->bal.subtract(amount));
            }
        }catch (Exception e){
            e.printStackTrace();
                return false;
        }finally{
            if(locked)
                lock.unlock();
        }
        return true;
    }
    public boolean debit(BigDecimal amount){
        try{
            locked = lock.tryLock(waitTime, TimeUnit.MILLISECONDS);
            if(locked){
                this.balance.updateAndGet(bal->bal.add(amount));
            }
        }catch (Exception e){
            e.printStackTrace();
                return false;
        }finally{
            if(locked)
                lock.unlock();
        }
        return true;
    }

    public BigDecimal getAmount(){
        return balance.get();
    }
}
