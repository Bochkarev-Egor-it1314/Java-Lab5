package ru.Bochkarev.Main;

import ru.Bochkarev.Task1.Fraction;

import java.util.Scanner;

public class Main {

    // Главный метод
    public static void main(String[] args) {
        System.out.println("1) Задание 1. №1 ");
        System.out.println("2) Задание 2. №1 ");
        System.out.println("3) Задание 3. №5 ");
        System.out.println("4) Задание 4. №2 ");
        System.out.println("5) Задание 5. №3 ");
        System.out.println("6) Задание 6. №4 ");
        System.out.println("7) Задание 7. №1 ");
        System.out.println("7) Задание 7. №2 ");
        System.out.println("Выберите задание: ");

        Check check = new Check();
        Scanner scan = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        if (scan.hasNextInt()) {
            int choice = scan.nextInt();

            switch (choice) {

                case 1: {
                    System.out.println("Задание 1");

                    try {
                        // Первая дробь
                        System.out.print("Введите числитель: ");
                        int numerator = check.readInt(scanner);
                        System.out.print("Введите знаменатель: ");
                        int denominator = check.readInt(scanner);
                        Fraction fraction = new Fraction(numerator, denominator);
                        System.out.println("Создана дробь: " + fraction);

                        // Вторая дробь
                        System.out.print("Введите числитель второй дроби: ");
                        int numerator2 = check.readInt(scanner);
                        System.out.print("Введите знаменатель второй дроби: ");
                        int denominator2 = check.readInt(scanner);
                        Fraction fraction2 = new Fraction(numerator2, denominator2);
                        System.out.println("Создана дробь: " + fraction2);

                        // Операции с дробями
                        System.out.println("Сложение дробей: " + fraction.add(fraction2));
                        System.out.println("Вычитание дробей: " + fraction.subtract(fraction2));
                        System.out.println("Умножение дробей: " + fraction.multiply(fraction2));
                        System.out.println("Деление дробей: " + fraction.divide(fraction2));

                        // Операции с целым числом
                        System.out.print("Введите целое число для операции с первой дробью: ");
                        int number = check.readInt(scanner);
                        System.out.println("Сложение с числом: " + fraction.add(number));
                        System.out.println("Вычитание с числом: " + fraction.subtract(number));
                        System.out.println("Умножение на число: " + fraction.multiply(number));
                        System.out.println("Деление на число: " + fraction.divide(number));

                        // Кэширование вещественного значения
                        System.out.println("Вещественное значение первой дроби: " + fraction.getDoubleValue());

                        // Проверка равенства
                        System.out.println("Равенство первой и второй дроби: " + fraction.equals(fraction2));

                        // Изменение дроби
                        System.out.println("Введите новый числитель для первой дроби: ");
                        fraction.setNumerator(check.readInt(scanner));
                        System.out.println("Введите новый знаменатель для первой дроби: ");
                        fraction.setDenominator(check.readInt(scanner));
                        System.out.println("Обновленная первая дробь: " + fraction);


                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }

                    break;
                }
                case 2: {
                    System.out.println("Задание 2");


                    break;
                }
                case 3: {
                    System.out.println("Задание 3");


                    break;
                }
                case 4: {
                    System.out.println("Задание 4");


                    break;

                }
                case 5: {
                    System.out.println("Задание 5");


                    break;
                }
                case 6: {
                    System.out.println("Задание 6");


                    break;
                }
                case 7: {
                    System.out.println("Задание 7");


                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
