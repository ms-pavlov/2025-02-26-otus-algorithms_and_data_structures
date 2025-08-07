package org.example;

public class KnightBitBoard extends Bitboard{

    public KnightBitBoard(String s) {
        super(s);
    }

    @Override
    public void setSteps() {
        long mask = (bb & 0x7F7F7F7F7F7F7F7FL) << 17;
        mask |=  (bb & 0x3F3F_3F3F_3F3F_3F3FL) << 10;
        mask |=  Long.divideUnsigned(bb & 0x3F3F_3F3F_3F3F_3F3FL, 64);
        mask |=  Long.divideUnsigned(bb & 0x7F7F7F7F7F7F7F7FL, 32768);

        mask |= (bb & 0xFEFE_FEFE_FEFE_FEFEL) << 15;
        mask |= (bb & 0xFCFC_FCFC_FCFC_FCFCL) << 6;
        mask |= Long.divideUnsigned(bb & 0xFCFC_FCFC_FCFC_FCFCL, 1024);
        mask |= Long.divideUnsigned(bb & 0xFEFE_FEFE_FEFE_FEFEL, 131072);
        bb = mask;
    }
}
