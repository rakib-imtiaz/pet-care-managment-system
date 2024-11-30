public class Turtle extends Animal {
    public Turtle(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Turtle";
    }

    @Override
    public String makeSound() {
        return "Hiss...";
    }

    @Override
    public int calculateHumanAge() {
        return age * 3; // Turtles age more slowly than humans
    }
} 