package inv.manage;
import java.util.Scanner;
public class Menu
{
    //if List is empty, create new Data, else set equal to old data
    Data inv = new Data();
    public int prntmenu(Scanner cin)
    {
        System.out.println(
           "1. Add a movie to the inventory \n" +
           "2. Remove a movie from the inventory (by sku). \n" +
           "3. Display the information for a movie (given by the sku). \n" +
           "4. Display the inventory in a table (in any order). \n" + 
           "5. Quit");
        int number_choice = cin.nextInt();
        return number_choice;
    }
    public int one(Scanner cin)
    {
        Movie temp = new Movie();
        String res;
        System.out.println("Please enter a unique SKU (integer): " );
        temp.sku = cin.nextInt();
        System.out.println("Please enter the movie title: ");
        cin.nextLine();
        temp.title = cin.nextLine(); 
        System.out.println("Please enter the movie price: "); 
        temp.price = cin.nextLine();
        System.out.println("Please enter the quantity of the movie: "); 
        temp.quantity = cin.nextInt();
        inv.add(temp);
        return 0;
    }
    public int remove(int index)
    {
       return 0;
    }
    public void action(int choice, Scanner cin)
    {
       if(choice == 1)
          one(cin);
    }
}
   
   /*
   public //response
   {
   }
   public //remove
   {
   }
   public //show movie
   {
   }
   public //show inventory in table
   {
   }
   public //quit (save file)
   {
   } 
   */
