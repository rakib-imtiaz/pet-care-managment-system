public class Rabbit extends Animal {
    public Rabbit(String name, String breed) {
        super(name, breed);
        this.type = "Rabbit";
    }

    public Rabbit(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Rabbit";
    }

    @Override
    public String makeSound() {
        return "Squeak!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 6; // Rabbits age approximately 6 human years for each rabbit year
    }
} 