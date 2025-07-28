import java.util.*;
import model.*;
import parkingspot.*;
import fee.*;
import vehicles.*;

public class ParkingLotSystem{
    private static ParkingLotSystem instance;
    private List<ParkingFloor> parkingfloors;
    private final Map<String, ParkingTicket> activeTickets;
    private FeeStrategy strategy;


    private ParkingLotSystem(){
        parkingfloors = new ArrayList<>();
        activeTickets = new HashMap<>();
    }

    public static ParkingLotSystem getInstance(){
        if(instance==null){
            instance = new ParkingLotSystem();
        }
        return instance;
    }

    public void setFeeStrategy(FeeStrategy s){
        strategy =s;
    }

    public void addNewFloor(ParkingFloor floor){
        parkingfloors.add(floor);
    }

    public void displaySpots(){
        for(ParkingFloor f: parkingfloors){
            for(ParkingSpot s: f.getSpots()){
                System.out.print(" ___ ");
            }
            System.out.println();

            for(ParkingSpot s: f.getSpots()){
                System.out.print(s.isAvailable() ? "|   |" : "| F |");
            }
            System.out.println();

            for(ParkingSpot s: f.getSpots()){
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("------------------------------------------------------------");
    }

    public ParkingTicket parkVehicle(Vehicle vehicle){
        for(ParkingFloor f: parkingfloors){
            ParkingSpot s = f.getAvailableSpot(vehicle);
            if(s!=null){
                s.assignVehicle(vehicle);
                ParkingTicket ticket = new ParkingTicket(vehicle, s);
                activeTickets.put(vehicle.getLicenseNumber(), ticket);
                return ticket;
            }
        }
        System.out.println("Spot not available.");
        return null;
    }

    public double unparkVehicle(ParkingTicket ticket){
        String licenseNo = ticket.getVehicle().getLicenseNumber();
        ticket.getSpot().removeVehicle();
        ticket.setExitTimestamp();

        return strategy.calculateFee(ticket);
    }
}