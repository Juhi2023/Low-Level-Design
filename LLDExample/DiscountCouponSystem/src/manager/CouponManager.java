package manager;

import models.*;
import coupon.*;
import java.util.concurrent.locks.*;
import java.util.*;

public class CouponManager{
    Coupon head=null;
    private static CouponManager instance;
    Lock lock = new ReentrantLock();

    private CouponManager(){

    }

    public synchronized static CouponManager getInstance(){
        if(instance==null){
            instance = new CouponManager();
        }
        return instance;
    }

    public void addCoupon(Coupon c){
        lock.lock();
        try{
            if(head == null){
                head=c;
                return;
            }

            Coupon curr = head;
            while(curr.getNext()!=null){
                curr = curr.getNext();
            }
            curr.setNext(c);
        }finally{
            lock.unlock();
        }
    }

    public List<String> getApplicableCoupons(Cart c){
        List<String> res = new ArrayList<>();
        Coupon curr = head;
        while(curr!=null){
            if(curr.isApplicable(c)){
                res.add(curr.getName());
            }
            curr = curr.getNext();
        }
        return res;
    }

    public double applyAll(Cart cart) {
        lock.lock();
        try {
            if (head != null) {
                head.applyCoupon(cart);
            }
            return cart.getCurrentTotal();
        } finally {
            lock.unlock();
        }
    }
}