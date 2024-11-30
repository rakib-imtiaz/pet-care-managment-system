public class Hamster extends Animal {
    public Hamster(String name, String breed) {
        super(name, breed);
        this.type = "Hamster";
    }

    public Hamster(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Hamster";
    }

    @Override
    public String makeSound() {
        return "Squeak squeak!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 25; // Hamsters age rapidly compared to humans
    }
} 