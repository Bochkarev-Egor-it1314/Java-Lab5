package ru.Bochkarev.Task2;

public class Cat implements Meowable {
    private String name;

    // Конструктор
    public Cat(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Имя кота не может быть пустым");
        }
        this.name = name.trim();
    }

    // Геттеры
    public String getName() {
        return name;
    }

    // Сеттеры
    public void setName(String name) {
        this.name = name;
    }

    // Реализация интерфейса
    @Override
    public void meow() {
        System.out.println(name + ": мяу!");
    }

    // Возвращение строки
    @Override
    public String toString() {
        return "Кот: " + name;
    }
}