package dev.krk.emulator.chipsekiz.opcodes;

import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;

public class Op8XY0 extends Opcode {
    public Op8XY0(int vx, int vy) {
        super(Optional.of(vx), Optional.of(vy), Optional.empty());
        checkArgument(vx >= 0 && vx <= 0xF, "register index out of bounds");
        checkArgument(vy >= 0 && vy <= 0xF, "register index out of bounds");
    }

    public int vx() {
        return super.getVx().get();
    }

    public int vy() {
        return super.getVy().get();
    }

    @Override public String toString() {
        return "8" + Integer.toHexString(vx()) + Integer.toHexString(vy()) + "0";
    }
}
