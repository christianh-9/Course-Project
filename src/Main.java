import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main extends JFrame {
    public static void main(String[] args) {
        // New frame
        JFrame frame = new JFrame();
        frame.setTitle("Set Appointment for ONLY today"); //Create tab title for UI
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 425); // Size
        frame.setLocationRelativeTo(null);

        // Main title for program at the top
        JLabel titleLabel = new JLabel("Christian's Dog Grooming Services", SwingConstants.CENTER);
        titleLabel.setFont(new java.awt.Font("", Font.BOLD, 24));
        frame.add(titleLabel, BorderLayout.NORTH);

        // Set layout and organize the text boxes
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Labeling text fields for name and pet
        JLabel nameLabel = new JLabel("Your Name:");
        JTextField nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(500,30));

        JLabel petName = new JLabel("Pet Name:");
        JTextField petField = new JTextField();
        petField.setMaximumSize(new Dimension(500,30));

        // Add labels and text boxes to panel
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(petName);
        panel.add(petField);

        // Labeling for services text boxes
        JLabel serviceInstructionLabel = new JLabel("Please select the services needed for your dog:");
        panel.add(serviceInstructionLabel);

        // Check boxes for each of the services
        JCheckBox bathingCheckBox = new JCheckBox("Bathing");
        JCheckBox nailsCheckBox = new JCheckBox("Nails");
        JCheckBox earCheckBox = new JCheckBox("Ear");

        // Add check boxes to panel
        panel.add(bathingCheckBox);
        panel.add(nailsCheckBox);
        panel.add(earCheckBox);

        // Label for appointment times and array to store all available slots
        JLabel timeLabel = new JLabel("Select Appointment Time:");
        String[] availableTimes = {"8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM", "12:00 PM", "1:00 PM", "2:00 PM", "3:00 PM", "4:00 PM", "5:00 PM", "6:00 PM"};
        JComboBox<String> timeDropdown = new JComboBox<>(availableTimes);
        // Add time and drop down to panel
        panel.add(timeLabel);
        panel.add(timeDropdown);

        // Add submit button for when finished with filling out appointment info
        JButton submitButton = new JButton("Submit");
        panel.add(submitButton);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Initialize text boxes for name and pet name
                String name = nameField.getText();
                String petName = petField.getText();

                String services = ""; //Initialize empty services
                double totalPrice = 0.0;
                // If statements for each service selection
                if (bathingCheckBox.isSelected()) {
                    services += "Bathing, ";
                    totalPrice += 25.0; // Add to price if selected
                }
                if (nailsCheckBox.isSelected()) {
                    services += "Nail trimming, ";
                    totalPrice += 15.0;
                }
                if (earCheckBox.isSelected()) {
                    services += "Ear cleaning, ";
                    totalPrice += 10.0;
                }
                // Switch statement if no selection or if services ends with a comma
                switch (services) {
                    case "":
                        services = "No services were selected";
                        break;
                    default:
                        if (services.endsWith(", ")) {
                            services = services.substring(0, services.length() - 2);
                        }
                        break;
                }

                // Drop down menu for times
                String selectedTime = (String) timeDropdown.getSelectedItem();
                int aptHour = Integer.parseInt(selectedTime.split(":")[0]);

                // Time conversions from 24 hour to 12 hour
                if (selectedTime.contains("PM") && aptHour != 12) {
                    aptHour += 12; // Convert any times with PM to 24-hour clock
                } else if (selectedTime.contains("AM") && aptHour == 12) {
                    aptHour = 0; // Convert midnight to 0 o
                }

                // Call appointmentmanage method to set arguments
                AppointmentManage appointment = new AppointmentManage(name, petName, services, totalPrice, aptHour);
                // Try catch function if error occurs for saving to .txt file
                try {
                    appointment.saveAppointment();
                } catch (IOException ea) {
                    System.out.println("Error saving appointment");
                }
                // Output message for after submitting appointment
                JOptionPane.showMessageDialog(frame, appointment.getDetails(), "Appointment Details", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        // For layout and allowing UI to be visible
        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }
}



