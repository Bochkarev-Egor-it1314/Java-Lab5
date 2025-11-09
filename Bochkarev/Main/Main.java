package ru.Bochkarev.Main;

import ru.Bochkarev.Task1.Fraction;
import ru.Bochkarev.Task2.Cat;
import ru.Bochkarev.Task2.Funs;
import ru.Bochkarev.Task2.CatDecorator;
import ru.Bochkarev.Task3.MyList;
import ru.Bochkarev.Task4.OlympiadResults;
import ru.Bochkarev.Task5.ConsonantsInOneWord;
import ru.Bochkarev.Task6.QueueEquality;
import ru.Bochkarev.Task7.Capitalize;
import ru.Bochkarev.Task7.Point;
import ru.Bochkarev.Task7.Polyline;
import ru.Bochkarev.Task7.Person;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

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
        System.out.println("8) Задание 7. №2 ");
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
                        System.out.println("Вещественное значение второй дроби: " + fraction2.getDoubleValue());

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

                    try {
                        System.out.print("Введите имя кота: ");
                        String catname = scanner.nextLine();
                        Cat cat = new Cat(catname);
                        CatDecorator dec = new CatDecorator(cat);

                        System.out.print("Сколько раз мяукает кот: ");
                        int number = check.readInt(scanner);

                        Funs.meowsCare(dec, number);

                        System.out.println("Кот мяукнул " + dec.getMeowCount() + " раз(а)");

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }

                    break;
                }
                case 3: {
                    System.out.println("Задание 3");

                    // Создаем список и добавляем несколько элементов
                    System.out.println("Какой список нужно создать?");
                    System.out.println("1) Integer");
                    System.out.println("2) String");
                    System.out.println("Ваш выбор: ");
                    int choose = scan.nextInt();

                    switch (choose) {

                        case 1: {
                            System.out.println("\nСколько элементов в списке?");
                            int number = check.readInt(scanner);
                            MyList<Integer> ints = new MyList<>();
                            for(int i = 0; i < number; i++) {
                                System.out.println("=Введите элемент=");
                                int x = check.readInt(scanner);
                                ints.add(x);
                            }
                            System.out.println("\nИзначальный список: " + ints);
                            System.out.println("Какой элемент убрать?");
                            int removed = ints.removeAll(check.readInt(scanner));
                            System.out.println("Удалено элементов: " + removed);
                            System.out.println("Список после удаления: " + ints);
                            System.out.println("Размер: " + ints.size());
                            System.out.println();

                            break;
                        }
                        case 2: {
                            System.out.println("\nСколько элементов в списке?");
                            int number = check.readInt(scanner);
                            scanner.nextLine();
                            MyList<String> strings = new MyList<>();
                            for(int i = 0; i < number; i++) {
                                System.out.println("=Введите элемент=");
                                String x = scanner.nextLine();
                                strings.add(x);
                            }
                            System.out.println("\nИзначальный список: " + strings);
                            System.out.println("Какой элемент убрать?");
                            int removed = strings.removeAll(scanner.nextLine());
                            System.out.println("Удалено элементов: " + removed);
                            System.out.println("Список после удаления: " + strings);
                            System.out.println("Размер: " + strings.size());
                            System.out.println();

                            break;
                        }
                        default: {
                            break;
                        }
                    }

                    break;
                }
                case 4: {
                    System.out.println("Задание 4");

                    // Чтение файла
                    String filename = "C:\\Users\\egorb\\IdeaProjects\\Lab5\\src\\ru\\Bochkarev\\Task4\\text.txt";

                    try {
                        OlympiadResults analyzer = new OlympiadResults();
                        analyzer.loadFromFile(filename);

                        List<String> top = analyzer.getTopParticipants();
                        int max = analyzer.getMaxScore();

                        if (top.isEmpty()) {
                            System.out.println("Данные об участниках отсутствуют.");
                        } else {
                            System.out.println("\nУчастники с максимальным количеством баллов (" + max + "):");
                            for (String name : top) {
                                System.out.println(name);
                            }
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }

                    break;

                }
                case 5: {
                    System.out.println("Задание 5");

                    // Чтение файла
                    String filename = "C:\\Users\\egorb\\IdeaProjects\\Lab5\\src\\ru\\Bochkarev\\Task5\\text.txt";
                    System.out.println("\nТекст в файле:");

                    try {
                        // Обработка файла и извлечение всех данных
                        Map<Character, Set<String>> consonantToWords = ConsonantsInOneWord.processFile(filename);

                        // Найдём согласные, которые встречаются ровно в одном слове
                        Set<Character> result = ConsonantsInOneWord.findUniqueConsonants(consonantToWords);

                        // Выводим результат
                        System.out.println("\nСогласные буквы, встречающиеся ровно в одном слове (в алфавитном порядке):");
                        for (Character consonant : result) {
                            System.out.print(consonant + " ");
                        }

                    } catch (IOException e) {
                        System.err.println("Ошибка при чтении файла: " + e.getMessage());
                    }

                    break;
                }
                case 6: {
                    System.out.println("Задание 6");

                    System.out.println("\nСколько элементов в очереди?");
                    int number = check.readInt(scanner);
                    Queue<Integer> queue = new LinkedList<>();
                    for(int i = 0; i < number; i++) {
                        System.out.println("=Введите элемент=");
                        int x = check.readInt(scanner);
                        queue.add(x);
                    }
                    System.out.println("\nИзначальная очередь: " + queue);

                    System.out.println("\nБудем проверять равенство участка с i");
                    int i = check.readInt(scanner);
                    System.out.println("по j");
                    int j = check.readInt(scanner);

                    boolean result = QueueEquality.checkEquality(queue, i, j);

                    // Выводим результат
                    if (result) {
                        System.out.println("Элементы с индексов " + i + " по " + j + " равны.");
                    } else {
                        System.out.println("Элементы с индексов " + i + " по " + j + " не равны.");
                    }

                    break;
                }
                case 7: {
                    System.out.println("Задание 7");

                    System.out.print("\nСколько точек создать? ");
                    int number = check.readInt(scanner);
                    scanner.nextLine();

                    // Список точек
                    List<Point> points = new ArrayList<>();

                    for (int i = 0; i < number; i++) {
                        System.out.println("Введите координаты в формате: X Y");
                        String input = scanner.nextLine().trim();

                        // Если строка пустая, выходим из цикла
                        if (input.isEmpty()) {
                            break;
                        }

                        // Разделяем строку на части по пробелу
                        String[] parts = input.split("\\s+");
                        if (parts.length == 2) {
                            try {
                                double x = Double.parseDouble(parts[0]);
                                double y = Double.parseDouble(parts[1]);
                                points.add(new Point(x, y));

                            } catch (NumberFormatException e) {
                                System.out.println("Неверный формат. Введите числа для X и Y");
                            }
                        } else {
                            System.out.println("Ошибка ввода. Введите координаты в формате: X Y");
                        }
                    }

                    // Стрим: фильтруем уникальные точки, сортируем по X, изменяем Y на положительный, собираем в Polyline
                    Polyline polyline = new Polyline(
                            points.stream()
                                    .distinct() // Убираем дубликаты
                                    .sorted(Comparator.comparing(Point::getX)) // Сортируем по X
                                    .map(p -> new Point(p.getX(), Math.abs(p.getY()))) // Меняем Y на положительный
                                    .collect(Collectors.toList()) // Собираем в список
                    );

                    // Выводим ломаную линию
                    System.out.println(polyline);

                    break;
                }
                case 8: {
                    System.out.println("Задание 8");

                    Capitalize c = new Capitalize();
                    String filename = "C:\\Users\\egorb\\IdeaProjects\\Lab5\\src\\ru\\Bochkarev\\Task7\\text.txt";

                    try {
                        // Чтение и вывод содержимого файла на экран
                        System.out.println("\nСодержимое файла:");
                        Files.lines(Paths.get(filename)).forEach(System.out::println);

                        // Чтение из файла
                        List<Person> people = Files.lines(Paths.get(filename))
                                .map(Person::fromString)
                                .filter(person -> person.getNumber() != null) // фильтруем людей без номера
                                .collect(Collectors.toList());

                        // Группировка людей по номеру и форматирование имени
                        Map<Integer, List<String>> groupedByNumber = people.stream().collect(Collectors.groupingBy(
                                Person::getNumber, // группируем по номеру
                                TreeMap::new, // используем TreeMap для сортировки по номеру
                                Collectors.mapping(
                                        person -> c.capitalize(person.getName().toLowerCase()), // приводим имя к нужному виду
                                        Collectors.toList()
                                )
                        ));

                        // Выводим результат
                        System.out.println("\nОтсортированный список: " + groupedByNumber);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    break;
                }
                default: {
                    break;
                }
            }
        }
    }
}
