package inv.manage;
import java.util.*;
import java.io.*;

/**
* maintains all the information included for the movie class, includes
* processSale specific to movie info
*/
public class Movie extends List 
{
    int upc;
    double commission = .12;
    double shipcred = 2.98;
    Movie() {}; 
    Movie(int sku, int quantity, String title, Double price, int upc)
    {
        super(sku, quantity, title, price);
        this.upc = upc;
    }

    public void print()
    {
        super.printsame();
        System.out.printf("\n     UPC:%3d", upc);
    }
    public void printAll()
    {
        String type = "Movie   ";
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
