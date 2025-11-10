# Java-Lab5

# Отчет по работе: Реализация задач на Java
Бочкарёв Егор ИТ-13,14

## Общее описание
Данный проект представляет собой консольное приложение на Java, которое реализует 7 различных задач:
1. Задание 1. Шаблоны
2. Задание 2. Структурные шаблоны
3. Задание 3. Список
4. Задание 4. Мап
5. Задание 5. Сет
6. Задание 6. Очередь
7. Задание 7. Стрим
8. Задание 8. Стрим

Программа предлагает пользователю выбрать задачу через меню, затем выполняет соответствующую функцию.

## Структура проекта
- Класс `Main` с вызовом всех задач и пользовательским интерфейсом
- Класс `Check` для проверки входных данных
- Класс `Fraction` и его интерфейс `FractionInterface`, который генеририрует такую версию дроби, которая будет кэшировать вычисление вещественного
значения
- Класс `Cat`, его интерфейс `Meowable` и вспомогательные классы `CatDecorator` и `Funs`, которые передают кота в указанный метод, что бы после окончания его работы
узнать сколько раз мяукал кот за время его работы
- Класс `MyList` и его интерфейс `GenericList`, который удаляет из списка L все элементы с указанным значением
- Класс `OlympiadResults`, который выводит на экран фамилию и имя участника, набравшего максимальное количество баллов
- Класс `ConsonantsInOneWord`, который печатает в алфавитном порядке все согласные буквы, которые входят ровно в одно слово
- Класс `QueueEquality`, который проверяет равенство участка очереди с i-го по j-й элемент
- Класс `Point`, `Line` и `Polyline`, которые получают несколько координат точек и собирают из них кривую
- Класс `Person` и `Capitalize`, которые считывают всех людй из списка и выводят их сгруппированно по номеру

## Детальный анализ методов

### Задание 1 (Шаблоны)

**<ins>Задача:</ins>**

В класс Дробь, добавить интерфейс на два метода: получение вещественного значения, установка числителя и установка знаменателя.
Сгенерировать такую версию дроби, которая будет кэшировать вычисление вещественного значения.
Если раннее в вашем варианте не было Дроби, то создайте сущность Дробь со следующими особенностями:
+ Имеет числитель: целое число
+ Имеет знаменатель: целое число
+ Дробь может быть создана с указанием числителя и знаменателя
+ Может вернуть строковое представление вида “числитель/знаменатель”
+ Необходимо корректно обрабатывать отрицательные значения. Учтите, что знаменатель не может быть отрицательным.
+ Переопределите метод сравнения объектов по состоянию таким образом, чтобы две дроби считались одинаковыми тогда, когда у них одинаковые значения числителя и знаменателя.

**<ins>Метод решения:</ins>**

Эта задача решается дополнением класса `Fraction` с прошлой лабораторной работы.

Создаём интерфейс `FractionInterface`. Он нужен для определения методов `getDoubleValue()`, `setNumerator(int numerator)`, `setDenominator(int denominator)`. Таким образом, интерфейс обеспечивает стандарт для работы с дробями: каждый класс, реализующий этот интерфейс, будет иметь эти методы и гарантировать, что они реализованы определённым образом.

Внутри класса 4 поля: `numerator`, `denominator`, `cachedValue` и `isCacheValid`. Также есть конструктор, который: проверяет на 0 знаменатель, задаёт числитель и знаменатель, проверяет на отрицательное значение знаменатель, инваледирует кэш при создании и сокращает дробь.

Кроме этого есть основные геттеры и сеттеры. Геттеры для этой задачи стандартные, а вот сеттеры модернизированные. Сеттер для числителя задаёт числитель, инвалидирует кэш и позваляет сократить число. Сеттер для знаменателя проверяет значение на 0, задаёт знаменатель, делает его положительным, инвалидирует кэш и позваляет сократить число.

Метод `positiveDenominator()` при отрицательном знаменателе меняе его знак на положительный, а знак числителя на отрицательный.

Метод `invalidateCache()` используется для того, чтобы инвалидировать (сделать недействительным) кэшированное вещественное значение дроби. Важно, что дробь кэширует результат деления числителя на знаменатель для ускорения вычислений. Но если дробь изменяется, нужно сбросить кэш, чтобы в следующий раз при запросе обновленное значение снова вычислялось корректно.

