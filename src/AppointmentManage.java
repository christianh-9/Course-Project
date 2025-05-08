import java.io.FileWriter;
import java.io.IOException;

// Interface to save appointment and throw error if an issue is present
interface AppointmentAction {
    void saveAppointment() throws IOException;
}
// Interface for notification sent to user
interface Notification {
    void sendNotification(String customerName, String appointmentDetails);
}
// Private class members
public class AppointmentManage implements AppointmentAction{
    private String customerName;
    private String petName;
    private String services;
    private double price;
    private int appointmentHour;

    // Class constructor to set all arguments to private memebers
    public AppointmentManage(String customerName, String petName, String services, double price, int appointmentHour) {
        this.customerName = customerName;
        this.petName = petName;
        this.services = services;
        this.price = price;
        this.appointmentHour = appointmentHour;
    }

    // To send all information to apt.txt file and call EmailNotification method to mimic an email confirmation
    public void saveAppointment() throws IOException {
        FileWriter writer = new FileWriter("apt.txt", true);
        writer.write("Owner: " + customerName + '\n');
        writer.write("Pet name: " + petName + '\n');
        writer.write("Services: " + services + '\n');
        writer.write("Price: $" + price + '\n');
        writer.write("Appointment time: " + timeFormat(appointmentHour) + '\n');
        writer.write("======================\n");
        writer.flush();

        // Call method for email confirmation
        EmailNotification emailNotification = new EmailNotification();
        emailNotification.sendNotification(customerName, getDetails());
    }

    // Method to output the information to the user
    public String getDetails () {
        return "Appointment scheduled for " + petName + " under " + customerName  +
                "\nServices: " + services + "\nTotal Price: $" + price + "\nAppointment time: " + timeFormat(appointmentHour) + '\n';
    }
    // Method to differentiate time from AM to PM
    private String timeFormat(int hour) {
        return hour + ":00" + (hour < 12 ? " AM" : " PM");
    }
    // Method to send user an email confirmation
    class EmailNotification implements Notification {
        @Override
        public void sendNotification(String customerName, String appointmentDetails) {
            System.out.println("Sending email to " + customerName + ": " + appointmentDetails);
        }
    }
}








