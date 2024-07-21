package fr.tmmods.tmapi.tools;

import com.google.common.annotations.Beta;

@Beta
public class Economy
{
    public static int estimateI(int balance, int cost)
    {
        return balance - cost;
    }

    public static float estimateF(float balance, float cost)
    {
        return balance - cost;
    }

    public static double estimateD(double balance, double cost)
    {
        return balance - cost;
    }
}
