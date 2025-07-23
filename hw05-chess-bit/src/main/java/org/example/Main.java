package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("=====   КОРОЛЬ   =====");
        System.out.println();
        Test kingTest = new Tester(KingBitBoard::new, 10, "king/");
        kingTest.run();

        System.out.println();
        System.out.println("=====   КОНЬ   =====");
        System.out.println();

        Test knightTest = new Tester(KnightBitBoard::new, 10, "knight/");
        knightTest.run();

        System.out.println();
        System.out.println("=====   ЛАДЬЯ   =====");
        System.out.println();

        Test rookTest = new Tester(RookBitBoard::new, 10, "rook/");
        rookTest.run();

        System.out.println();
        System.out.println("=====   СЛОН   =====");
        System.out.println();

        Test bishopTest = new Tester(BishopBitBoard::new, 10, "bishop/");
        bishopTest.run();

        System.out.println();
        System.out.println("=====   ФЕРЗЬ   =====");
        System.out.println();

        Test queenTest = new Tester(QueenBitBoard::new, 10, "queen/");
        queenTest.run();

    }
}