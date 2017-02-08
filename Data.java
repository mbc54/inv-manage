/*
This class will manage a type "list" that is an arrayList of 3 different data
objects. 


*/
package inv.manage;
import java.util.*;
import java.io.*;

public class Data
{
   //switch type to List
   ArrayList<Movie> datan = new ArrayList<Movie>();
   int aind = -1; 
   public int popArray()
   {
      try 
      {
         FileInputStream fis = new FileInputStream("MovieFile");
         ObjectInputStream ois = new ObjectInputStream(fis);
         //switch type to list
         datan = (ArrayList<Movie>)ois.readObject(); 
         fis.close();
         return 0;
      } 
      catch (FileNotFoundException e) 
      {
         System.out.println("No Exsisting Inventory.");
         return 1;
      } 
      catch (IOException e) 
      {
         System.out.println("Problem with file input.");
         return 1;
      }   
      catch (ClassNotFoundException e) 
      {
         System.out.println("Class not found on input from file.");
         return 1;
      }
   }
   public int prntmenu(Scanner cin)
   {
      //update menu based on sample output
      System.out.println(
         "\n1. Add a movie to the inventory \n" +
         "2. Remove a movie from the inventory (by sku). \n" +
         "3. Display the information for a movie (given by the sku). \n" +
         "4. Display the inventory in a table (in any order). \n" +
         "5. Quit");
      int number_choice = cin.nextInt();
      if(aind == -1)
      {
         if(popArray() == 0)
            aind = datan.size() - 1;
      }
      return number_choice;
      
   }
   public int find(int sk)
   {
      //update data type to list
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
      //update data type and input options
      //make add product menu handled by object (each is unique)
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
         //update output
         System.out.println("\nMovie Added.\n");
      }
      else
      {
         //update input
	  System.out.println("\nDuplicate Movie.\n");
      }
      return 0;
   }
  
   public int remove(Scanner cin)
   {
      //should work fine, but will need to test
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
      //update data type, actual print should be handled by object
      Movie temp = new Movie();
      System.out.println("Enter a SKU for the movie to print: ");
      int index = find(cin.nextInt());
      temp = datan.get(index);
      System.out.printf("      SKU:%3d\n    TITLE:%3S\n    PRICE: $%.2f\n QUANTITY:%3d"
                         ,temp.sku, temp.title,temp.price, temp.quantity);
      return 0;
   }
   public int printall()
   {
      //update data type, should work may need a little love
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

   //-We need to implement processSale() method. 
   //-user enters sku, quantity, and cost 
   //-method updates quantity, or prints an error message if there is not enough
   // inventory, (if error or maybe not at all ever), don't remove product from inventory
   //-need to compute and print total price, shipping, and commission based on
   // the table in assignment. (looks annoying)
   //-format output

   public int action(Scanner cin) 
   {
       //add actions for new menu choices
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
       {
          //update file name 
          try 
          {
             FileOutputStream fos = new FileOutputStream("MovieFile");
             ObjectOutputStream oos = new ObjectOutputStream(fos);
             oos.writeObject(datan);  
             fos.close();
             return 1;
          }  
          catch (IOException e) 
          {
            System.out.println("Problem with file output");
            return 0;
          }
       } 
       else
	  System.out.println("\nNot a Valid Menu Choice.\n");
       return 0;
   }
}   
      
