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
import dev.krk.emulator.chipsekiz.opcodes.Op8XY1;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY2;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY3;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY4;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY5;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY6;
import dev.krk.emulator.chipsekiz.opcodes.Op8XY7;
import dev.krk.emulator.chipsekiz.opcodes.Op8XYE;
import dev.krk.emulator.chipsekiz.opcodes.Op9XY0;
import dev.krk.emulator.chipsekiz.opcodes.OpANNN;
import dev.krk.emulator.chipsekiz.opcodes.OpBNNN;
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
                switch (value & 0xF) {
                    case 0:
                        return Optional.of(new Op8XY0((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 1:
                        return Optional.of(new Op8XY1((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 2:
                        return Optional.of(new Op8XY2((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 3:
                        return Optional.of(new Op8XY3((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 4:
                        return Optional.of(new Op8XY4((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 5:
                        return Optional.of(new Op8XY5((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 6:
                        return Optional.of(new Op8XY6((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 7:
                        return Optional.of(new Op8XY7((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                    case 0xE:
                        return Optional.of(new Op8XYE((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                }
            case 9:
                switch (value & 0xF) {
                    case 0:
                        return Optional.of(new Op9XY0((value & 0xF00) >> 8, (value & 0xF0) >> 4));
                }
            case 0xA:
                return Optional.of(new OpANNN(value & 0xFFF));
            case 0xB:
                return Optional.of(new OpBNNN(value & 0xFFF));
        }

        return Optional.empty();
    }
}
