package ru.Bochkarev.Task3;

interface GenericList<T> {
    void add(T element); // добавить элемент в конец
    int removeAll(T value); // удалить все элементы, равные value, вернуть число удалённых
    int size(); // количество элементов
    boolean isEmpty(); // пуст ли список
    String toString(); // строковое представление
}
