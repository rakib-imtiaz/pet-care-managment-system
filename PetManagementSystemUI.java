import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import java.io.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import javax.swing.filechooser.FileNameExtensionFilter;

public class PetManagementSystemUI extends JFrame {
    private JTable petTable;
    private JButton addButton, editButton, deleteButton, saveButton;
    private JTextField nameField, breedField, ageField;
    private JComboBox<String> typeComboBox;
    private List<Animal> petList = new ArrayList<>();
    private JLabel imageLabel;
    private JButton uploadImageButton;
    private final int IMAGE_WIDTH = 150;
    private final int IMAGE_HEIGHT = 150;

    public PetManagementSystemUI() {
        setTitle("Pet Care Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set gradient background panel
        setContentPane(new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint gradient = new GradientPaint(0, 0, new Color(240, 248, 255),
                        0, getHeight(), new Color(176, 224, 230));
                g2d.setPaint(gradient);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        });

        // Layout setup with margins
        setLayout(new BorderLayout(20, 20));
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Enhance table appearance
        String[] columnNames = { "Pet ID", "Name", "Breed", "Age", "Type" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        petTable = new JTable(tableModel);
        petTable.setRowHeight(35);
        petTable.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        petTable.setSelectionBackground(new Color(135, 206, 250));
        petTable.setGridColor(new Color(176, 224, 230));
        petTable.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        petTable.getTableHeader().setBackground(new Color(135, 206, 250));
        petTable.getTableHeader().setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(petTable);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 2));
        add(scrollPane, BorderLayout.CENTER);

