package com.codingvalhalla.meredith.evidencevozidel.utills;

import java.util.Objects;

/**
 *
 * @author Meredith
 */
public class LoznaPlocha implements java.io.Serializable{

    private int[] rozmery = new int[3];

    /**
     * Konstruktor lozne plochy
     *
     * @param hloubka v milimetrech (mm)
     * @param vyska v milimetrech (mm)
     * @param sirka v milimetrech (mm)
     */
    public LoznaPlocha(int hloubka, int vyska, int sirka) {
        rozmery[0] = hloubka;
        rozmery[1] = vyska;
        rozmery[2] = sirka;
    }

    public LoznaPlocha(int[] rozmery) {
        if (rozmery.length != 3) {
            throw new IllegalArgumentException("Zadan spatny pocet rozmeru");
        }
        this.rozmery = rozmery;
    }

    public void setHloubka(int hloubka) {
        rozmery[0] = hloubka;
    }

    public void setVyska(int vyska) {
        rozmery[1] = vyska;
    }

    public void setSirka(int sirka) {
        rozmery[2] = sirka;
    }

    public int getHloubka() {
        return rozmery[0];
    }

    public int getVyska() {
        return rozmery[1];
    }

    public int getSirka() {
        return rozmery[2];
    }

    public void setRozmery(int[] rozmery) {
        if (rozmery.length != 3) {
            throw new IllegalArgumentException("Zadan spatny pocet rozmeru");
        }
        this.rozmery = rozmery;
    }

    public void setRozmery(int hloubka, int vyska, int sirka) {
        rozmery[0] = hloubka;
        rozmery[1] = vyska;
        rozmery[2] = sirka;
    }

    public int[] getRozmery() {
        return rozmery;
    }

    @Override
    public String toString() {
        return "[" + rozmery[0] + " mm X " + rozmery[1] + "mm X " + rozmery[2] + " mm]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        LoznaPlocha other = (LoznaPlocha) obj;
        boolean hloubka = this.rozmery[0] != other.rozmery[0];
        boolean vyska = this.rozmery[1] != other.rozmery[1];
        boolean sirka = this.rozmery[2] != other.rozmery[2];

        if (hloubka && vyska && sirka) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(rozmery);
    }

}
