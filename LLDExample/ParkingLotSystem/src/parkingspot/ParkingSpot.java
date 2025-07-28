package parkingspot;

import vehicles.Vehicle;
import model.*;
import enums.*;

public abstract class ParkingSpot{
    private static int nextId=0;
    private String spotId;
    private boolean isOccupied;
    private Vehicle vehicle;

    public ParkingSpot (){
        this.spotId = nextId++ + "Spot";
        this.isOccupied = false;
    }

    public boolean isAvailable(){
        return !isOccupied;
    }

    public synchronized boolean assignVehicle(Vehicle vehicle) {
        if (isOccupied) {
            return false;
        }
        this.vehicle = vehicle;
        isOccupied = true;
        return true;
    }

    public synchronized void removeVehicle() {
        vehicle = null;
        isOccupied = false;
    }
    
    public Vehicle getVehicle() {
        return vehicle;
    } 

    public String getId(){
        return spotId;
    }

    public abstract boolean canFitVehicle(VehicleType type);
}