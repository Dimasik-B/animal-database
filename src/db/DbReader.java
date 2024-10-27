package db;

import model.Animal;
import model.AnimalFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DbReader {



    public DbReader() {
    }

    public List<Animal> getAllAnimals(){
        List<Animal> resultList = new ArrayList<Animal>();

        try{
            String url = "jdbc:mysql://localhost:3306/human_friends?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1369";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT c.name, c.birth_date, c.commands, p.animal_type  \n" +
                                                                "\tFROM cat c\n" +
                                                                "\tLEFT JOIN pet p ON p.id = c.pet_id \n" +
                                                                "\tUNION\n" +
                                                                "\tSELECT d.name, d.birth_date, d.commands, p.animal_type\n" +
                                                                "\tFROM dog d\n" +
                                                                "\tLEFT JOIN pet p ON p.id = d.pet_id \n" +
                                                                "\tUNION\n" +
                                                                "\tSELECT h.name, h.birth_date, h.commands, p.animal_type\n" +
                                                                "\tFROM hamster h\n" +
                                                                "\tLEFT JOIN pet p ON p.id = h.pet_id \n" +
                                                                "\tUNION\n" +
                                                                "\tSELECT h2.name, h2.birth_date, h2.commands, pa.animal_type\n" +
                                                                "\tFROM horse h2 \n" +
                                                                "\tLEFT JOIN pack_animal pa ON pa.id = h2.pack_animal_id \t\n" +
                                                                "\tUNION\n" +
                                                                "\tSELECT c2.name, c2.birth_date, c2.commands, pa.animal_type\n" +
                                                                "\tFROM camel c2 \n" +
                                                                "\tLEFT JOIN pack_animal pa ON pa.id = c2.pack_animal_id \t\n" +
                                                                "\tUNION\n" +
                                                                "\tSELECT d2.name, d2.birth_date, d2.commands, pa.animal_type\n" +
                                                                "\tFROM donkey d2 \n" +
                                                                "\tLEFT JOIN pack_animal pa ON pa.id = d2.pack_animal_id;");
                while(resultSet.next()){

                    String name = resultSet.getString(1);
                    LocalDate birthDate = resultSet.getDate(2).toLocalDate();
                    String commands = resultSet.getString(3);
                    String type = resultSet.getString(4);
                    Animal tempAnimal = new AnimalFactory().create(type);
                    tempAnimal.setName(name);
                    tempAnimal.setBirthDate(birthDate);
                    tempAnimal.setCommands(commands);
                    resultList.add(tempAnimal);

                };
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        return resultList;
    }
    public void addAnimal(Animal animal){
        try{
            String url = "jdbc:mysql://localhost:3306/human_friends?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1369";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                String values ="'" + animal.getName() + "', '" + animal.getBirthDate().toString() + "', '" + animal.getCommands() + "'";
                Statement statement = conn.createStatement();
                statement.executeUpdate("INSERT animal_list(name, birth_date, commands) VALUES (" + values + ")");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void updateAnimal(Animal animal){
        try{
            String url = "jdbc:mysql://localhost:3306/human_friends?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1369";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                statement.executeUpdate(String.format("UPDATE %s SET commands = '%s' WHERE name = '%s';", animal.getClass().getSimpleName().toLowerCase(), animal.getCommands(), animal.getName()));
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }

    public void deleteAnimal(Animal animal){
        try{
            String url = "jdbc:mysql://localhost:3306/human_friends?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1369";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){
                Statement statement = conn.createStatement();
                statement.executeUpdate(String.format("DELETE FROM %s WHERE name = '%s' AND birth_date = '%s';", animal.getClass().getSimpleName().toLowerCase(), animal.getName(), animal.getBirthDate()));
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");
            System.out.println(ex);
        }
    }
}
