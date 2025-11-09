package ru.Bochkarev.Task7;

public class Person {
    private String name;
    private Integer number;

    // Конструктор
    public Person(String name, Integer number) {
        this.name = name;
        this.number = number;
    }

    // Геттеры
    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    // Возвращение строки
    @Override
    public String toString() {
        return name;
    }

    // Преобразование строки
    public static Person fromString(String str) {
        String[] parts = str.split(":");
        String name = parts[0].trim();
        Integer number = null;
        if (parts.length > 1 && !parts[1].trim().isEmpty()) {
            try {
                number = Integer.parseInt(parts[1].trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка формата строки: " + str);
            }
        }
        return new Person(name, number);
    }
}
