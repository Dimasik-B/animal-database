import dbreader.DbReader;
import model.Animal;
import model.Cat;
import model.Dog;

import java.sql.Connection;
import java.sql.DriverManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//             System.out.println("Hello world!");
//         DbReader dbReader = new DbReader();
//         dbReader.testConection();
//         Cat testCat = new Cat();
//         testCat.setName("Fasol");
//         testCat.setCommands("Eat, shit, sleep");
// //        System.out.println(testCat);
//         test();
        // Animal testDog = new Dog();
        // testDog.setName("test");
        // testDog.setBirthDate(LocalDate.parse("2000-01-01"));
        // testDog.setCommands("test");
        // dbReader.addAnimal(testDog);
        // System.out.println();
        // System.out.println(testDog.getClass().getSimpleName());
//         test();

            Controller controller = new Controller();
            controller.start();

    }

    static void test(){
        DbReader dbReader = new DbReader();
        List<Animal> test = dbReader.getAllAnimals();
        int count = 1;
        for (Animal animal:
                test) {
            System.out.println("" + count++ + " " + animal);
        }
    }
}