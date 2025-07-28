import java.util.*;
import parkingspot.*;
import model.*;
import enums.*;
import fee.*;
import vehicles.*;

class Main{
    public static void main(String args[]){
        ParkingLotSystem  machine = ParkingLotSystem.getInstance();

        machine.setFeeStrategy(new VehicleBasedFeeStrategy());
        
        List<ParkingSpot> parkingSpotsFloor1 = new ArrayList<>();
        parkingSpotsFloor1.add(ParkingSpotFactory.createSpot(ParkingSpotType.BIKE));
        parkingSpotsFloor1.add(ParkingSpotFactory.createSpot(ParkingSpotType.COMPACT));
        parkingSpotsFloor1.add(ParkingSpotFactory.createSpot(ParkingSpotType.LARGE));

        List<ParkingSpot> parkingSpotsFloor2 = new ArrayList<>();
        parkingSpotsFloor2.add(ParkingSpotFactory.createSpot(ParkingSpotType.BIKE));
        parkingSpotsFloor2.add(ParkingSpotFactory.createSpot(ParkingSpotType.LARGE));

        ParkingFloor floor1 = new ParkingFloor(1, parkingSpotsFloor1);
        ParkingFloor floor2 = new ParkingFloor(2, parkingSpotsFloor2);

        machine.addNewFloor(floor1);
        machine.addNewFloor(floor2);

        machine.displaySpots();

        ParkingTicket ticket = machine.parkVehicle(new Bike("DJCN@42"));

        machine.displaySpots();

        System.out.println("Parking cost: " + machine.unparkVehicle(ticket));

        machine.displaySpots();
    }

 
}