Методы `simplify()` и `gcd(int a, int b)` позваляют сокращать дробь с помощью НОД.

Метод это `toString()` отвечает за вывод результата строкой.

Далее следует выполнение всех математических операции с дробями (Сложение, вычитание, умножение и деление) и выполнение с натуральным числом, а не с дробью. В методе с делением проверяется, чтоб не было деления на 0.

Последним идёт меттод `equals(Object obj)`. Метод `equals(Object obj)` используется для проверки, равны ли две дроби. Важно, что метод сравнивает дроби по математическому значению, а не по внутреннему состоянию числителя и знаменателя.

В `Main` создаётся с клавиатуры две дроби. После чего проводятся операции: с дробями, с целым числом и дробью, кэширование вещественного значения дробей, проверка равенства дробей, изменение дроби.

**<ins>Код реализации:</ins>**

```
interface FractionInterface {
    double getDoubleValue();
    void setNumerator(int numerator);
    void setDenominator(int denominator);
}
```

```
public class Fraction implements FractionInterface {
    private int numerator;  // числитель
    private int denominator; // знаменатель
    private Double cachedDoubleValue; // кэшированное вещественное значение
    private boolean isCacheValid; // флаг валидности кэша

    // Конструктор с числителем и знаменателем
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }
        this.denominator = denominator;
        this.numerator = numerator;
        positiveDenominator(); // нормализуем знаки
        invalidateCache(); // инвалидируем кэш при создании
        simplify();
    }

    // Геттеры
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    public double getDoubleValue() {
        if (!isCacheValid) {
            cachedDoubleValue = (double) numerator / denominator;
            isCacheValid = true;
        }
        return cachedDoubleValue;
    }

    // Сеттеры
    @Override
    public void setNumerator(int numerator) {
        this.numerator = numerator;
        invalidateCache();
        simplify();
    }

    @Override
    public void setDenominator(int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю!");
        }
        this.denominator = denominator;
        positiveDenominator();
        invalidateCache();
        simplify();
    }

    // Делаем знаменатель всегда положительным
    private void positiveDenominator() {
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // Инвалидация кэша
    private void invalidateCache() {
        isCacheValid = false;
        cachedDoubleValue = null;
    }

    // Упрощение дроби
    public void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        if (gcd > 1) {
            numerator /= gcd;
            denominator /= gcd;
        }
        invalidateCache();
    }

    // Вычисление НОД (наибольшего общего делителя)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Переопределение toString
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Метод сложения двух дробей
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод вычитания двух дробей
    public Fraction subtract(Fraction other) {
        int newNumerator = this.numerator * other.denominator - this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод умножения дробей
    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод деления дробей
    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно.");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод сложения дроби и целого числа
    public Fraction add(int number) {
        int newNumerator = this.numerator + number * this.denominator;
        return new Fraction(newNumerator, this.denominator);
    }

    // Метод вычитания дроби и целого числа
    public Fraction subtract(int number) {
        int newNumerator = this.numerator - number * this.denominator;
        return new Fraction(newNumerator, this.denominator);
    }

    // Метод умножения дроби и целого числа
    public Fraction multiply(int number) {
        int newNumerator = this.numerator * number;
        return new Fraction(newNumerator, this.denominator);
    }

    // Метод деления дроби на целое число
    public Fraction divide(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно.");
        }
        return new Fraction(this.numerator, this.denominator * number);
    }

    // Переопределение equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        return numerator * other.denominator == denominator * other.numerator;
    }
}
```

**<ins>Вывод на экран:</ins>**

Введите числитель: 8

Введите знаменатель: 24

Создана дробь: 1/3

Введите числитель второй дроби: 9

Введите знаменатель второй дроби: 11

Создана дробь: 9/11

Сложение дробей: 38/33

Вычитание дробей: -16/33

Умножение дробей: 3/11

Деление дробей: 11/27

Введите целое число для операции с первой дробью: 2

Сложение с числом: 7/3

Вычитание с числом: -5/3

