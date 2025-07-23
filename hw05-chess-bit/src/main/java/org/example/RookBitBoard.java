package org.example;

public class RookBitBoard extends Bitboard {

    public RookBitBoard(String s) {
        super(s);
    }

    @Override
    public void setSteps() {
        long mask = 0x000_0000_0000_0000L;
        long rightStep = (bb & 0x7F7F7F7F7F7F7F7FL) << 1;
        while (Long.compareUnsigned(rightStep, 0L) > 0) {
            mask |= rightStep;
            rightStep = (rightStep & 0x7F7F7F7F7F7F7F7FL) << 1;
        }
        long leftStep = Long.divideUnsigned(bb & 0xFEFE_FEFE_FEFE_FEFEL, 2L);
        while (Long.compareUnsigned(leftStep, 0L) > 0) {
            mask |= leftStep;
            leftStep = Long.divideUnsigned(leftStep & 0xFEFE_FEFE_FEFE_FEFEL, 2L);
        }
        long topStep = bb << 8;
        while (Long.compareUnsigned(topStep, 0L) > 0) {
            mask |= topStep;
            topStep = topStep  << 8;
        }

        long bottomStep = Long.divideUnsigned(bb, 256);
        while (Long.compareUnsigned(bottomStep, 0L) > 0) {
            mask |= bottomStep;
            bottomStep = Long.divideUnsigned(bottomStep, 256);
        }

        bb = mask;

    }
}
