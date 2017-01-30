package inv.manage;
import java.util.Scanner;

public class InventoryDriver
{
   public static void main(String[] args)
   {
      
      //open file
      //create instance of inventory 
      //close file
      Menu drive = new Menu();
      int stop = 0;
      while(stop == 0)
      {
         Scanner input = new Scanner(System.in);
         stop = drive.action(drive.prntmenu(input), input);
      }           
   }
}
