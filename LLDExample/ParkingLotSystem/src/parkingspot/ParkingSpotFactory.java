package parkingspot;

import enums.ParkingSpotType;

public class ParkingSpotFactory{
    public static ParkingSpot createSpot(ParkingSpotType type){
        switch(type){
            case ParkingSpotType.BIKE: return new BikeParkingSpot();
            case ParkingSpotType.COMPACT: return new CompactParkingSpot();
            case ParkingSpotType.LARGE: return new LargeParkingSpot();
            default: return null;
        }
    }
}