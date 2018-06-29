package com.simpleharmonic.kars;

public class Main {
    private final Cradle cradle;
    private final MathParserTranslator mpt;

    Main() {
        System.setProperty("org.slf4j.simpleLogger.logFile", "System.out");
        cradle = new Cradle();
        mpt = new MathParserTranslator(cradle);
        init();
    }

    public static void main(String[] args) {
        new Main();
    }

    private void init() {
        cradle.getChar();
        mpt.expression();
    }
}
