package inv.manage;
import java.util.*;
import java.io.*;

public class Book extends List
{
    int isbn;
    String author;

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
}
