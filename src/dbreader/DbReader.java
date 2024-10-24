package dbreader;

import model.Animal;

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
                ResultSet resultSet = statement.executeQuery("SELECT * FROM animal_list");
                while(resultSet.next()){

                    String name = resultSet.getString(1);
                    LocalDate birthDate = resultSet.getDate(2).toLocalDate();
                    String commands = resultSet.getString(3);
                    int age = resultSet.getInt(4);
                    String type = resultSet.getString(5);
                    Animal tempAnimal = new Animal();
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

    public void addAnimal (Animal animal){
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
    public void testConection(){
        try{
            String url = "jdbc:mysql://localhost:3306/human_friends?serverTimezone=Europe/Moscow&useSSL=false";
            String username = "root";
            String password = "1369";
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            try (Connection conn = DriverManager.getConnection(url, username, password)){

                System.out.println("Connection to Store DB succesfull!");
            }
        }
        catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
    }
}
