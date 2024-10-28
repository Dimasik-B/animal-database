import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import db.DbReader;
import model.*;

public class Controller {
    private View view;
    private DbReader dbReader;
    private Scanner scanner;
    private LocalDB localDB;

    public Controller(){
        this.view = new View();
        this.dbReader = new DbReader();
        this.scanner = new Scanner(System.in);
        this.localDB = new LocalDB();
    }

    public void start() throws Exception {
        try(Counter counter = new Counter()) {
            localDB.fillLocalBase();
            while (true){
                view.showMenu();
                switch (scanner.nextInt()){
                    case 1:
                        localDB.fillLocalBase();
                        view.displayList(localDB.getAnimalList());
                        view.show("\n1. Выбрать животное\n2. Назад");
                        switch (scanner.nextInt()){
                            case 1:
                                Animal tempAnimal = localDB.getAnimal(scanner.nextInt());
                                view.show(tempAnimal.toString());
                                view.showAnimalEditorMenu();
                                switch (scanner.nextInt()){
                                    case 1:
                                        view.show("Введите команду: ");
                                        localDB.editAnimal(tempAnimal, scanner.next());
                                        break;
                                    case 2:
                                        localDB.deleteAnimal(tempAnimal);
                                        break;
                                }
                                break;
                            case 2:
                                break;
                        }
                        break;
                    case 2:
                        dbReader.addAnimal(readAnimal());
                        counter.add();
                        break;
                    case 3:
                        view.show("\nВведите имя: ");
                        List<Animal> temp = localDB.findAnimal(scanner.next());
                        if (temp.size() == 0) {
                            view.show("Животное не найдено");
                            break;
                        }
                        view.displayList(temp);
                        view.show("\n1. Выбрать животное\n2. Назад");
                        switch (scanner.nextInt()){
                            case 1:
                                Animal tempAnimal = temp.get(scanner.nextInt() - 1);
                                view.show(tempAnimal.toString());
                                view.showAnimalEditorMenu();
                                switch (scanner.nextInt()){
                                    case 1:
                                        view.show("Введите команду: ");
                                        localDB.editAnimal(tempAnimal, scanner.next());
                                        break;
                                    case 2:
                                        localDB.deleteAnimal(tempAnimal);
                                        break;
                                }
                                break;
                            case 2:
                                break;
                        }
                        break;
                    case 4:
                        System.exit(0);
                }
            }
        }

    }

    private Animal readAnimal(){
        System.out.println("Введите вид: ");
        Animal tempAnimal = new AnimalFactory().create(scanner.next());
        System.out.println("Введите имя: ");
        tempAnimal.setName(scanner.next());
        System.out.println("Введите дату рождения (ДД.ММ.ГГГГ): ");
        tempAnimal.setBirthDate(LocalDate.parse(scanner.next()));
        tempAnimal.setCommands("Нет команд");
        return tempAnimal;
    }
}
