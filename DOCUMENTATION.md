# Dog Grooming Appointment Program  

## How It Works  
1. The user enters **name** and **pet's name**.  
2. They choose **services** (Bathing, Nail Trimming, Ear Cleaning).  
3. They choose an **available time** from drop-down menu  
4. Click **"Submit"**:  
   - The appointment will be sent to `apt.txt`.  
   - A confirmation message will be displayed.  
   - A simulated email confirmation will be outputted.  

## File Storage (`apt.txt`)  
Each appointment is saved as:  

**Owner: Christian  
Pet name: Zoey  
Services: Bathing, Nail Trimming  
Price: $40.00  
Appointment time: 12:00 PM  
======================  **

## Program Structure  
### 1. `Main.java`
- Creates UI with Swing functions (JFrame, JLabel, JTextField, JComboBox, JCheckBox, JButton).  
- Collects user input and sends it to `AppointmentManage.java`.   

### 2. `AppointmentManage.java`  
- Saves appointments to `apt.txt`.  
- Simulates an email confirmation.

## Improvements to work on  
- Allow users to schedule future appointments    
- Implement a real email notification system  
- Use a database instead of a .txt file   