Умножение на число: 2/3

Деление на число: 1/6

Вещественное значение первой дроби: 0.3333333333333333

Равенство первой и второй дроби: false

Введите новый числитель для первой дроби:

5

Введите новый знаменатель для первой дроби: 

25

Обновленная первая дробь: 1/5
***

### Задание 2 (Структурные шаблоны)

**<ins>Задача:</ins>**

Необходимо воспользоваться классом Кот и методом принимающим всех мяукающих из задачи 2.5.4. Необходимо таким образом передать кота в указанный метод, что бы после окончания его работы узнать сколько раз мяукал кот за время его работы. На рисунке показан пример работы. Перед вызовом метода создаем кота, отправляем ссылку на кота в метод, после окончания его работы выводим количество мяуканий на экран. Кота изменять нельзя.

Если раннее в вашем варианте не было Кота, то создайте
+ сущность Кот, которая описывается следующим образом:
    + Имеет Имя (строка)
    + Для создания необходимо указать имя кота.
    + Может быть приведен к текстовой форме вида: “кот: Имя”
    + Может помяукать, что приводит к выводу на экран следующего текста: “Имя: мяу!”, вызвать мяуканье можно без параметров.
+ интерфейс Мяуканье: разработайте метод, который принимает набор объектов способных мяукать и вызывает мяуканье у каждого объекта. Мяукающие объекты должны иметь метод со следующей сигнатурой: public void meow();

**<ins>Метод решения:</ins>**

Сначала для решения этой задачи создаётся класс `Cat` и интерфейс `Meowable`, как указано в примере.

Задача нтерфейса `Meowable` - объект, реализующий `Meowable`, должен иметь метод `meow()`.

Класс `Cat` имеет: поле `name`; конструктор, который требует для создания указать имя кота; возвращение строки: `“кот: Имя”`; реализует интерфейс `Meowable`: `“Имя: мяу!”`; геттер и сеттер.

Далее для выполнения условия задачи: кота изменять нельзя - мы создаём класс-декоратор `CatDecorator`, который оборачивает любой `Meowable` и добавляет подсчёт мяуканий на уровне декоратора.

В конце создаём класс `Funs`, который реализует метод `meowsCare(Meowable m, int number)`. Метод `meowsCare(Meowable m, int number)` проверяет объект `m` на `null` и вызывает метод `meow()` столько раз, сколько задано в переменной `number`.

В `Main` реализуем сценарий работы.

**<ins>Код реализации:</ins>**

```
public interface Meowable {
    void meow();
}
```

```
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
```

```
// Реализация шаблона декоратор
public class MeowCounterDecorator implements Meowable {
    private Meowable decoratedMeowable;
    private int meowCount;

    // Конструктор
    public MeowCounterDecorator(Meowable meowable) {
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
```

```
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
```

**<ins>Вывод на экран:</ins>**

Введите имя кота: Барсик

Сколько раз мяукает кот: 3

Барсик: мяу!

Барсик: мяу!

Барсик: мяу!

Кот мяукнул 3 раз(а)
***

### Задание 3.5 (Список)

**<ins>Задача:</ins>**

Составить программу, которая удаляет из списка L все элементы с указанным значением.

**<ins>Метод решения:</ins>**

Для решения этой задачи создаётся новый класс `MyList` и его интерфейс `GenericList<T>`.

Интерфейс `GenericList<T>` задаёт контракт: любая реализация списка должна поддерживать добавление элементов, удаление всех вхождений заданного значения, возвращать размер, проверять пустоту и уметь выдавать строковое представление. Использование обобщения <T> делает интерфейс универсальным — он может хранить объекты любого типа.

Класс `MyList` начинается с вложенного класса `Node<E>`, который является узлом списка. Так же класс имеет поля `head` - указатель на первый узел списка, `tail` - указатель на последний узел, `size` - количество элементов в списке.

Метод `add(T element)` создаёт новый узел с переданным `element`, если список пуст, новый узел становится и `head`, и `tail`, иначе прикрепляет новый узел к `tail.next` и обновляет `tail`, увеличивает `size`.

