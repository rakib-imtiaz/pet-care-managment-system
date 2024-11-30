public class Dog extends Animal {
    public Dog(String name, String breed) {
        super(name, breed);
        this.type = "Dog";
    }

    public Dog(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Dog";
    }

    @Override
    public String makeSound() {
        return "Woof!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 7; // Dogs age approximately 7 human years for each dog year
    }
} 