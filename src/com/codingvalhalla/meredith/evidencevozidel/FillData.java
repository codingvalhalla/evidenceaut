package com.codingvalhalla.meredith.evidencevozidel;

import com.codingvalhalla.meredith.evidencevozidel.automobily.Barva;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Dodavka;
import com.codingvalhalla.meredith.evidencevozidel.automobily.NakladniAutomobil;
import com.codingvalhalla.meredith.evidencevozidel.automobily.OsobniAutomobil;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Palivo;
import com.codingvalhalla.meredith.evidencevozidel.automobily.Vozidlo;
import com.codingvalhalla.meredith.evidencevozidel.utills.LoznaPlocha;

/**
 *
 * @author Meredith
 */
public class FillData {

    private FillData() {
    }
    private static OsobniAutomobil auto = new OsobniAutomobil("Skoda", "I AM SORRY", 2000, 1.3, Palivo.GASOLINE, Barva.GRAY, 75);
    private static Dodavka dodavka = new Dodavka("Mercedes", "7M4 3642", 2003, 2.5, Palivo.DIESEL, new LoznaPlocha(4070, 1932, 1870), false);
    private static NakladniAutomobil nakladak = new NakladniAutomobil("Avia", "TUE 32-41", 1999, 15, Palivo.DIESEL, 25, 3);

    private static OsobniAutomobil auto1 = new OsobniAutomobil("BMV", "4AY 3642", 2003, 1.3, Palivo.ALCOHOL, Barva.WHITE, 75);
    private static Dodavka dodavka1 = new Dodavka("Ford", "4H7 6351", 2001, 2.5, Palivo.DIESEL, new LoznaPlocha(4070, 1932, 1870), true);
    private static NakladniAutomobil nakladak1 = new NakladniAutomobil("Tatra", "PHK 98-29", 1998, 15, Palivo.DIESEL, 25, 3);

    private static OsobniAutomobil auto2 = new OsobniAutomobil("Citroën", "8E3 1563", 2012, 1.3, Palivo.ELECTRO, Barva.BLACK, 75);
    private static Dodavka dodavka2 = new Dodavka("Volkswagen", "7B9 0809", 2005, 2.5, Palivo.DIESEL, new LoznaPlocha(4070, 1932, 1870), false);
    private static NakladniAutomobil nakladak2 = new NakladniAutomobil("Liaz", "HKV 24-49", 1995, 15, Palivo.DIESEL, 25, 3);

    private static OsobniAutomobil auto3 = new OsobniAutomobil("Daewoo", "3SA 7523", 2007, 1.3, Palivo.LPG, Barva.GREEN, 75);
    private static Dodavka dodavka3 = new Dodavka("Renault", "6C2 1100", 2000, 2.5, Palivo.DIESEL, new LoznaPlocha(4070, 1932, 1870), true);
    private static NakladniAutomobil nakladak3 = new NakladniAutomobil("Praga", "CDS 63-52", 1983, 15, Palivo.DIESEL, 25, 3);

    public static Vozidlo[] listData = {auto, dodavka, nakladak, auto1, dodavka1, nakladak1, auto2, dodavka2, nakladak2, auto3, dodavka3, nakladak3};
}
