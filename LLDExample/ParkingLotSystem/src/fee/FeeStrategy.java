package fee;
import model.*;

public interface FeeStrategy {
    double calculateFee(ParkingTicket parkingTicket);
}