public class Pet {
    private String name;
    private String breed;
    private int age;
    private String type;
    private int id;

    // Constructor
    public Pet(int id, String name, String breed, int age, String type) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.type = type;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    @Override
    public String toString() {
        return "ID: " + id + " Name: " + name + " Breed: " + breed + " Age: " + age + " Type: " + type;
    }
}
