package com.example;

/**
 * Egy könyvtárstruktúrában dokumentumokat szerkesztenek.
 *
 * Minden dokumentumszerkesztéskor .bak kiterjesztésű fájlok biztonsági mentés jönnek létre
 * (doc1.doc-hoz doc1.bak), azonban gyakori, hogy a szerkesztő bezárása után ottmaradnak.
 *
 * Sok esetben a dokumentumokat már törlik is, és üres könyvtárra egyébként nem lenne szükség,
 * de a .bak fájlok miatt a könyvtárak megmaradnak.
 *
 * A feladat egy olyan program készítése, ami a paraméterként kapott könyvtárból kiindulva
 * kitisztítja a dokumentum nélkül ottmaradt .bak fájlokat, és az üres könyvtárakat is törli.
 *
 * A struktúra egy nagyon nagy (több milliós) virtuális fájlrendszer, és egy törlés viszonylag lassú művelet,
 * ezért fontos több szálon végezni a törléseket - a fájlrendszer elosztott,
 * ezért jól fogja bírni a párhuzamos terhelést.
 *
 * Megoldásként Java nyelvű forráskódot várunk (tetszőleges Java verzió használható).
 */
public class CleanFolders {

}
