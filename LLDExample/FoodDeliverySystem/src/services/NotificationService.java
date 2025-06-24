package services;

import java.util.List;
import models.*;

public class NotificationService {
    public static void notify(int id, String message) {
        System.out.println("Notification for " + id + ": " + (message));
        System.out.println("---------------------------------------------");
    }
}
