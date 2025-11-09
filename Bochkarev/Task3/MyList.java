package ru.Bochkarev.Task3;

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
