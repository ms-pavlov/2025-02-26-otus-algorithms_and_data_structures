package org.example;

import org.jline.terminal.Terminal;
import org.jline.utils.InfoCmp;

public abstract class Bitboard {
    protected long bb = 0L;

    public Bitboard(String s) {
        int point;
        s = s.toUpperCase();
        Terminal terminal = Console.TERMINAL;
        try {
            point = Integer.parseInt(s);
        } catch (Exception exception) {
            try {
                point = (s.charAt(0) - 'A') + (s.charAt(1) - '1') * 8;
            } catch (Exception ex) {
                terminal.puts(InfoCmp.Capability.cursor_address, 0, 10);
                System.out.println(ex.getMessage());
                return;
            }
        }
        bb = 1L << point;
    }

    public abstract void setSteps();

    public long getBoard() {
        return bb;
    }

    public int getCount() {
        int count1 = getOnesRev1(bb);
        int count4 = getOnesRev4(bb);
        if (count1 != count4) {
            throw new RuntimeException("методы вычисления количества единиц дают разное значение");
        }
        return getOnesRev1(bb);
    }


    public void print(int row) {
        String backgroundColor;
        Terminal terminal = Console.TERMINAL;

        terminal.puts(InfoCmp.Capability.cursor_address, 0, row);
        boolean color = false;
        for (int i = 8; i > 0; i--) {
            backgroundColor = ConsoleColors.WHITE_BACKGROUND;
            System.out.print(backgroundColor + i + ":  ");
            for (int j = 0; j < 8; j++) {
                if (color)
                    backgroundColor = ConsoleColors.YELLOW_BACKGROUND;
                else
                    backgroundColor = ConsoleColors.WHITE_BACKGROUND;
                if (Long.compareUnsigned(bb & (1L << ((i - 1) * 8 + j)), 0L) > 0) {
                    backgroundColor = ConsoleColors.RED_BACKGROUND;
                }
                System.out.print(backgroundColor + " " + String.format("%02d", (i - 1) * 8 + j) + " ");
                color = !color;
            }
            color = !color;
            System.out.println(ConsoleColors.WHITE_BRIGHT);
        }
        backgroundColor = ConsoleColors.WHITE_BACKGROUND;
        System.out.print(backgroundColor + "      ");
        for (int i = 0; i < 8; i++) {
            System.out.print(backgroundColor + (char) ('A' + i) + "   ");
        }
        System.out.println(ConsoleColors.WHITE_BRIGHT);
        terminal.puts(InfoCmp.Capability.cursor_address, 0, 10 + row);
    }

    private int getOnesRev1(long num) {
        int count = 0;
        long board = num;
        while (Long.compareUnsigned(board, 0L) > 0) {
            if ((board & 1L) != 0) {
                count++;
            }
            board = Long.divideUnsigned(board, 2);
        }
        return count;
    }

    private int getOnesRev4(long num) {
        int[] ones = new int[16];
        for (int i = 0; i < 16; i++)
        {
            ones[i] = getOnesRev1(i);
        }

        int count = 0;
        long board = num;
        long mask = 0x0000_0000_0000_000FL;
        while (Long.compareUnsigned(board, 0L) > 0) {
            count += ones[(int) (board & mask)];
            board = Long.divideUnsigned(board, 16);
        }
        return count;
    }

}
