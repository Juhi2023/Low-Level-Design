package fee;

import model.*;
import enums.VehicleType;
import java.util.*;

public class VehicleBasedFeeStrategy implements FeeStrategy {

    private Map<VehicleType, Double> hourlyRates;

    public VehicleBasedFeeStrategy(){
        hourlyRates = new HashMap<>();
        hourlyRates.put(VehicleType.BIKE, 10.0);
        hourlyRates.put(VehicleType.CAR, 20.0);
        hourlyRates.put(VehicleType.TRUCK, 30.0);
    }

    @Override
    public double calculateFee(ParkingTicket parkingTicket) {
        VehicleType type = parkingTicket.getVehicle().getType();
        long duration = parkingTicket.getExitTimestamp() - parkingTicket.getEntryTimestamp();
        long hours = (duration / (1000 * 60 * 60)) + 1;
        return hours * hourlyRates.get(type);
    }
}