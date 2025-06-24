package managers;

import models.*;
import java.util.*;
import constants.*;

// Singleton
public class DeliveryManager {
    private List<DeliveryAgent> agents = new ArrayList<>();
    private static DeliveryManager instance = null;

    private DeliveryManager() {
        // private constructor
    }

    public static DeliveryManager getInstance() {
        if (instance == null) {
            instance = new DeliveryManager();
        }
        return instance;
    }

    public void addAgent(DeliveryAgent a) {
        agents.add(a);
    }

    public List<DeliveryAgent> getAgents(DeliveryAgent a) {
        return agents;
    }

    public void assignPartner(Order order){
        //get delivery agents from staregies. Here I am  just taking first available agent

        DeliveryAgent da =null;
        for(DeliveryAgent d: agents){
            if(d.isAvailable()){
                order.setStatus(OrderStatus.OUT_FOR_DELIVERY);
                da = d;
                da.performDelivery(order);
                return;
            }
        }

    }

  
}