Метод `removeAll(T value)` это главный метод задачи — удаляет все элементы со значением `value`. В начале реализуется удаление подряд идущих голов - это покрывает случай, когда удаляемые элементы находятся в начале списка, для корректной работы используем сравнение `Objects.equals(x, y)`, которое корректно обрабатывает `null`. Далее идёт обработка случая опустевшего списка. Затем проходим по оставшемуся списку. В конце обновляем `tail`, ведь после возможных удалений последний элемент списка мог измениться.

Метод `size()` выводит длину списка, `isEmpty() ` проверяет список на пустоту, а `toString()` выводит строку.

В `Main` показывается работа нескольких сценариев: список Integer и список String - эти примеры проверяют типичные сценарии и пограничные случаи.

**<ins>Код реализации:</ins>**

```
interface GenericList<T> {
    void add(T element); // добавить элемент в конец
    int removeAll(T value); // удалить все элементы, равные value, вернуть число удалённых
    int size(); // количество элементов
    boolean isEmpty(); // пуст ли список
    String toString(); // строковое представление
}
```

```
import java.util.Objects;

public class MyList<T> implements GenericList<T> {
    // Внутренний класс узла
    private static class Node<E> {
        E data;
        Node<E> next;
        Node(E data) { this.data = data; }
    }

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    // Добавление элемента
    @Override
    public void add(T element) {
        Node<T> node = new Node<>(element);
        if (head == null) {
            head = tail = node;
        } else {
            tail.next = node;
            tail = node;
        }
        size++;
    }

    // Удаляет все элементы списка, равные value.
    @Override
    public int removeAll(T value) {
        int removed = 0;

        // Удаляем подряд идущие головы, равные value
        while (head != null && Objects.equals(head.data, value)) {
            head = head.next;
            removed++;
            size--;
        }

        // Если список стал пустым — обновляем tail и возвращаем
        if (head == null) {
            tail = null;
            return removed;
        }

        // Проходим по списку и удаляем последующие узлы, равные value
        Node<T> current = head;
        while (current.next != null) {
            if (Objects.equals(current.next.data, value)) {
                // пропускаем следующий узел
                current.next = current.next.next;
                removed++;
                size--;
            } else {
                current = current.next;
            }
        }

        // Обновляем tail на случай, если последний элемент(ы) были удалены
        tail = head;
        if (tail != null) {
            while (tail.next != null) tail = tail.next;
        }

        return removed;
    }

    // Размер списка
    @Override
    public int size() {
        return size;
    }

    // Проверка на пустоту
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    // Возвращение строки
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        Node<T> cur = head;
        boolean first = true;
        while (cur != null) {
            if (!first) sb.append(", ");
            sb.append(String.valueOf(cur.data));
            first = false;
            cur = cur.next;
        }
        sb.append(']');
        return sb.toString();
    }

}
```

**<ins>Вывод на экран:</ins>**

Какой список нужно создать?

1) Integer
   
2) String
   
Ваш выбор:

2

Сколько элементов в списке?

5
=Введите элемент=

qwerty

=Введите элемент=

qw

=Введите элемент=

er

=Введите элемент=

ty

=Введите элемент=

qwerty

Изначальный список: [qwerty, qw, er, ty, qwerty]

Какой элемент убрать?

qwerty

Удалено элементов: 2

Список после удаления: [qw, er, ty]

Размер: 3
***

### Задание 4.2 (Мап)

**<ins>Задача:</ins>**

На городской олимпиаде по информатике участникам было предложено выполнить 3 задания, каждое из которых оценивалось по 25-балльной шкале. Известно, что общее количество участников первого тура олимпиады не превосходит 250 человек. На вход программы подаются сведения о результатах олимпиады. В первой строке вводится количество участников N. Далее следуют N строк, имеющих следующий формат:

<Фамилия><Имя><Баллы>

Здесь<Фамилия>– строка, состоящая не более чем из 20 символов;<Имя>– строка, состоящая не более чем из 15 символов;<Баллы>– строка, содержащая три целых числа, разделенных пробелом, соответствующих баллам, полученным участником за каждое задание первого тура. При этом <Фамилия> и <Имя>, <Имя> и <Баллы> разделены одним пробелом. Примеры входных строк:

