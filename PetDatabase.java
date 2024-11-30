import java.io.*;
import java.util.*;

public class PetDatabase {
    private static final String FILE_NAME = "pets.txt";

    public static void savePets(List<Pet> petList) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : petList) {
                writer.write(pet.getId() + "," + pet.getName() + "," + pet.getBreed() + "," + pet.getAge() + "," + pet.getType());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Pet> loadPets() {
        List<Pet> petList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] petData = line.split(",");
                int id = Integer.parseInt(petData[0]);
                String name = petData[1];
                String breed = petData[2];
                int age = Integer.parseInt(petData[3]);
                String type = petData[4];
                petList.add(new Pet(id, name, breed, age, type));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return petList;
    }
}
