package model;

public class AnimalEditor {
    Animal animal;

    public AnimalEditor(Animal animal) {
        this.animal = animal;
    }

    public String learnNewComand(String command){
        String commandList = animal.getCommands().toLowerCase();
        if (!commandList.contains(command.toLowerCase())){
            animal.setCommands(animal.getCommands() + String.format(", %s", command));
            return "Команда успешно добавлена!";
        }
        return "Такая команда уже есть";
    }
}