Петрова Ольга 25 18 16

Калиниченко Иван 14 19 15

Напишите программу, которая будет выводить на экран фамилию и имя участника, набравшего максимальное количество баллов. Если среди остальных участников есть ученики, набравшие такое же количество баллов, то их фамилии и имена также следует вывести. При этом имена и фамилии можно выводить в произвольном порядке.

**<ins>Метод решения:</ins>**

Для решения этой задачи создаётся отдельный класс `OlympiadResults`. Он имеет поле `participantScores` - HashMap, ключ: строка "Фамилия Имя", значение: суммарный балл (int). Map нужен для быстрого хранения и последующей итерации. И поле `maxScore` - текущий максимум набранных очков.

Далее создаётся метод  `loadFromFile`, который отвечает за работу с файлом. Метод открывает файл, считывает каждую строку, делит её по пробелам и записывает эти данные в массив, присваивает определённой переменной определённый элемент массива, в конце считает ключ и значения, которые добавляются в словарь, а так же определяет максимальный балл среди участников. Дополнительно в методе реализованны проверки на все неугодные случаи.

Затем создаётся метод `getTopParticipants`, который создаёт новый массив, проходится по всем записям в словаре, и если значение записи совпадает с переменной `maxScore`, то ключ записи добавляется в массив. В конце возвращает список участников с максимальным количеством баллов.

В самом конце создаётся геттер `getMaxScore`.

В `Main` реализуем сценарий работы.

**<ins>Код реализации:</ins>**

```
public class OlympiadResults {
    private Map<String, Integer> participantScores = new HashMap<>();
    private int maxScore = 0;

    // Метод считывает данные из файла, выводит их на экран и сохраняет в карту
    public void loadFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            int N = Integer.parseInt(br.readLine().trim()); // Чтение количества участников
            if (N <= 250 && N > 0) {
                System.out.println("\nСодержимое файла: ");

                for (int i = 0; i < N; i++) {
                    String line = br.readLine();
                    if (line == null || line.trim().isEmpty()) {
                        continue;
                    }
                    System.out.println(line); // выводим строку файла

                    String[] parts = line.trim().split("\\s+"); // делим по пробелам
                    if (parts.length != 5) {
                        System.out.println("Ошибка формата строки: " + line);
                        continue;
                    }

                    String surname = parts[0];
                    String name = parts[1];
                    int score1 = Integer.parseInt(parts[2]);
                    int score2 = Integer.parseInt(parts[3]);
                    int score3 = Integer.parseInt(parts[4]);

                    if (surname.length() > 20 || name.length() > 15 || (score1 > 25 || score1 < 0)
                            ||(score2 > 25 || score2 < 0) || (score3 > 25 || score3 < 0)) {
                        System.out.println("Ошибка формата строки: " + line);
                        continue;
                    }

                    int total = score1 + score2 + score3;
                    String key = surname + " " + name;
                    participantScores.put(key, total);

                    if (total > maxScore) {
                        maxScore = total;
                    }

                }
            }
            else {
                System.out.println("Число человек не соответствует условию");
            }

        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден: " + filename);
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка в формате чисел в файле: " + e.getMessage());
        }
    }

    // Метод возвращает список участников с максимальным количеством баллов
    public List<String> getTopParticipants() {
        List<String> winners = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : participantScores.entrySet()) {
            if (entry.getValue() == maxScore) {
                winners.add(entry.getKey());
            }
        }
        return winners;
    }

    // Геттер
    public int getMaxScore() {
        return maxScore;
    }
}
```

**<ins>Вывод на экран:</ins>**

Содержимое файла:

Панченко Андрей 24 18 19

Романов Иван 3 2 2

Гиниятов Фидан 15 23 18

Половников Тимофей 25 25 25

Участники с максимальным количеством баллов (75):

Половников Тимофей
***

### Задание 5.3 (Сет)

**<ins>Задача:</ins>**

Файл содержит текст на русском языке. Напечатать в алфавитном порядке все согласные буквы, которые входят ровно в одно слово.

**<ins>Метод решения:</ins>**

Для решения этой задачи создаётся класс `ConsonantsInOneWord`. В нём содержится несколько методов, коорые потребуются для решения этой задачи.

