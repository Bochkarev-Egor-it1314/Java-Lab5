package ru.Bochkarev.Task7;

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