package state;

import controller.ATM;
import enums.OperationType;
import models.*;

public interface IATMState{
    void insertCard(ATM machine, String cardNo);
    void enterPin(ATM machine, String pin);
    void selectOption(ATM machine, OperationType type, int amount);
    void ejectCard(ATM machine);
}