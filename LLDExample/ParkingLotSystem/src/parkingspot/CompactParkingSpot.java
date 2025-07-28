package parkingspot;

import enums.VehicleType;

public class CompactParkingSpot extends ParkingSpot{
    public boolean canFitVehicle(VehicleType type){
        return VehicleType.CAR == type;
    }
}