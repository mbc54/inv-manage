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
        System.out.println("Please enter the movie price (0.00 format): "); 
        temp.price = cin.nextLine();
        //test for incorrect format?
        System.out.println("Please enter the quantity of the movie: "); 
        temp.quantity = cin.nextInt();
        inv.add(temp);
        return 0;
    }
    public int two(Scanner cin)
    {
       int id;
       System.out.println("Enter a SKU for the movie to be removed: ");
       int index = inv.find(cin.nextInt());
       if(index < 0)
       {
          System.out.println("Movie not found");
          return 0;
       }
       else
       {
          inv.remove(index);
       }
       return 0;
    }
    public int three(Scanner cin)
    {
       System.out.println("Enter a SKU for the movie to print: ");
       inv.printone(inv.find(cin.nextInt()));
       return 0;
    }
    public int four()
    {
       inv.printall();
       return 0;
    }

    public int action(int choice, Scanner cin)
    {
       int returnval = 0;
       if(choice == 1)
          returnval = one(cin);
       if(choice == 2)
       {
          returnval = two(cin);
       }
       if(choice == 3)
          returnval = three(cin);
       if(choice == 4)
          returnval = four();
       if(choice == 5)
          returnval = 1;
       return returnval;
    }
}
