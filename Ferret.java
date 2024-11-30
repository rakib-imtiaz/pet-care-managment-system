public class Ferret extends Animal {
    public Ferret(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Ferret";
    }

    @Override
    public String makeSound() {
        return "Dook dook!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 15;
    }
} 