import javax.swing.ImageIcon;

public abstract class Animal {
    protected String name;
    protected int age;
    protected String breed;
    protected String type;
    protected ImageIcon profilePicture;
    private static final ImageIcon DEFAULT_IMAGE = new ImageIcon("default_pet.png");

    // Overloaded constructors
    public Animal(String name, String breed) {
        this(name, breed, 0); // Calls the other constructor with default age
    }

    public Animal(String name, String breed, int age) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.profilePicture = DEFAULT_IMAGE;
    }

    // Abstract method that must be implemented by subclasses
    public abstract String makeSound();

    // Common methods
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    // Method to be overridden by subclasses for specific age calculations
    public int calculateHumanAge() {
        return age;
    }

    // Add getter and setter for profile picture
    public ImageIcon getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(ImageIcon image) {
        this.profilePicture = image;
    }
} 