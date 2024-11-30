public class Bird extends Animal {
    public Bird(String name, String breed) {
        super(name, breed);
        this.type = "Bird";
    }

    public Bird(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Bird";
    }

    @Override
    public String makeSound() {
        return "Chirp chirp!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 9; // Birds age differently based on species, using average
    }
} 