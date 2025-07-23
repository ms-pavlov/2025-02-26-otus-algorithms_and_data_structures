package org.example;

public class KingBitBoard extends Bitboard{

    public KingBitBoard(String s) {
        super(s);
    }

    @Override
    public void setSteps() {

        long mask = (bb & 0x7F7F7F7F7F7F7F7FL) << 1;
        mask |= Long.divideUnsigned(bb & 0xFEFE_FEFE_FEFE_FEFEL, 2L);
        mask |= (bb << 8);
        mask |= Long.divideUnsigned(bb, 256);
        mask |= Long.divideUnsigned(bb & 0xFEFEFEFEFEFEFEFEL, 512);
        mask |= (bb & 0xFEFEFEFEFEFEFEFEL) << 7;
        mask |= (bb & 0x7F7F7F7F7F7F7F7FL) << 9;
        mask |= Long.divideUnsigned(bb & 0x7F7F7F7F7F7F7F7FL, 128);
        bb = mask;

    }
}
