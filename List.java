package inv.manage;
import java.util.*;
import java.io.*;

/**
* serializable class that processes and prints lists to the screen, either
* lists containing only a user-specified item or all available items
* @author Michael Childress
* @author Kyle Duncan
*/
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

    /**
    * prints all products to a table, where each cell is the length of the 
    * longest item in that row
    */
    public void printsameAll(String type)
    {
        int typeln = (10 - type.length()),
            skulen = 5,
            titlen = 10,
            prilen = 6,
            qualen = 2;
        
       if((Integer.toString(sku)).length() > skulen)
            skulen = (Integer.toString(sku)).length() + 1;
        if(title.length() > titlen) 
            titlen = title.length() + 1;
        if((Double.toString(price)).length() > prilen)
            prilen = (Double.toString(price)).length() + 1;
        if((Integer.toString(quantity)).length() > qualen)
            qualen = (Integer.toString(quantity)).length() + 1;

        System.out.printf(type + "   %-" + skulen + "d $%-" + prilen + 
                ".2f %-" + titlen + "S\n", sku, price, title);
    }
    public void printAll()
    {
        String type = "List";
        printsameAll(type);
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

    public int decQuan(double quan)
    {
        Double d = new Double(quan);
        int locquan = d.intValue();
        if(locquan > quantity) 
        {
            System.out.println("Not enough inventory.");
            return 1;
        }
        else 
        {
            quantity = quantity - locquan;
            return 0;
        }
    }

    /**
    * prints sale information, including sale price, commission, shipping,
    * and profit.
    */
    public void processSale(double q, double scost)
    {
        double pri = compPrice(q);
        double com = compCom(0, q);
        double shipcr = compShip(0, q);
        double profit = compProf(pri, shipcr, com, scost);
        if(decQuan(q) == 0)
        {
            System.out.printf("Total Price: %.2f\n", pri);
            System.out.printf("Total Commission: %.2f\n", com);
            System.out.printf("Total Shipping Credit: %.2f\n", shipcr);
            System.out.printf("Total Profit: %.2f\n", profit);
        }
    }

}
