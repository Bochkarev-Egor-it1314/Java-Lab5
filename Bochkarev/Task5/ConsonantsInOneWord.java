package ru.Bochkarev.Task5;

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
    public static Map<Character, Set<String>> processFile(String filename) throws IOException {
        Map<Character, Set<String>> consonantToWords = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
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