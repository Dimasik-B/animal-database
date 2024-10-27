package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Animal {
    protected String name;
    protected LocalDate birthDate;
    protected String commands;
    protected int kindId;

    protected String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCommands() {
        return commands;
    }

    public void setCommands(String commands) {
        this.commands = commands;
    }

    public int getKindId() {
        return kindId;
    }
    public String getType() {
        return type;
    }


    @Override
    public String toString() {
        return  "Name: " + name +
                "; Birth Date: " + birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) +
                ": Commands: " + commands +'}';
    }
}
