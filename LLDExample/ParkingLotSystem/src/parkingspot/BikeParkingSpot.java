package parkingspot;

import enums.VehicleType;

public class BikeParkingSpot extends ParkingSpot{
    public boolean canFitVehicle(VehicleType type){
        return VehicleType.BIKE == type;
    }
}