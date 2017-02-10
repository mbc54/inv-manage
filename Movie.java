package inv.manage;
import java.util.*;
import java.io.*;

public class Movie extends List 
{
    int upc;
    Movie() {}; 
    Movie(int sku, int quantity, String title, Double price, int upc)
    {
        super(sku, quantity, title, price);
        this.upc = upc;
    }

    public void print()
    {
        super.printsame();
        System.out.printf("     \nUPC:%3d", upc);
    }


}
