import db.DbReader;
import model.Animal;
import model.AnimalEditor;

import java.util.ArrayList;
import java.util.List;

public class LocalDB {
    private DbReader dbReader;
    private View view;

    private List<Animal> animalList;
    private List<Animal> editedList;

    public LocalDB() {
        this.dbReader = new DbReader();
        this.view = new View();
        this.animalList = new ArrayList<>();
        this.editedList = new ArrayList<>();
    }

    public List<Animal> getAnimalList() {
        return animalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.animalList = animalList;
    }

    public List<Animal> getEditedList() {
        return editedList;
    }

    public void setEditedList(List<Animal> editedList) {
        this.editedList = editedList;
    }

    public void fillLocalBase(){
        animalList = dbReader.getAllAnimals();
    }

    public Animal getAnimal(int index){
        return animalList.get(index - 1);
    }

    public List<Animal> findAnimal(String name){
        List<Animal> resultList = new ArrayList<>();
        for (Animal animal:
             animalList) {
            if(animal.getName().equals(name)) resultList.add(animal);
        }
        return resultList;
    }

    public void editAnimal(Animal animal, String command){
        AnimalEditor editor = new AnimalEditor(animal);
        view.show(editor.learnNewComand(command));
        dbReader.updateAnimal(animal);

    }

    public void deleteAnimal(Animal animal){
        dbReader.deleteAnimal(animal);
    }





}


