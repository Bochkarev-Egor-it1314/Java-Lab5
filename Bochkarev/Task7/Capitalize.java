package ru.Bochkarev.Task7;

public class Capitalize {
    // Метод для преобразования первого символа в верхний регистр
    public String capitalize(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
