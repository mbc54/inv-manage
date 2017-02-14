package inv.manage;
import java.util.*;
import java.io.*;

public class List implements Serializable, Comparable<List> 
{
    int sku, quantity;
    String title;
    Double price;

    List() {};

    List(int sku, int quantity, String title, Double price)
    {
        this.sku = sku;
        this.quantity = quantity;
        this.title = title;
        this.price = price;
    }
    public void printsame()
    {
        System.out.printf("      SKU:%3d\n    TITLE:%3S\n    PRICE: $%.2f\n QUANTITY:%3d"
                , sku, title, price, quantity);
    }
    public void print()
    {
        printsame();
    }
    public int getSku()
    {
        return sku;
    }
    public String getTitle()
    {
        return title;
    }
    public int compareTo(List rhs)
    {
        return title.compareTo(rhs.title);
    }
    public double compPrice(double quan)
    {
        return (price * quan);
    }
    public double compCom(double comper, double quan)
    {
        return (comper*(compPrice(quan)));
    }
    public double compShip(double shipcred, double quan)
    {
        return (shipcred * quan);
    }
    public double compProf(double pri, double ship, double com, double shipcost)
    {
        return ((pri + ship - (com + shipcost)));
    }
    public void decQuan(double quan)
    {
        Double d = new Double(quan);
        int locquan = d.intValue();
        if(locquan > quantity) 
        {
            System.out.println("Not enough inventory.");
        }
        else 
        {
            quantity = quantity - locquan;
        }
    }
    public void processSale(double q, double scost)
    {
        double pri = compPrice(q);
        double com = compCom(0, q);
        double shipcr = compShip(0, q);
        double profit = compProf(pri, shipcr, com, scost);
        decQuan(q);
        System.out.printf("Total Price: %.2f\n", pri);
        System.out.printf("Total Commission: %.2f\n", com);
        System.out.printf("Total Shipping Credit: %.2f\n", shipcr);
        System.out.printf("Total Profit: %.2f\n", profit);

    }

}
