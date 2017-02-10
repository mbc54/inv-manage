package inv.manage;
import java.util.*;

import java.io.*;

public class Toy extends List
{
    Double weight;
    Toy() {}; 
    Toy(int sku, int quantity, String title, Double price, Double weight)
    {
        super(sku, quantity, title, price);
        this.weight = weight;
    }

    public void print()
    {
        super.printsame();
        System.out.printf("\n   WEIGHT:  %.2f", weight);
    }
}
