package inv.manage;
import java.util.ArrayList;

public class Data
{
   ArrayList<Movie> datan = new ArrayList<Movie>(); 
   int aind = -1;
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
   public int find(int sk)
   {
      Movie temp = new Movie();
      for(int i = 0; i <= aind; i++)
      {
         temp = datan.get(i);
         if(temp.sku == sk)
            return i;
      }
      return -1;
   }
   public int remove(int index)
   {
      datan.remove(index);
      aind--;
      return 0;  
   }
   public void printone(int index)
   {
      Movie temp = new Movie();
      temp = datan.get(index);
      System.out.println("      SKU: " + temp.sku + "\n" + 
                         "    Title: " + temp.title + "\n" +
                         "    Price: $" + temp.price + "\n" + 
                         " Quantity: " + temp.quantity + "\n");
   }
   public void printall()
   {
      if(aind < 0)
         System.out.println("No Inventory \n");
      else
         for(int i = 0; i <= aind; i++)
         {
            System.out.println("Movie: \n");
            printone(i); 
         }
   }
}   
      
