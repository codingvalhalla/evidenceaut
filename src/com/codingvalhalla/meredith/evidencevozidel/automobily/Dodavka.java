package com.codingvalhalla.meredith.evidencevozidel.automobily;

import com.codingvalhalla.meredith.evidencevozidel.utills.LoznaPlocha;
import java.util.Objects;

public class Dodavka extends Vozidlo {

    private static final long serialVersionUID = 9135874716087972085L;

    private LoznaPlocha loznaPlocha;
    private boolean tazneZarizeni;

    /**
     * Konstruktor dodavky
     *
     * @param znacka jmeno vyrobce
     * @param SPZ napr.: 4A2 3000
     * @param rokVyroby rok vyroby vozu
     * @param vaha hodnota udavana v tunach (t)
     * @param palivo vyber z prevolenych
     * @param loznaPlocha rozmery v lozne plochy v milimetrech (mm)
     * @param tazneZarizeni boolean {@code true} pokud ma tazne zarizeni (koule
     * pro privez)
     */
    public Dodavka(String znacka, String SPZ, int rokVyroby, double vaha, Palivo palivo, LoznaPlocha loznaPlocha, boolean tazneZarizeni) {
        super(VozidloEnum.DODAVKA, znacka, SPZ, rokVyroby, vaha, palivo);
        this.loznaPlocha = loznaPlocha;
        this.tazneZarizeni = tazneZarizeni;
    }

    @Override
    public VozidloEnum getDruh() {
        return super.getDruh();
    }

    public void setTazneZarizeni(boolean tazneZarizeni) {
        this.tazneZarizeni = tazneZarizeni;
    }

    public void setLoznaPlocha(LoznaPlocha loznaPlocha) {
        this.loznaPlocha = loznaPlocha;
    }

    public LoznaPlocha getLoznaPlocha() {
        return loznaPlocha;
    }

    public boolean isTazneZarizeni() {
        return tazneZarizeni;
    }

    public String getTazneZarizni() {
        if (isTazneZarizeni()) {
            return "Ano";
        } else {
            return "Ne";
        }
    }

    @Override
    public String toString() {
        return "Dodavka {" + super.toString() + ", lozna plocha= " + getLoznaPlocha() + ", tazne zarizeni= " + getTazneZarizni() + " }";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        if (!super.equals(obj)) {
            return false;
        }
        Dodavka other = (Dodavka) obj;
        if (!Objects.equals(this.tazneZarizeni, other.tazneZarizeni)) {
            return false;
        }
        if (!Objects.equals(this.loznaPlocha, other.loznaPlocha)) {
            return false;
        }
        return true;

    }

    @Override
    public int hashCode() {
        return Objects.hash(loznaPlocha, tazneZarizeni);
    }

}