Сначала идёт объявление константы гласных букв с помощью `Set<Character> VOWELS`. Далее метод `getConsonantsFromWord(String word)`: удаляет небуквенные символы, после чего извлекает согласные буквы и выводит результат. Метод `processFile(String fileName)`: читает файл, выводит строки на экран, разбивает строки на слова, обрабатывает каждое слово и заполняет карты. В конце метод `findUniqueConsonants(Map<Character, Set<String>> consonantToWords)` перебирает все согласные и уникальные добавляет в результат, который в конце выводится.

В `Main` происходит открытие файла с текстом и дальнейшая работа с ним.

**<ins>Код реализации:</ins>**

```
import java.io.*;
import java.util.*;

public class ConsonantsInOneWord {

    // Множество гласных букв русского языка
    private static final Set<Character> VOWELS = new HashSet<>(Arrays.asList(
            'А', 'Е', 'Ё', 'И', 'О', 'У', 'Ы', 'Э', 'Ю', 'Я',
            'а', 'е', 'ё', 'и', 'о', 'у', 'ы', 'э', 'ю', 'я'
    ));

    // Метод для извлечения согласных букв из строки
    public static Set<Character> getConsonantsFromWord(String word) {
        Set<Character> consonants = new HashSet<>();
        word = word.replaceAll("[^а-яА-Я]", "");  // Удаляем все символы, не являющиеся буквами

        for (char c : word.toCharArray()) {
            if (!VOWELS.contains(c)) {
                consonants.add(c);
            }
        }
        return consonants;
    }

    // Метод для обработки файла и извлечения всех согласных
    public static Map<Character, Set<String>> processFile(String fileName) throws IOException {
        Map<Character, Set<String>> consonantToWords = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                // Выводим сам текст на экран
                System.out.println(line);

                String[] words = line.split("\\s+");  // Разбиваем текст на слова
                for (String word : words) {
                    Set<Character> consonants = getConsonantsFromWord(word);
                    for (Character consonant : consonants) {
                        consonantToWords.computeIfAbsent(consonant, k -> new HashSet<>()).add(word.toLowerCase());
                    }
                }
            }
        }
        return consonantToWords;
    }

    // Метод для поиска согласных, встречающихся ровно в одном слове
    public static Set<Character> findUniqueConsonants(Map<Character, Set<String>> consonantToWords) {
        Set<Character> result = new TreeSet<>();  // Используем TreeSet для сортировки по алфавиту

        for (Map.Entry<Character, Set<String>> entry : consonantToWords.entrySet()) {
            if (entry.getValue().size() == 1) {  // Если согласная встречается только в одном слове
                result.add(entry.getKey());
            }
        }
        return result;
    }
}
```

**<ins>Вывод на экран:</ins>**

Текст в файле:

Мороз и солнце; день чудесный!

Еще ты дремлешь, друг прелестный —

Пора, красавица, проснись:

Открой сомкнуты негой взоры

Навстречу северной Авроры,

Звездою севера явись!

Согласные буквы, встречающиеся ровно в одном слове (в алфавитном порядке):

З М Н П ш щ
***

### Задание 6.4 (Очередь)

**<ins>Задача:</ins>**

Проверить равенство участка очереди с i-го по j-й элемент (i < j).

**<ins>Метод решения:</ins>**

Для решения этой задачи создаётся класс `QueueEquality`. В нём метод `checkEquality(Queue<Integer> queue, int i, int j)` принимает очередь `queue`, а также два индекса 
`i` и `j`, для которых нужно проверить равенство элементов. Затем проверяем индексы на ошибочные случаи `i >= j || i < 0 || j >= queue.size()`. Используем метод `toArray()` для преобразования очереди в массив. Это облегчает работу с индексами, так как доступ к элементам массива осуществляется по индексам, что делает проверку проще. Проверка равенства: берём первый элемент в интервале — это элемент с индекса `i`, сравниваем каждый элемент от `i+1` до `j` с этим эталонным элементом. Если хотя бы один элемент не равен эталону, возвращаем `false`. Если все элементы равны эталону, возвращаем `true`.

