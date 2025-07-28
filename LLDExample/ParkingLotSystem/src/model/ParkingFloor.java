package model;

import java.util.*;
import parkingspot.*;
import vehicles.*;

public class ParkingFloor{
    private int floorNumber;
    private List<ParkingSpot> spots;

    public ParkingFloor(int number, List<ParkingSpot> spots){
        this.floorNumber = number;
        this.spots = spots;
    }

    public int getFloorNumber(){
        return floorNumber;
    }

    public List<ParkingSpot> getSpots(){
        return spots;
    }

    public ParkingSpot getAvailableSpot(Vehicle vehicle){
        for(ParkingSpot s: spots){
            if(s.isAvailable() && s.canFitVehicle(vehicle.getType())){
                return s;
            }
        }
        return null;
    }
    
}