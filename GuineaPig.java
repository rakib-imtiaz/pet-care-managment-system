public class GuineaPig extends Animal {
    public GuineaPig(String name, String breed, int age) {
        super(name, breed, age);
        this.type = "Guinea Pig";
    }

    @Override
    public String makeSound() {
        return "Wheek wheek!";
    }

    @Override
    public int calculateHumanAge() {
        return age * 12;
    }
} 