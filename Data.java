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
    ArrayList<List> datan = new ArrayList<List>();
    int tsku, tquan, tisbn, tupc;
    Double tprice, tweight;
    String ttit, tauth;
    public int popArray()
    {
        try 
        {
            FileInputStream fis = new FileInputStream("ListFile");
            ObjectInputStream ois = new ObjectInputStream(fis);
            datan = (ArrayList<List>)ois.readObject(); 
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
        System.out.println(
                "\n1. Add a product to the inventory \n" +
                "2. Remove a product from the inventory (by sku). \n" +
                "3. Display the information for a product (given by the sku). \n" +
                "4. Display the inventory in a table (sorted by SKU). \n" +
                "5. Display the inventory in a table (sorted by title. \n" +
                "6. Process a Sale. \n" +
                "7. Quit");
        return cin.nextInt();
    }
    public int find(int sk)
    {
        List temp = new List();
        for(int i = 0; i < datan.size(); i++)
        {
            temp = datan.get(i);
            if(temp.sku == sk)
                return i;
        }
        return 0;
    }

    public int add(Scanner cin)
    {  
        tsku = 0;
        System.out.println("\nPlease enter the product type (M, B, T): ");
        char proty = cin.next().charAt(0);
        System.out.println("Please enter a unique SKU (integer): " ); 
        tsku = cin.nextInt();
        System.out.println("Please enter the movie title: "); 
        cin.nextLine(); 
        ttit = cin.nextLine();  
        System.out.println("Please enter the movie price (0.00 format): ");  
        tprice = cin.nextDouble();         
        System.out.println("Please enter the quantity of the movie: ");  
        tquan = cin.nextInt();

        if(proty == 'm' || proty == 'M')
        {
            System.out.println("Please enter the UPC of the movie: ");
            tupc = cin.nextInt();
            List temp = new Movie(tsku, tquan, ttit, tprice, tupc);
            addListEl(tsku, temp);
        }
        else if(proty == 'b' || proty == 'B')
        {
            System.out.println("Please enter the ISBN of the book: "); 
            tisbn = cin.nextInt();
            System.out.println("Please enter the author of the book: ");
            cin.nextLine();
            tauth = cin.nextLine();
            List temp = new Book(tsku, tquan, tisbn, tauth, ttit, tprice);
            addListEl(tsku, temp);
        }
        else if(proty == 't' || proty == 'T')
        {
            System.out.println("Please enter the weight of the toy in ounces: ");
            tweight = cin.nextDouble();
            List temp = new Toy(tsku, tquan, ttit, tprice, tweight);
            addListEl(tsku, temp);
        }
        else 
        {
            System.out.println("Invalid Type");
        }
        return 0;
    }

    public void addListEl(int sk, List el)
    {
        if(find(sk) <= 0)
        {
            datan.add(el);
            System.out.println("\nProduct added.\n");
        }
        else
        {
            System.out.println("\nDuplicate product.\n");
        }
    }

    public int remove(Scanner cin)
    {
        System.out.println("Enter a SKU for the product to be removed: ");
        int index = find(cin.nextInt());
        datan.remove(index);
        System.out.println("\nProduct Removed.\n");
        return 0;  
    }
    public int printone(List temp)
    {
        temp.print();
        return 0;
    }
    public int printall()
    {
        //update data type, should work may need a little love
        List temp = new List();
        int skulen = 5,
            titlen = 10,
            prilen = 6,
            qualen = 2;

        for(int i = 0; i < datan.size(); i++)
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
        for(int i = 0; i < datan.size(); i++)
        {   
            temp = datan.get(i);
            System.out.printf("  %-" + skulen + "d %-" + titlen + 
                    "S $%-" + prilen + ".2f %-" + qualen + "d %n", temp.sku, temp.title, 
                    temp.price, temp.quantity); 
        }  

        if(datan.size() <= 0)
            System.out.println("\nNo Inventory to Print.\n");
        return 0;
    }
    //public void Sale(List temp)
    //{
    //    temp.processSale();
    //    return 0;
    //}

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
        {
            System.out.println("Enter a SKU for the product to print: ");
            int index = find(cin.nextInt());

            return printone(datan.get(index));
        }
        if(choice == 4)
            return printall();
        if(choice == 5)
        {
            try 
            {
                FileOutputStream fos = new FileOutputStream("ListFile");
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

   
      
