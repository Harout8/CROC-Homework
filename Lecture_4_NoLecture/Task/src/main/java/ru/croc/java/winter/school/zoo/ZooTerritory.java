package ru.croc.java.winter.school.zoo;

import ru.croc.java.winter.school.zoo.tracking.location.Position;

/**
 * Территория зоопарка
 */
public class ZooTerritory {
    private double x1;
    private double y1;
    private double x2;
    private double y2;


    /**
     * Территория зоопарка
     */
    public ZooTerritory(double x1, double y1, double x2, double y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }


    /**
     * Проверяет находится ли объект с позицией position на территории зоопарка.
     *
     * @param position позиция
     * @return true, сотрудник в зоопарке
     */
    public boolean insideTheZoo(Position position) {
        double x = position.x;
        double y = position.y;

        return ((x > x1)
                & (x < x2)
                & (y > y1)
                & (y < y2));
    }


    public double getX1() {
        return x1;
    }

    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }

    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public double getY2() {
        return y2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }
}
