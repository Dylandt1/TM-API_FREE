/**
 * The getter return the percentage bonus /100, for use juste multiply your value by the result getter.
 */

package fr.tmmods.tmapi.tools;

public enum PartyBonus
{
    //Amethystes bonus :
    AMTH1(10),
    AMTH2(20),
    AMTH3(30),
    AMTH4(40),
    AMTH5(50),

    //Legendary Dusts bonus :
    LD1(5),
    LD2(10),
    LD3(15),
    LD4(20),
    LD5(25),

    //XP bonus :
    XP1(5),
    XP2(10),
    XP3(20),
    XP4(40),
    XP5(80),

    //Combined bonus :
    COMB1(3.33f),
    COMB2(6.66f),
    COMB3(10.83f),
    COMB4(16.66f),
    COMB5(25.83f),

    //Common bonus :
    COM1(1.66f),
    COM2(3.33f),
    COM3(5.41f),
    COM4(8.33f),
    COM5(12.91f);

    private float bonus; // bonus float, is percentage of additional game rewards

    PartyBonus(float bonus)
    {
        this.bonus = bonus;
    }

    public float getBonus() {return bonus/100;}
}
