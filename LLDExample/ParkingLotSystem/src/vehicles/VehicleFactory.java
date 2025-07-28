package vehicles;

import enums.VehicleType;

public class VehicleFactory{
    public static Vehicle createVehicle(VehicleType type, String lincenseNumber){
        switch(type){
            case VehicleType.BIKE: return new Bike(lincenseNumber);
            case VehicleType.CAR: return new Car(lincenseNumber);
            case VehicleType.TRUCK: return new Truck(lincenseNumber);
            default: return null;
        }
    }
}