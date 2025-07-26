
import controller.ATM;
import enums.OperationType;

public class Main {
    public static void main(String args[]) {
        ATM atm = ATM.getInstance();

        // Perform Check Balance operation
        atm.insertCard("0CARD");
        atm.enterPin("1234");
        atm.selectOperation(OperationType.CHECK_BALANCE, 0); // $1000

        // Perform Withdraw Cash operation
        atm.insertCard("0CARD");
        atm.enterPin("1234");
        atm.selectOperation(OperationType.WITHDRAW_CASH, 570);

        // Perform Deposit Cash operation
        atm.insertCard("0CARD");
        atm.enterPin("1234");
        atm.selectOperation(OperationType.DEPOSIT_CASH, 200);

        // Perform Check Balance operation
        atm.insertCard("0CARD");
        atm.enterPin("1234");
        atm.selectOperation(OperationType.CHECK_BALANCE, 0); // $630

        // Perform Withdraw Cash more than balance
        atm.insertCard("0CARD");
        atm.enterPin("1234");
        atm.selectOperation(OperationType.WITHDRAW_CASH, 700); // Insufficient balance

        // Insert Incorrect PIN
        atm.insertCard("0CARD");
        atm.enterPin("3425");
    }
}