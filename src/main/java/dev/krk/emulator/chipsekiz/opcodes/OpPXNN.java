package dev.krk.emulator.chipsekiz.opcodes;

import com.google.common.primitives.UnsignedBytes;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public abstract class OpPXNN extends Opcode {
    private final byte p;

    public OpPXNN(int p, int vx, byte imm) {
        super(Optional.of(vx), Optional.empty(), Optional.of(UnsignedBytes.toInt(imm)));
        checkArgument(p >= 0 && p <= 0xF, "p out of bounds");
        checkArgument(vx >= 0 && vx <= 0xF, "register index out of bounds");
        checkArgument(imm >= Byte.MIN_VALUE && imm <= Byte.MAX_VALUE, "immediate out of bounds");

        this.p = (byte) p;
    }

    public int vx() {
        return super.getVx().get();
    }

    public byte imm() {
        return super.getAddress().get().byteValue();
    }

    @Override public short getValue() {
        return (short) (p << 12 | vx() << 8 | (imm() & 0xFF));
    }

    @Override public String encode() {
        return String.format("%1X%1X%02X", p, vx(), imm());
    }
}

