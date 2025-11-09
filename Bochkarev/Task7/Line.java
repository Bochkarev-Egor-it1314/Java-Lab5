package ru.Bochkarev.Task7;

public class Line {
    private Point start;
    private Point end;

    // Конструктор
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    // Геттеры
    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    // Возвращение строки
    @Override
    public String toString() {
        return "Линия от {" + start.toString() + "} до {" + end.toString() + "}";
    }
}