package inv.manage;
import java.util.*;
import java.io.*;

/**
* class of the LIST superclass for book that holds book information
* @author Michael Childress
* @author Kyle Duncan
*/
public class Book extends List
{
    int isbn;
    String author;
    Double commission = .15;
    Double shipcred = 3.99;

    Book() {}; 
    Book(int sku, int quantity, int isbn, String author, String title, Double price)
    {
        super(sku, quantity, title, price);
        this.isbn = isbn;
        this.author = author;
    }

    public void print()
    {
        super.printsame();
        System.out.printf("\n     ISBN:%3d\n   AUTHOR:%3S", isbn, author);
    }
     public void printAll()
    {
        String type = "Book    ";
        super.printsameAll(type);
    }

    /**
    * processes a sale for the book type, taking into account the commission
    * specific to books
    */
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
