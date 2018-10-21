package com.codingvalhalla.meredith.evidencevozidel.automobily;

public enum Barva {
    WHITE("Bila"),
    BLACK("Cerna"),
    BLUE("Modra"),
    RED("Cervena"),
    GRAY("Seda"),
    GREEN("Zelena"),
    YELLOW("Zluta"),
    SILVER("Stribrna");

    private final String nazev;

    private Barva(String nazev) {
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
