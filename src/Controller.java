import java.time.LocalDate;
import java.util.Scanner;

import dbreader.DbReader;
import model.Animal;
import model.Dog;

public class Controller {
    private View view;
    private DbReader dbReader;
    private Scanner scanner;

    public Controller(){
        this.view = new View();
        this.dbReader = new DbReader();
        this.scanner = new Scanner(System.in);
    }

    public void start(){
        while (true){
            view.showMenu();
            switch (scanner.nextInt()){
                case 1:
                    view.displayList(dbReader.getAllAnimals());
                    // System.out.println(dbReader.findTablesNames().contains("cats"));
                    break;
                case 2:
                    dbReader.addAnimal(animalReader());
                    break;
                case 3:
                    System.exit(0);
            }
        }
    }

    private Animal animalReader(){
        String temp = "";
        Dog testDog = new Dog();
        System.out.println("Введите имя: ");
        temp = scanner.next();
        testDog.setName(temp);
        System.out.println("Введите дату рождения: ");
        temp = scanner.next();
        testDog.setBirthDate(LocalDate.parse(temp));
        System.out.println("Введите команды: ");
        temp = scanner.next();
        testDog.setCommands(temp);
        return testDog;
    }
}