В `Main` реализуем сценарий работы.

**<ins>Код реализации:</ins>**

```
import java.util.Queue;

public class QueueEquality {
    // Метод для проверки равенства сегмента очереди с i-го по j-й элемент
    public static boolean checkEquality(Queue<Integer> queue, int i, int j) {
        if (i >= j || i < 0 || j >= queue.size()) {
            throw new IllegalArgumentException("Неверные индексы. Убедитесь, что i < j и индексы в пределах очереди.");
        }

        // Преобразуем очередь в массив для удобства работы с индексами
        Integer[] array = queue.toArray(new Integer[0]);

        // Берём элемент с индекса i как эталон
        int reference = array[i];

        // Сравниваем элементы с i по j
        for (int k = i + 1; k <= j; k++) {
            if (!array[k].equals(reference)) {
                return false;  // Если хотя бы один элемент не равен эталону, возвращаем false
            }
        }
        return true;  // Все элементы равны эталону, возвращаем true
    }
}
```

**<ins>Вывод на экран:</ins>**

Сколько элементов в очереди?

5

=Введите элемент=

1

=Введите элемент=

2

=Введите элемент=

3

=Введите элемент=

3

=Введите элемент=

3

Изначальная очередь: [1, 2, 3, 3, 3]

Будем проверять равенство участка с i

2

по j

4

Элементы с индексов 2 по 4 равны.
***

### Задание 7.1 (Стрим)

**<ins>Задача:</ins>**

Необходимо написать стрим:
Дан набор объектов типа Point, необходимо взять все Point в разных координатах, убрать с одинаковыми X,Y, отсортировать по X, отрицательные Y сделать положительными и собрать это все в ломаную (объект типа Polyline)
Если раннее в вашем варианте не было задание с классом Point и Polyline, то написать их:
+ класс Point:
    + Координата Х: число.
    + Координата Y: число.
    + Может возвращать текстовое представление вида “{X;Y}”.
+ класс Line (Линия), расположенная на двумерной плоскости, которая описывается:
    + Координата начала: Точка
    + Координата конца: Точка
    + Может возвращать текстовое представление вида “Линия от {X1;Y1} до {X2;Y2}”
+ класс Polyline (Ломаная), которая будет представлять собой ломаную линию. Ломаная линия представляет собой набор следующих характеристик:
    + Имеет массив Точек, через которые линия проходит.
    + Может быть приведена к строковой форме вида “Линия [Т1,T2,…,TN]”, где TN – это результат приведения к строке Точки с номером N

**<ins>Метод решения:</ins>**

Сначала для решения этой задачи создаются классы `Point` и `Polyline`.

Класс `Point` имеет поля `x` и `y` для координат, конструктор, геттреры и метод `toString` для возвращения строки. Так же в классе создаются два метода `equals` и `hashCode` - они потребуются в дальнейшем для корректного удалении дубликатов во время работы со `stream()`.

Класс `Polyline` имеет поле `points` - массив, в котором будут содержаться все точки; конструктор и метод `toString()` для возвращения строки.

В `Main` пользователя просят ввести количество создаваемых точек. На основании этих данных массив `points` заполняется точками, которые удовлетворяют поставленным условиям. Далее создаёам объект `polyline`, который принимает массив `points`, а уже с массивом `points` работает `stream()` - начинаем с начального конвеера, продолжаем промежуточным конвеером, заканчиваем конечным конвеером. В конце получивщаяся ломаная линия выводится на экран.

**<ins>Код реализации:</ins>**

```
import java.util.Objects;

public class Point {
    private double x;
    private double y;

    // Конструктор
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // Геттеры
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    // Возвращение строки
    @Override
    public String toString() {
        return "{" + x + ";" + y + "}";
    }

    // Переопределение equals и hashCode для корректного удаления одинаковых точек по X и Y
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Point point = (Point) obj;
        return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
```

```
import java.util.List;

public class Polyline {
    private List<Point> points;

    // Конструктор
    public Polyline(List<Point> points) {
        this.points = points;
    }

    // Возвращение строки
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Линия [");
        for (int i = 0; i < points.size(); i++) {
            sb.append(points.get(i).toString());
            if (i < points.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
```

