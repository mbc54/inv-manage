package inv.manage;
import java.util.*;
import java.io.*;

/**
* contains main, handles some exceptions
* @author Michael Childress
* @author Kyle Duncan
*/
public class InventoryDriver
{
   public static void main(String[] args)
   {
     
      Data drive = new Data();
      int stop = 0;
      drive.popArray();
      while(stop == 0)
      {
         Scanner input = new Scanner(System.in);
         try
         {
            stop = drive.action(input);
         }
         catch(InputMismatchException x)
         {
            System.err.println("\nEnter Valid Input.\n");
         }
         catch(ArrayIndexOutOfBoundsException y)
         {
            System.err.println("\nProduct Not Found.\n");
         }
         catch(IndexOutOfBoundsException z)
         {
            System.err.println("\nNo Inventory to Print.\n");
         }
      }           
   }
}
