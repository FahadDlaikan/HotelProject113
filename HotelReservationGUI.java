import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class HotelReservationGUI extends JFrame {
    private JTextField checkInField, checkOutField, customerNameField, customerPhoneField, capacityField;
    private JRadioButton regularRoomButton, suiteRoomButton;
    private JButton submitButton, saveButton, loadFileButton, newReservationButton;
    private JTextArea displayArea;
    private JFrame roomListFrame;
    private ButtonGroup roomTypeGroup;

    public HotelReservationGUI() {
        createAndShowGUI();
        
    }
   
    private void createAndShowGUI() {
        setTitle("Hotel Reservation System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);  // Ensure the window is resizable

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 5));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        customerNameField = new JTextField();
        customerPhoneField = new JTextField();
        capacityField = new JTextField();
        checkInField = new JTextField();
        checkOutField = new JTextField();
        
        regularRoomButton = new JRadioButton("Regular Room", true);
        suiteRoomButton = new JRadioButton("Suite");
        roomTypeGroup = new ButtonGroup();
        roomTypeGroup.add(regularRoomButton);
        
        roomTypeGroup.add(suiteRoomButton);
        
        submitButton = new JButton("Submit Reservation");
        saveButton = new JButton("Save Reservations to File");
        newReservationButton = new JButton("New Reservation");
       
        loadFileButton = new JButton("Save Reservation");
        loadFileButton = new JButton("Load Room Data File");
        JButton viewReservationButton = new JButton("View Reservation");
        
        inputPanel.add(new JLabel("Customer Name:"));
        inputPanel.add(customerNameField);
        inputPanel.add(new JLabel("Customer Phone:"));
        inputPanel.add(customerPhoneField);
        inputPanel.add(new JLabel("Capacity:"));
        inputPanel.add(capacityField);
        inputPanel.add(new JLabel("Check-In Date:"));
        inputPanel.add(checkInField);
        inputPanel.add(new JLabel("Check-Out Date:"));
        inputPanel.add(checkOutField);
        inputPanel.add(new JLabel("Room Type:"));
        inputPanel.add(regularRoomButton);
        inputPanel.add(new JLabel(""));
        inputPanel.add(suiteRoomButton);
        inputPanel.add(submitButton);
        inputPanel.add(saveButton);
        inputPanel.add(newReservationButton);
        inputPanel.add(viewReservationButton);
       
       
        inputPanel.add(loadFileButton);

        // Display Area
        displayArea = new JTextArea();
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Event Handlers
        submitButton.addActionListener(this::processReservation);
        newReservationButton.addActionListener(this::clearReservationForm);
        
        saveButton.addActionListener(e -> saveReservationToFile());
        loadFileButton.addActionListener(e -> loadRoomDataFile());
        loadFileButton.addActionListener(e -> saveReservationToFile());
        viewReservationButton.addActionListener(e -> loadAndDisplayReservation());

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    
    private void createRoomListFrame() {
        roomListFrame = new JFrame("Room List");
        roomListFrame.setSize(200, 400);  // Set the size of the frame
        roomListFrame.setLayout(new BorderLayout());  // Use BorderLayout for layout management
        JTextArea displayArea = new JTextArea();
        displayArea.setEditable(false); // Depending on whether you want the text to be editable
        JScrollPane scrollPane = new JScrollPane(displayArea);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        // Create a JTextArea to display room details
        JTextArea roomListArea = new JTextArea();
        roomListArea.setEditable(false);  // Make the text area non-editable

        // Add a JScrollPane containing the JTextArea to the frame
        JScrollPane scrollPane1 = new JScrollPane(roomListArea);
        roomListFrame.add(scrollPane, BorderLayout.CENTER);  // Add the scroll pane to the center of the frame

        // Position the frame relative to the main frame
        roomListFrame.setLocationRelativeTo(null);  // Center it on the screen
        roomListFrame.setLocation(getX() + getWidth(), getY());  // Adjust position to the right of the main frame
    }
    
    private void viewReservation() {
        JFrame viewFrame = new JFrame("View Reservation");
        viewFrame.setSize(300, 200);
        viewFrame.setLayout(new BorderLayout());
        JTextArea viewTextArea = new JTextArea();
        viewTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(viewTextArea);

        // Assuming you have a method to fetch reservation data as string:
        String reservationData = getReservationData();  // Implement this method to fetch data
        viewTextArea.setText(reservationData);
        
        viewFrame.add(scrollPane, BorderLayout.CENTER);
        viewFrame.setLocationRelativeTo(null); // Center on screen
        viewFrame.setVisible(true);
    }

    private String getReservationData() {
        // Example of fetching reservation data
        return "Reservation Details:\n" + "Name: " + customerNameField.getText() +
               "\nPhone: " + customerPhoneField.getText() +
               "\nRoom Type: " + (regularRoomButton.isSelected() ? "Regular Room" : "Suite") +
               "\nCapacity: " + capacityField.getText() +
               "\nCheck-In Date: " + checkInField.getText() +
               "\nCheck-Out Date: " + checkOutField.getText();
    }
    
   
    private void clearReservationForm(ActionEvent event) {
        // Clear all text fields and reset radio buttons
        customerNameField.setText("");
        customerPhoneField.setText("");
        capacityField.setText("");
        checkInField.setText("");
        checkOutField.setText("");
        regularRoomButton.setSelected(true);
        displayArea.setText("");
    }

    private void processReservation(ActionEvent event) {
        try {
            String name = customerNameField.getText();
            String phone = customerPhoneField.getText();
            int capacity = Integer.parseInt(capacityField.getText());
            if (capacity < 0)
            	throw new IllegalArgumentException("Capacity cannot be less than zero.");
            String roomType = regularRoomButton.isSelected() ? "Regular Room" : "Suite";
            String checkIn = checkInField.getText();
            String checkOut = checkOutField.getText();
            validateDates(checkIn, checkOut);
            displayArea.setText(String.format("Reservation processed for %s, %s. Phone: %s, Capacity: %d, Dates: %s to %s",
                                             name, roomType, phone, capacity, checkIn, checkOut));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for capacity.", "Input Error", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException | InvalidDateException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void validateDates(String checkIn, String checkOut) throws InvalidDateException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date checkInDate = sdf.parse(checkIn);
            Date checkOutDate = sdf.parse(checkOut);
            if (checkOutDate.before(checkInDate)) {
                throw new InvalidDateException("Check-out date cannot be before check-in date.");
            }
        } catch (Exception e) {
            throw new InvalidDateException("Invalid date format. Please use YYYY-MM-DD.");
        }
    }

    private void saveReservations(ActionEvent event) {
        displayArea.setText("Reservations saved to file.");
    }

    
    private void loadAndDisplayReservation() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Open Reservation File");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(fileToLoad)) {
                StringBuilder fileContent = new StringBuilder();
                while (scanner.hasNextLine()) {
                    fileContent.append(scanner.nextLine()).append("\n");
                }
                displayArea.setText(fileContent.toString());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Failed to read the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
   
    private void loadRoomDataFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Select Room Data File");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            try (Scanner scanner = new Scanner(fileToLoad)) {
                StringBuilder roomData = new StringBuilder();
                while (scanner.hasNextLine()) {
                    roomData.append(scanner.nextLine()).append("\n");
                }
                displayArea.setText(roomData.toString());
            } catch (FileNotFoundException e) {
                JOptionPane.showMessageDialog(this, "Failed to read the file.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    
    
    private void saveReservationToFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Reservation");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setApproveButtonText("Save");

        int userSelection = fileChooser.showSaveDialog(this);
        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".txt")) {
                fileToSave = new File(fileToSave.toString() + ".txt");  // Ensure the file has a .txt extension
            }

            try (PrintWriter out = new PrintWriter(fileToSave)) {
                out.println("Customer Name: " + customerNameField.getText());
                out.println("Customer Phone: " + customerPhoneField.getText());
                out.println("Room Type: " + (regularRoomButton.isSelected() ? "Regular Room" : "Suite"));
                out.println("Capacity: " + capacityField.getText());
                out.println("Check-In Date: " + checkInField.getText());
                out.println("Check-Out Date: " + checkOutField.getText());
                JOptionPane.showMessageDialog(this, "Reservation saved to " + fileToSave.getPath(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error saving reservation: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private String chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
        fileChooser.setDialogTitle("Select a Room Data File");
        int result = fileChooser.showOpenDialog(this);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return null;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(HotelReservationGUI::new);
    }

    
}
