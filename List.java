package inv.manage;
import java.util.*;
import java.io.*;

public class List implements Serializable
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


}
