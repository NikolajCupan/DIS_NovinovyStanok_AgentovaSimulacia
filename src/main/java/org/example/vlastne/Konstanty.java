package org.example.vlastne;

public class Konstanty
{
    // Trvanie simulacie
    public static final int HODINA_OTVORENIA = 9;
    public static final int HODINA_ZATVORENIA = 17;

    public static final int ZACIATOK_CAS_SEKUNDY = Konstanty.HODINA_OTVORENIA * 60 * 60;
    public static final int KONIEC_CAS_SEKUNDY = Konstanty.HODINA_ZATVORENIA * 60 * 60;
    public static final int TRVANIE_CAS_SEKUNDY = Konstanty.KONIEC_CAS_SEKUNDY - Konstanty.ZACIATOK_CAS_SEKUNDY;

}