```
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
```

**<ins>Вывод на экран:</ins>**

Сколько точек создать? 5

Введите координаты в формате: X Y

1 2

Введите координаты в формате: X Y

-3 -5

Введите координаты в формате: X Y

10 -6

Введите координаты в формате: X Y

13 12

Введите координаты в формате: X Y

2 5

Линия [{-3.0;5.0}, {1.0;2.0}, {2.0;5.0}, {10.0;6.0}, {13.0;12.0}]
***

### Задание 7.2 (Стрим)

**<ins>Задача:</ins>**

Дан текстовый файл со строками, содержащими имя человека и его номер в следующей форме:

Вася:5

Петя:3

Аня:5

Номера людей могут повторяться. У каких-то людей может не быть номера.
Необходимо написать стрим выполняющую следующее:
читаются все люди из файла, все имена приводится к нижнему регистру, но с первой буквой в верхнем регистре, убираем из перечня всех людей без номеров, а имена оставшихся группируются по их номеру:
[5:[Вася, Аня], 3:[Петя]]

**<ins>Метод решения:</ins>**

Для решения этой задачи создадим класс `Person`. Он имеет поля `name` и `number`, конструктор, геттеры, метод `toString` для возвращения строки и метод `fromString` для преобразования строки. Метод `fromString`: статический метод, который принимает строку вида "Имя:Номер", разделяет её на части (имя и номер) и создает объект Person. Важно, что если номер отсутствует или его невозможно преобразовать в целое число, мы ставим null для поля number.

Затем создаём вспомогательный класс `Capitalize` с методом `capitalize()`. Этот метод принимает строку и преобразует её так, чтобы первая буква была заглавной, а все остальные - строчными.

В `Main` сначала считывается заданный файл, а его содержимое печатается на экран. Далее файл снова считывается, но на этот раз добавляются дополнительные шаги обработки: каждая строка файла преобразуется в объект Person с помощью метода fromString(); затем мы фильтруем людей, у которых нет номера; после фильтрации мы собираем результат в список. Далее группируем людей: распределяем их по номеру, извлекая номер из каждого объекта; используем `TreeMap`, чтобы результат был отсортирован по номерам; для каждого объекта `Person` извлекаем имя, приводим его к нижнему регистру с первой заглавной буквой, используя метод `capitalize()`, и собираем эти имена в список - для этого используется метод `Collectors.mapping()`, который позволяет применить дополнительную трансформацию перед сбором в коллекцию. В конце выводим итоговый отсортированный список на экран.

**<ins>Код реализации:</ins>**

```
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
```

```
public class Capitalize {
    // Метод для преобразования первого символа в верхний регистр
    public String capitalize(String name) {
        if (name == null || name.isEmpty()) {
            return name;
        }
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }
}
```

```
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
```

**<ins>Вывод на экран:</ins>**

Содержимое файла:

Егор:1

Иван:3

Фёдор:4

Андрей:9

Алексей:3

Тимофей:3

Фидан:4

Отсортированный список: {1=[Егор], 3=[Иван, Алексей, Тимофей], 4=[Фёдор, Фидан], 9=[Андрей]}
***

## Вспомогательные методы

+ `public int readInt(Scanner scanner)`

Что делает:

Читает из Scanner целое число, пока пользователь не введёт корректное значение.

Как работает (пошагово):
- Заходит в бесконечный цикл while (true).
- Печатает приглашение "Введите целое число: ".
- Проверяет scanner.hasNextInt() — есть ли следующий токен, который можно распарсить как int.
- Если true: читает int num = scanner.nextInt(); и возвращает num.
- Иначе: печатает сообщение об ошибке и вызывает scanner.next() — чтобы "съесть" неверный токен (иначе hasNextInt() будет снова false и цикл застрянет).

Примеры:

При вводе 42 вернёт 42; при вводе abc — попросит ввести ещё раз.

Код:
```
public int readInt(Scanner scanner) {
        while (true) {
            System.out.print("Введите целое число: ");
            if (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                return num;
            } else {
                System.out.println("Ошибка: введите целое число!");
                scanner.next(); // очищаем неверный ввод
            }
        }
    }
```
