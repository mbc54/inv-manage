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
   public int find(int id)
   {
      Movie temp = new Movie();
      for(int i = 0; i <= aind; i++)
      {
         temp = datan.get(i);
         if(temp.sku == id)
            return i;
      }
      return -1;
   }
   public void remove(int index)
   {
      datan.remove(find(index));  
   }
   public void printone(int index)
   {
      Movie temp = new Movie();
      temp = datan.get(find(index));
      System.out.println(temp.sku + "\n" + temp.title + "\n" + temp.price 
                         + "\n" + temp.quantity);
   }
   public void printall()
   {
      for(int i = 0; i < aind; i++)
         printone(i);
   }
}   
      
