package ru.Bochkarev.Task2;

public class Funs {
    public static void meowsCare(Meowable m, int number) {
        if (m == null) {
            throw new IllegalArgumentException("Мяукающий объект не может быть null");
        }

        // Вызываем мяуканье
        for (int i = 0; i < number; i++) {
            m.meow();
        }
    }
}

