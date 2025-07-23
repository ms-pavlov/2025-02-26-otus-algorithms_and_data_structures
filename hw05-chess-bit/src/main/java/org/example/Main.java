package org.example;

public class Main {
    public static void main(String[] args) {

        System.out.println("=====   КОРОЛЬ   =====");
        Test kingTest = new Tester(KingBitBoard::new, 10, "king/");
        kingTest.run();
        System.out.println("=====   КОНЬ   =====");
        Test knightTest = new Tester(KnightBitBoard::new, 10, "knight/");
        knightTest.run();
        System.out.println("=====   ЛАДЬЯ   =====");
        Test rookTest = new Tester(RookBitBoard::new, 10, "rook/");
        rookTest.run();
        System.out.println("=====   СЛОН   =====");
        Test bishopTest = new Tester(BishopBitBoard::new, 10, "bishop/");
        bishopTest.run();
        System.out.println("=====   ФЕРЗЬ   =====");
        Test queenTest = new Tester(QueenBitBoard::new, 10, "queen/");
        queenTest.run();

    }
}