package dev.krk.emulator.chipsekiz.opcodes;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class OpPXQR extends Opcode {
    private final byte p;
    private final byte q;
    private final byte r;

    public OpPXQR(int p, int vx, int q, int r) {
        super(Optional.of(vx), Optional.empty(), Optional.empty());
        checkArgument(p >= 0 && p <= 0xF, "p out of bounds");
        checkArgument(vx >= 0 && vx <= 0xF, "register index out of bounds");
        checkArgument(q >= 0 && q <= 0xF, "q out of bounds");
        checkArgument(r >= 0 && r <= 0xF, "r out of bounds");

        this.p = (byte) p;
        this.q = (byte) q;
        this.r = (byte) r;
    }

    public int vx() {
        return super.getVx().get();
    }

    @Override public short getValue() {
        return (short) (p << 12 | vx() << 8 | q << 4 | r);
    }

    @Override public String toString() {
        return String.format("%1X%1X%1X%1X", p, vx(), q, r);
    }
}
