package model;

public class AnimalFactory {

    public AnimalFactory() {
    }

    public Animal create(String type){
        switch (type.toLowerCase()){
            case "cat":
                return new Cat();
            case "dog":
                return new Dog();
            case "hamster":
                return new Hamster();
            case "horse":
                return new Horse();
            case "camel":
                return new Camel();
            case "donkey":
                return new Donkey();
            default:
                return null;
        }
    }
}
