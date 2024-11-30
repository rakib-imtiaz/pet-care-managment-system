public class Cat extends Animal {
    public Cat(String name, String breed) {
        super(name, breed);
        this.type = "Cat";
    }

    public Cat(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Cat";
    }

    @Override
    public String makeSound() {
        return "Meow!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 4; // Cats age approximately 4 human years for each cat year
    }
} 