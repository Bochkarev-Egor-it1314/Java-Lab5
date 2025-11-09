package ru.Bochkarev.Task6;

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
