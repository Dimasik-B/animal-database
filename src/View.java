import java.util.List;
import java.util.Map;

import model.Animal;

public class View {
    public void showMenu(){
        System.out.println("""
                        1. Показать всех животных
                        2. Завести новое животное
                        3. Найти животное
                        4. Выйти
                            """);
    }

    public void showAnimalEditorMenu(){
        System.out.println("""
                        1. Обучить новой команде
                        2. Удалить запись
                        3. Отмена
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
