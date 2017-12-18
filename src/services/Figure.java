package services;

public class Figure {

    private int a;
    private int h;

    public Figure() {
    }

    public Figure(int a, int h) {
        this.a = a;
        this.h = h;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    @Override
    public String toString() {
        return "Figure{" + "a=" + a + ", h=" + h + '}';
    }

    public double volume() {
        double aa = (double) this.a;
        double hh = (double) this.h;

        double area6 = (3 * a * a * Math.sqrt(3)) / 2;
        double volume = Math.round(area6 * hh);

        return volume;
    }

}
