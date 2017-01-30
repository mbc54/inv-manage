package inv.manage;
import java.util.ArrayList;

public class Data
{
   ArrayList<Movie> datan = new ArrayList<Movie>(); 
   int aind;
   public void add(Movie var)
   {  
      //if there are no elements, start at 0
      if(datan.size() < 1)
         aind = 0;
      //else increment by 1
      else
         aind++;
      datan.add(aind, var);
   }
   public void remove(int index)
   {
      datan.remove(index);  
   }
   public void printone(int index)
   {
      Movie temp = new Movie();
      temp = datan.get(index);
      System.out.println("SKU: " + temp.sku + "\nTitle: " + temp.title + "\n" 
                         + "Price: $" + temp.price + "\n" + "Quantity: " + 
                         temp.quantity);
   }
   public void printall()
   {
      for(int i = 0; i < aind; i++)
      {
         System.out.println("Movie " + i + ": ");
         printone(i);
      }
   }
}   
      
