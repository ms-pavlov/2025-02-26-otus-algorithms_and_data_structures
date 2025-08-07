package org.example;

import com.google.common.base.Charsets;
import com.google.common.io.Resources;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Function;

public class Tester implements Test{

    private final Function<String, Bitboard> bitBoard;
    private final int count;
    private final String path;

    public Tester(Function<String, Bitboard> bitBoard, int count, String path) {
        this.bitBoard = bitBoard;
        this.count = count;
        this.path = path;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            String in = getFirstLine(path + "test." + i + ".in");
            List<String> out = readLines(path + "test." + i + ".out");

            if(out.size() != 2) {
                throw new RuntimeException();
            }
            long expectCount = Integer.parseInt(out.get(0).trim());
            long expectBoard = Long.parseUnsignedLong(out.get(1).trim());

            Bitboard board = bitBoard.apply(in);
            board.setSteps();

            long result = board.getBoard();
            int count = board.getCount();

            System.out.println("Тест для позиции = " + in);
            System.out.println("Ожидаемой значение " + Long.toUnsignedString(expectBoard) + ", результат " + Long.toUnsignedString(result));
            System.out.println("Ожидаемой количество ходов " + expectCount + ", результат " + count);
            System.out.println("Тест " + ((expectBoard == result && expectCount == count) ? ConsoleColors.GREEN + "ПРОЙДЕН"  : ConsoleColors.RED + "НЕ ПРОЙДЕН") + ConsoleColors.WHITE_BRIGHT);
            board.print(0);
        }
    }

    private String getFirstLine(String path) {
        try {
            URL url = Resources.getResource(path);
            return Resources.toString(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private List<String> readLines(String path) {
        try {
            URL url = Resources.getResource(path);
            return Resources.readLines(url, Charsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
