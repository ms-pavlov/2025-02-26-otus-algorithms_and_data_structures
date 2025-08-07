package org.example;

public class BishopBitBoard extends Bitboard{

    public BishopBitBoard(String s) {
        super(s);
    }

    @Override
    public void setSteps() {
        long mask = 0x000_0000_0000_0000L;
        long rightTopStep = (bb & 0x7F7F7F7F7F7F7F7FL) << 9;
        while (Long.compareUnsigned(rightTopStep, 0L) > 0) {
            mask |= rightTopStep;
            rightTopStep = (rightTopStep & 0x7F7F7F7F7F7F7F7FL) << 9;
        }

        long rightBottomStep = Long.divideUnsigned(bb & 0x7F7F7F7F7F7F7F7FL, 128);
        while (Long.compareUnsigned(rightBottomStep, 0L) > 0) {
            mask |= rightBottomStep;
            rightBottomStep = Long.divideUnsigned(rightBottomStep & 0x7F7F7F7F7F7F7F7FL, 128);
        }

        long leftTopStep = (bb & 0xFEFEFEFEFEFEFEFEL) << 7;
        while (Long.compareUnsigned(leftTopStep, 0L) > 0) {
            mask |= leftTopStep;
            leftTopStep = (leftTopStep & 0xFEFEFEFEFEFEFEFEL) << 7;
        }

        long leftBottomStep = Long.divideUnsigned(bb & 0xFEFEFEFEFEFEFEFEL, 512);
        while (Long.compareUnsigned(leftBottomStep, 0L) > 0) {
            mask |= leftBottomStep;
            leftBottomStep = Long.divideUnsigned(leftBottomStep & 0xFEFEFEFEFEFEFEFEL, 512);
        }

        bb = mask;
    }
}