        // Enhanced form panel
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setOpaque(false);
        formPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
                        "Enter Pet Details",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 16),
                        new Color(70, 130, 180)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Style form components
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);

        JLabel[] labels = {
                new JLabel("Name:", SwingConstants.RIGHT),
                new JLabel("Breed:", SwingConstants.RIGHT),
                new JLabel("Age:", SwingConstants.RIGHT),
                new JLabel("Type:", SwingConstants.RIGHT)
        };

        for (JLabel label : labels) {
            label.setFont(labelFont);
            label.setForeground(new Color(70, 130, 180));
        }

        // Add labels and fields to form panel
        formPanel.add(labels[0]);
        formPanel.add(nameField = new JTextField(20));
        nameField.setFont(inputFont);

        formPanel.add(labels[1]);
        formPanel.add(breedField = new JTextField(20));
        breedField.setFont(inputFont);

        formPanel.add(labels[2]);
        formPanel.add(ageField = new JTextField(20));
        ageField.setFont(inputFont);

        formPanel.add(labels[3]);
        typeComboBox = new JComboBox<>(new String[] {
            "Dog", "Cat", "Rabbit", "Hamster", "Bird", "Fish",
            "Guinea Pig", "Turtle", "Ferret", "Parrot"
        });
        typeComboBox.setFont(inputFont);
        formPanel.add(typeComboBox);

        add(formPanel, BorderLayout.NORTH);

        // Create image panel
        JPanel imagePanel = new JPanel(new BorderLayout(5, 5));
        imagePanel.setOpaque(false);
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(
                        BorderFactory.createLineBorder(new Color(135, 206, 250), 2),
                        "Pet Profile Picture",
                        TitledBorder.LEFT,
                        TitledBorder.TOP,
                        new Font("Segoe UI", Font.BOLD, 16),
                        new Color(70, 130, 180)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        // Initialize image label with default image
        imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(IMAGE_WIDTH, IMAGE_HEIGHT));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(135, 206, 250), 1));
        setDefaultImage();

        // Create upload button
        uploadImageButton = createColorfulButton("Upload Photo", new Color(106, 90, 205), new Color(72, 61, 139));
        uploadImageButton.addActionListener(e -> uploadImage());

        imagePanel.add(imageLabel, BorderLayout.CENTER);
        imagePanel.add(uploadImageButton, BorderLayout.SOUTH);

        // Add image panel to the right of the form panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setOpaque(false);
        rightPanel.add(imagePanel, BorderLayout.NORTH);
        add(rightPanel, BorderLayout.EAST);

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 248, 255)); // Light blue background for buttons

        // Buttons with custom colors and hover effects
        addButton = createColorfulButton("Add Pet", new Color(72, 201, 176), new Color(34, 139, 115));
        editButton = createColorfulButton("Edit Pet", new Color(255, 165, 0), new Color(255, 140, 0));
        deleteButton = createColorfulButton("Delete Pet", new Color(255, 99, 71), new Color(255, 69, 0));
        saveButton = createColorfulButton("Save to File", new Color(65, 105, 225), new Color(0, 0, 205));

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(saveButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Button actions
        addButton.addActionListener(e -> addPet());
        editButton.addActionListener(e -> editPet());
        deleteButton.addActionListener(e -> deletePet());
        saveButton.addActionListener(e -> saveToFile());

        // Add selection listener to the table
        petTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // This ensures we only handle the final selection event
                handlePetSelection();
            }
        });

        // Load saved pets at startup
        loadFromFile();
    }

    private JButton createColorfulButton(String text, Color background, Color hover) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(background);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0), 2));
        button.setPreferredSize(new Dimension(120, 40));

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(hover);
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(background);
            }
        });

        return button;
    }

    private void addPet() {
        try {
            // Validate inputs
            if (nameField.getText().trim().isEmpty() ||
                    breedField.getText().trim().isEmpty() ||
                    ageField.getText().trim().isEmpty()) {
                throw new InvalidPetDataException("Please fill in all fields");
            }

            String name = nameField.getText().trim();
            String breed = breedField.getText().trim();
            int age;

            try {
                age = Integer.parseInt(ageField.getText().trim());
                if (age < 0) {
                    throw new InvalidPetDataException("Age must be a positive number");
                }
            } catch (NumberFormatException ex) {
                throw new InvalidPetDataException("Please enter a valid age");
            }

            String type = (String) typeComboBox.getSelectedItem();
            Animal pet;

            // Create appropriate animal type based on selection
            switch(type) {
                case "Dog":
                    pet = new Dog(name, breed, age);
                    break;
                case "Cat":
                    pet = new Cat(name, breed, age);
                    break;
                case "Rabbit":
                    pet = new Rabbit(name, breed, age);
                    break;
                case "Hamster":
                    pet = new Hamster(name, breed, age);
                    break;
                case "Bird":
                    pet = new Bird(name, breed, age);
                    break;
                case "Fish":
                    pet = new Fish(name, breed, age);
                    break;
                case "Guinea Pig":
                    pet = new GuineaPig(name, breed, age);
                    break;
                case "Turtle":
                    pet = new Turtle(name, breed, age);
                    break;
                case "Ferret":
                    pet = new Ferret(name, breed, age);
                    break;
                case "Parrot":
                    pet = new Parrot(name, breed, age);
                    break;
                default:
                    throw new InvalidPetDataException("Invalid pet type selected");
            }

            petList.add(pet);
            updateTable();
            clearForm();

        } catch (InvalidPetDataException ex) {
            JOptionPane.showMessageDialog(this,
                ex.getMessage(),
                "Input Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void editPet() {
        try {
            int selectedRow = petTable.getSelectedRow();
            if (selectedRow < 0) {
                throw new PetNotFoundException("Please select a pet to edit");
            }

            // Validate inputs
            if (nameField.getText().trim().isEmpty() ||
                    breedField.getText().trim().isEmpty() ||
                    ageField.getText().trim().isEmpty()) {
                throw new InvalidPetDataException("Please fill in all fields");
            }

            Animal selectedPet = petList.get(selectedRow);
            selectedPet.setName(nameField.getText().trim());
            selectedPet.setBreed(breedField.getText().trim());

            try {
                int age = Integer.parseInt(ageField.getText().trim());
                if (age < 0) {
                    throw new InvalidPetDataException("Age must be a positive number");
                }
                selectedPet.setAge(age);
            } catch (NumberFormatException ex) {
                throw new InvalidPetDataException("Please enter a valid age");
            }

            selectedPet.setType((String) typeComboBox.getSelectedItem());

            // Update table
            updateTable();

            // Clear form
            nameField.setText("");
            breedField.setText("");
            ageField.setText("");
        } catch (PetNotFoundException | InvalidPetDataException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePet() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow >= 0) {
            petList.remove(selectedRow);

            // Update table
            updateTable();
        }
    }

    private void updateTable() {
        DefaultTableModel model = (DefaultTableModel) petTable.getModel();
        model.setRowCount(0);

        for (Animal pet : petList) {
            model.addRow(new Object[]{
                petList.indexOf(pet) + 1,  // Changed to start with Pet ID
                pet.getName(),
                pet.getBreed(),
                pet.getAge() + " (" + pet.calculateHumanAge() + " human years)",
                pet.getType() + " - " + pet.makeSound()
            });
        }
    }

    private void clearForm() {
        nameField.setText("");
        breedField.setText("");
        ageField.setText("");
        typeComboBox.setSelectedIndex(0);
    }

    private void saveToFile() {
        try {
            // Create directories if they don't exist
            new File("pet_images").mkdirs();
            
            // Save pet data
            File file = new File("pets_data.txt");
            PrintWriter writer = new PrintWriter(new FileWriter(file));
            
            // Write header
            writer.println("Pet ID\tName\tBreed\tAge\tType");
            
            // Write each pet's data
            for (Animal pet : petList) {
                writer.println(String.format("%d\t%s\t%s\t%d\t%s",
                    petList.indexOf(pet) + 1,
                    pet.getName(),
                    pet.getBreed(),
                    pet.getAge(),
                    pet.getType()));
                
                // Save pet's profile picture if it exists
                if (pet.getProfilePicture() != null) {
                    try {
                        String imagePath = "pet_images/" + 
                            pet.getName().toLowerCase() + "_" + 
                            pet.getType().toLowerCase() + ".png";
                        
                        BufferedImage bi = new BufferedImage(
                            IMAGE_WIDTH, IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D g2d = bi.createGraphics();
                        pet.getProfilePicture().paintIcon(null, g2d, 0, 0);
                        g2d.dispose();
                        
                        ImageIO.write(bi, "png", new File(imagePath));
                    } catch (IOException e) {
                        System.err.println("Could not save image for pet: " + pet.getName());
                    }
                }
            }
            
            writer.close();
            JOptionPane.showMessageDialog(this,
                "Data successfully saved!",
                "Save Successful",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                "Error saving data: " + ex.getMessage(),
                "Save Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setDefaultImage() {
        try {
            BufferedImage defaultImg = ImageIO.read(new File("default_pet.png"));
            ImageIcon defaultIcon = new ImageIcon(defaultImg.getScaledInstance(
                    IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
            imageLabel.setIcon(defaultIcon);
        } catch (IOException e) {
            imageLabel.setIcon(null);
            imageLabel.setText("No Image");
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
        }
    }

    private void uploadImage() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter(
            "Image files", "jpg", "jpeg", "png", "gif"));
        
        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();
                BufferedImage originalImage = ImageIO.read(selectedFile);
                
                // Scale image to fit the label while maintaining aspect ratio
                double scale = Math.min(
                    (double) IMAGE_WIDTH / originalImage.getWidth(),
                    (double) IMAGE_HEIGHT / originalImage.getHeight()
                );
                
                int scaledWidth = (int) (originalImage.getWidth() * scale);
                int scaledHeight = (int) (originalImage.getHeight() * scale);
                
                BufferedImage scaledImage = new BufferedImage(
                    scaledWidth, scaledHeight, BufferedImage.TYPE_INT_ARGB);
                
                Graphics2D g2d = scaledImage.createGraphics();
                g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_BILINEAR);
                g2d.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
                g2d.dispose();
                
                ImageIcon profileIcon = new ImageIcon(scaledImage);
                imageLabel.setIcon(profileIcon);
                
                // If a pet is selected, update its profile picture
                int selectedRow = petTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Animal selectedPet = petList.get(selectedRow);
                    selectedPet.setProfilePicture(profileIcon);
                    updateTable(); // Refresh the table to show the updated image
                }
                
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this,
                    "Error loading image: " + ex.getMessage(),
                    "Image Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Update the handlePetSelection method
    private void handlePetSelection() {
        int selectedRow = petTable.getSelectedRow();
        if (selectedRow >= 0) {
            Animal selectedPet = petList.get(selectedRow);
            
            // Update the image label with the selected pet's profile picture
            if (selectedPet.getProfilePicture() != null) {
                imageLabel.setIcon(selectedPet.getProfilePicture());
            } else {
                setDefaultImage(); // Show default image if no profile picture exists
            }
            
            // Fill form fields
            nameField.setText(selectedPet.getName());
            breedField.setText(selectedPet.getBreed());
            ageField.setText(String.valueOf(selectedPet.getAge()));
            typeComboBox.setSelectedItem(selectedPet.getType());
        }
    }

    private void loadFromFile() {
        try {
            File file = new File("pets_data.txt");
            if (!file.exists()) {
                return; // No saved data yet
            }

            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            
            // Skip header line
            reader.readLine();
            
            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split("\t");
                    if (data.length >= 5) {
                        String name = data[1];
                        String breed = data[2];
                        int age = Integer.parseInt(data[3]);
                        String type = data[4];
                        
                        // Create appropriate animal type based on the saved type
                        Animal pet;
                        switch(type) {
                            case "Dog":
                                pet = new Dog(name, breed, age);
                                break;
                            case "Cat":
                                pet = new Cat(name, breed, age);
                                break;
                            case "Rabbit":
                                pet = new Rabbit(name, breed, age);
                                break;
                            case "Hamster":
                                pet = new Hamster(name, breed, age);
                                break;
                            case "Bird":
                                pet = new Bird(name, breed, age);
                                break;
                            case "Fish":
                                pet = new Fish(name, breed, age);
                                break;
                            case "Guinea Pig":
                                pet = new GuineaPig(name, breed, age);
                                break;
                            case "Turtle":
                                pet = new Turtle(name, breed, age);
                                break;
                            case "Ferret":
                                pet = new Ferret(name, breed, age);
                                break;
                            case "Parrot":
                                pet = new Parrot(name, breed, age);
                                break;
                            default:
                                continue; // Skip invalid pet types
                        }

                        // Try to load pet's profile picture if it exists
                        try {
                            File imageFile = new File("pet_images/" + name.toLowerCase() + "_" + type.toLowerCase() + ".png");
                            if (imageFile.exists()) {
                                BufferedImage img = ImageIO.read(imageFile);
                                ImageIcon icon = new ImageIcon(img.getScaledInstance(
                                    IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_SMOOTH));
                                pet.setProfilePicture(icon);
                            }
                        } catch (IOException e) {
                            // If image loading fails, continue without the image
                            System.err.println("Could not load image for pet: " + name);
                        }

                        petList.add(pet);
                    }
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    System.err.println("Error parsing line: " + line);
                }
            }
            reader.close();
            
            // Update the table with loaded pets
            updateTable();
            
            // Show confirmation message
            JOptionPane.showMessageDialog(this,
                petList.size() + " pets loaded successfully!",
                "Data Loaded",
                JOptionPane.INFORMATION_MESSAGE);
                
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this,
                "Error loading saved data: " + e.getMessage(),
                "Load Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PetManagementSystemUI ui = new PetManagementSystemUI();
            ui.setVisible(true);
        });
    }
}
