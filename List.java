package inv.manage;
import java.io.*;

public class List implements Serializable
{
   int sku, quantity;
   String title;
   Double price;
   Movie() {}; 
   Movie(int sku, int quantity, String title, Double Price)
   {
      this.sku = sku;
      this.quantity = quantity;
      this.title = title;
      this.price = price;
   }
   public String display() 
   {
      return (sku + " " + title + " " + price  + " " + quantity);
   }
   
}
