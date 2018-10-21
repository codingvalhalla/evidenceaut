package com.codingvalhalla.meredith.evidencevozidel.automobily;

/**
 *
 * @author Meredith
 */
public enum VozidloEnum {
    DODAVKA("dodavka"),
    OSOBNI_AUTOMOBIL("osobni automobil"),
    NAKLADNI_AUTOMOBIL("nakladni automobil");

    private final String nazev;

    private VozidloEnum(String nazev) {
        this.nazev = nazev;
    }

    public String getNazev() {
        return nazev;
    }

    @Override
    public String toString() {
        return nazev;
    }

}
