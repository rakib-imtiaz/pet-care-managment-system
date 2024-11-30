public class Fish extends Animal {
    public Fish(String name, String breed) {
        super(name, breed);
        this.type = "Fish";
    }

    public Fish(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Fish";
    }

    @Override
    public String makeSound() {
        return "Blub blub!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 5; // Fish age varies by species, using average
    }
} 