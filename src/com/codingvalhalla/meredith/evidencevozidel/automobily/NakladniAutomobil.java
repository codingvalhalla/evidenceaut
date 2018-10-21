package com.codingvalhalla.meredith.evidencevozidel.automobily;

import java.util.Objects;

public class NakladniAutomobil extends Vozidlo {

    private static final long serialVersionUID = -958198117233569704L;

    private double nosnost;
    private int pocetNaprav;

    /**
     * Konstruktor nakladniho automobilu
     *
     * @param znacka jmeno vyrobce
     * @param SPZ napr.: 4A2 3000
     * @param rokVyroby rok vyroby vozu
     * @param vaha hodnota udavana v tunach (t)
     * @param palivo vyber z prevolenych
     * @param nosnost maximalni hmotnost prepravovaneho nakladu v tunach (t)
     * @param napravy pocet naprav
     */
    public NakladniAutomobil(String znacka, String SPZ, int rokVyroby, double vaha, Palivo palivo, double nosnost, int napravy) {
        super(VozidloEnum.NAKLADNI_AUTOMOBIL, znacka, SPZ, rokVyroby, vaha, palivo);
        this.nosnost = nosnost;
        this.pocetNaprav = napravy;
    }

    @Override
    public VozidloEnum getDruh() {
        return super.getDruh();
    }

    public void setNosnost(double nosnost) {
        this.nosnost = nosnost;
    }

    public void setPocetNaprav(int pocetNaprav) {
        this.pocetNaprav = pocetNaprav;
    }

    public double getNosnost() {
        return nosnost;
    }

    public int getPocetNaprav() {
        return pocetNaprav;
    }

    @Override
    public String toString() {
        return "NakladniAutomobil {" + super.toString() + ", nosnost= " + getNosnost() + " t , pocet naprav= " + getPocetNaprav() + " }";
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
        NakladniAutomobil other = (NakladniAutomobil) obj;
        if (!Objects.equals(this.nosnost, other.nosnost)) {
            return false;
        }
        if (!Objects.equals(this.pocetNaprav, other.pocetNaprav)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nosnost, pocetNaprav);
    }

}
