package dev.krk.emulator.chipsekiz.opcodes;

/**
 * Skips the next instruction if VX equals VY.
 */
public class Op5XY0 extends OpPXYQ {
    public Op5XY0(int vx, int vy) {
        super(5, vx, vy, 0);
    }
}
