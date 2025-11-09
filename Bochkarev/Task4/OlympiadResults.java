package ru.Bochkarev.Task4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

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
