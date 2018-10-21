package com.codingvalhalla.meredith.evidencevozidel.automobily;

public enum Palivo {
    GASOLINE("Benzin"),
    DIESEL("Motorova Nafta"),
    ELECTRO("Elektrika"),
    LPG("LPG"),
    ALCOHOL("Alkohol");
    private final String nazev;

    private Palivo(String nazev) {
        this.nazev = nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
