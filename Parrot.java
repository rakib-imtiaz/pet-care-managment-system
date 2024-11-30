public class Parrot extends Animal {
    public Parrot(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Parrot";
    }

    @Override
    public String makeSound() {
        return "Hello!"; // Parrots can mimic human speech
    }

    @Override
    public int calculateHumanAge() {
        return age * 7;
    }
} 