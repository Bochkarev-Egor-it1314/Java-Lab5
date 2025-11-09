package ru.Bochkarev.Task2;

// Реализация шаблона декоратор
public class CatDecorator implements Meowable {
    private Meowable decoratedMeowable;
    private int meowCount;

    // Конструктор
    public CatDecorator(Meowable meowable) {
        if (meowable == null) {
            throw new IllegalArgumentException("Мяукающий объект не может быть null");
        }
        this.decoratedMeowable = meowable;
        this.meowCount = 0;
    }

    // Реализация интерфейса
    @Override
    public void meow() {
        decoratedMeowable.meow(); // Делегируем вызов оригинальному объекту
        meowCount++;
    }

    // Геттер
    public int getMeowCount() {
        return meowCount;
    }

    // Возвращение строки
    @Override
    public String toString() {
        return "Декоратор счетчика для: " + decoratedMeowable.toString();
    }
}
