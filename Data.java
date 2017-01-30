package inv.manage;
import java.util.*;
import java.io.*;

public class Data
{
   ArrayList<Movie> datan = new ArrayList<Movie>(); 
   int aind = -1;
   public int prntmenu(Scanner cin)
   {
      System.out.println(
         "\n1. Add a movie to the inventory \n" +
         "2. Remove a movie from the inventory (by sku). \n" +
         "3. Display the information for a movie (given by the sku). \n" +
         "4. Display the inventory in a table (in any order). \n" +
         "5. Quit");
      int number_choice = cin.nextInt();
      return number_choice;
   }
   public int find(int sk)
   {
      Movie temp = new Movie();
      if(aind <= 0)
         return -1;
      for(int i = 0; i <= aind; i++)
      {
         temp = datan.get(i);
         if(temp.sku == sk)
            return i;
      }
      return -1;
   }
   public int add(Scanner cin)
   {  
      Movie temp = new Movie();
      System.out.println("\nPlease enter a unique SKU (integer): " ); 
      temp.sku = cin.nextInt();
      System.out.println("Please enter the movie title: "); 
      cin.nextLine(); 
      temp.title = cin.nextLine();  
      System.out.println("Please enter the movie price (0.00 format): ");  
      temp.price = cin.nextDouble();         
      System.out.println("Please enter the quantity of the movie: ");  
      temp.quantity = cin.nextInt();
      if(find(temp.sku) < 0)
      {
	 aind++;
         datan.add(aind, temp);
         System.out.println("\nMovie Added.\n");
      }
      else
      {
	  System.out.println("\nDuplicate Movie.\n");
      }
      return 0;
   }
  
   public int remove(Scanner cin)
   {
      int id;
      System.out.println("Enter a SKU for the movie to be removed: ");
      int index = find(cin.nextInt());
      datan.remove(index);
      aind--;
      System.out.println("\nMovie Removed.\n");
      return 0;  
   }
   public int printone(Scanner cin)
   {
      Movie temp = new Movie();
      System.out.println("Enter a SKU for the movie to print: ");
      int index = find(cin.nextInt());
      temp = datan.get(index);
      System.out.println("      SKU: " + temp.sku + "\n" + 
                         "    Title: " + temp.title + "\n" +
                         "    Price: $" + temp.price + "\n" + 
                         " Quantity: " + temp.quantity + "\n");
      return 0;
   }
   public int printall()
   {
      Movie temp = new Movie();
      int skulen = 5,
          titlen = 10,
          prilen = 6,
          qualen = 2;
     
      for(int i = 0; i <= aind; i++)
      {
         temp = datan.get(i);
         if((Integer.toString(temp.sku)).length() > skulen)
            skulen = (Integer.toString(temp.sku)).length() + 1;
         if(temp.title.length() > titlen) 
            titlen = temp.title.length() + 1;
         if((Double.toString(temp.price)).length() > prilen)
            prilen = (Double.toString(temp.price)).length() + 1;
         if((Integer.toString(temp.quantity)).length() > qualen)
            qualen = (Integer.toString(temp.quantity)).length() + 1;
      }
      for(int i = 0; i <= aind; i++)
      {   
         temp = datan.get(i);
         System.out.printf("  %-" + skulen + "d %-" + titlen + 
                           "S $%-" + prilen + ".2f %-" + qualen + "d %n", temp.sku, temp.title, 
                           temp.price, temp.quantity); 
      }
      if(aind < 0)
         System.out.println("\nNo Inventory to Print.\n");
      return 0;
   }  
   public int action(Scanner cin) 
   {
       int choice = prntmenu(cin);
       if(choice == 1)
          return add(cin);
       if(choice == 2)
          return remove(cin);
       if(choice == 3)
          return printone(cin);
       if(choice == 4)
          return printall();
       if(choice == 5)
          return 1;
       else
	  System.out.println("\nNot a Valid Menu Choice.\n");
       return 0;
   }
}   
      
