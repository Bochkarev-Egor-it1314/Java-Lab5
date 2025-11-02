package ru.Bochkarev.Task1;

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
