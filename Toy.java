package inv.manage;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Toy extends List
{
    double weight_oz;
    double weight_lb = weight_oz / 16.0;
    double commission = .15;
    double shipcred = (4.49 + (.5 * weight_lb)); 

    Toy() {}; 
    Toy(int sku, int quantity, String title, Double price, double weight_oz)
    {
        super(sku, quantity, title, price);
        this.weight_oz = weight_oz;
    }

    public void print()
    {
        super.printsame();
        System.out.printf("\n   WEIGHT:  %.2f", weight_oz);
    }
    public void printAll()
    {
        String type = "Toy     ";
        super.printsameAll(type);
    }
    public void processSale(double q, double scost)
    {
        double pri = super.compPrice(q);
        double com = super.compCom(commission, q);
        double shipcr = super.compShip(shipcred, q);
        double profit = super.compProf(pri, shipcr, com, scost);
        if(super.decQuan(q) == 0)
        {
            System.out.printf("Total Price: %.2f\n", pri);
            System.out.printf("Total Commission: %.2f\n", com);
            System.out.printf("Total Shipping Credit: %.2f\n", shipcr);
            System.out.printf("Total Profit: %.2f\n", profit);
        }
    }
}
