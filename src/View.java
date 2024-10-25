import java.util.List;

import dbreader.DbReader;
import model.Animal;

public class View {
    public void showMenu(){
        System.out.println("""
                        1. Показать всех животных
                        2. Завести новое животное
                        3. Выйти
                            """);
                            
    }

    public void show(String string){
        System.out.println(string);
    }

    public void displayList(List<Animal> resultList){
        int count = 1;
        for (Animal animal:
                resultList) {
            System.out.println("" + count++ + " " + animal);
        }
    }
}
