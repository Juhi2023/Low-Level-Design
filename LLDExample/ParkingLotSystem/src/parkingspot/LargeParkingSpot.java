package parkingspot;

import enums.VehicleType;

public class LargeParkingSpot extends ParkingSpot{
    public boolean canFitVehicle(VehicleType type){
        return VehicleType.TRUCK == type;
    }
}