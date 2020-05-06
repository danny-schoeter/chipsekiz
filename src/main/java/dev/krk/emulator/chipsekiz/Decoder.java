package dev.krk.emulator.chipsekiz;



import dev.krk.emulator.chipsekiz.opcodes.Op00E0;
import dev.krk.emulator.chipsekiz.opcodes.Op00EE;
import dev.krk.emulator.chipsekiz.opcodes.Op0NNN;
import dev.krk.emulator.chipsekiz.opcodes.Op1NNN;
import dev.krk.emulator.chipsekiz.opcodes.Op2NNN;
import dev.krk.emulator.chipsekiz.opcodes.Op3XNN;
import dev.krk.emulator.chipsekiz.opcodes.Op4XNN;
import dev.krk.emulator.chipsekiz.opcodes.Op5XY0;
import dev.krk.emulator.chipsekiz.opcodes.Op6XNN;
import dev.krk.emulator.chipsekiz.opcodes.Op7XNN;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY0;
import dev.krk.emulator.chipsekiz.opcodes.Opcode;

import java.util.Optional;

public class Decoder {
    public Optional<Opcode> decode(short value) {
        int b0 = (value & 0xF000) >> 12;
        switch (b0) {
            case 0:
                if (value == 0x00E0) {
                    return Optional.of(new Op00E0());
                }
                if (value == 0x00EE) {
                    return Optional.of(new Op00EE());
                }

                return Optional.of(new Op0NNN(value & 0xFFF));
            case 1:
                return Optional.of(new Op1NNN(value & 0xFFF));
            case 2:
                return Optional.of(new Op2NNN(value & 0xFFF));
            case 3:
                return Optional.of(new Op3XNN((value & 0xF00) >> 8, value & 0xFF));
            case 4:
                return Optional.of(new Op4XNN((value & 0xF00) >> 8, value & 0xFF));
            case 5:
                if ((value & 0xF) == 0) {
                    return Optional.of(new Op5XY0((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                }
                break;
            case 6:
                return Optional.of(new Op6XNN((value & 0xF00) >> 8, value & 0xFF));
            case 7:
                return Optional.of(new Op7XNN((value & 0xF00) >> 8, value & 0xFF));
            case 8:
                byte b3 = (byte) (value & 0xF);
                switch (b3) {
                    case 0:
                        return Optional.of(new Op8XY0((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                }

        }

        return Optional.empty();
    }
}