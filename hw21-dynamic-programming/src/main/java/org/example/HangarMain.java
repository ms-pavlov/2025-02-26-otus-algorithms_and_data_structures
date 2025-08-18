package org.example;

public class HangarMain {
    public static void main(String[] args)
    {
        int h = 10, w = 10;
        int qty = 10;
        Hangar hangar = new Hangar(h, w, qty);
        System.out.println(hangar);
        System.out.println("Площадь = " + hangar.getMaxArea());
    }

}
