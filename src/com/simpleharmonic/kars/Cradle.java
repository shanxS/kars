package com.simpleharmonic.kars;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

@Slf4j
public class Cradle {

    private static final Integer INVALID = -1;
    private static final Integer BEGIN = 0;

    private int currentInputPos = INVALID;
    private String currentLine = null;
    @Getter private Character look;

    public void getChar() {

        if (currentInputPos == INVALID) {
            Scanner s = new Scanner(System.in);
            while (!s.hasNext());
            currentLine = s.next();
            currentInputPos = BEGIN;
        }

        look = currentLine.charAt(currentInputPos);
        log.info("Read " + look + " currentPos " + currentInputPos);

        ++currentInputPos;
        if (currentInputPos >= currentLine.length()) {
            currentInputPos = INVALID;
        }
    }

    public void error(String e) {
        System.out.println("Error: " + e);
    }

    public void abort(String a) {
        error(a);
        System.exit(1);
    }

    public void expected(String e) {
        abort(e + " Expected");
    }

    public void match(Character x) {
        if (look.equals(x)) getChar();
        else expected("'" + x + "'");
    }

    public boolean isAlpha(Character c) {
        return Character.isLetter(c);
    }

    public boolean isDigit(Character c) {
        return Character.isDigit(c);
    }

    public Character getName() {
        if (!isAlpha(look)) expected("Name");
        getChar();
        return Character.toUpperCase(look);
    }

    public Character getNum() {
        if(!isDigit(look)) expected("Integer");
        Character retValue = look;
        getChar();
        return retValue;
    }

    public void emit(String s) {
        System.out.print("\t " + s);
    }

    public void emitLn(String s) {
        System.out.println("\t " + s);
    }
}
