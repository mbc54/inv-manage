
package inv.manage;
import java.util.*;
import java.io.*;

public class Data
{
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
                "\nOnline Store Inventory Menu \n\n" +
                "1. Add a Product to the Inventory \n" +
                "2. Remove a Product From the Inventory (by SKU). \n" +
                "3. Display the Information for a Product (Given by the SKU). \n" +
                "4. Display the Inventory in a Table (Sorted by SKU). \n" +
                "5. Display the Inventory in a table (Sorted by Title). \n" +
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
        return -1;
    }
    public boolean checkDup(int sk)
    {
        boolean found = false;
        if(find(sk) >= 0)
            found = true;
        return found;
    }
    public int add(Scanner cin)
    {  
        tsku = 0;
        System.out.println("\nPlease enter the product type.");
        System.out.println("\nEnter 'M' for movie, 'B' for Book, or 'T' for Toy.");
        char proty = cin.next().charAt(0);
        while(proty != 'm' && proty != 'M' && proty != 'b' && proty != 'B' &&
              proty != 't' && proty != 'T') 
        {
            System.out.println("\nInvalid Type. Please choose M, B, or T : ");
            proty = cin.next().charAt(0);
        }
        System.out.println("Please enter a unique SKU (integer): " ); 
        tsku = cin.nextInt();
        System.out.println("Please enter the product title: "); 
        cin.nextLine(); 
        ttit = cin.nextLine();  
        System.out.println("Please enter the product price (0.00 format): ");  
        tprice = cin.nextDouble();         
        System.out.println("Please enter the quantity of the product: ");  
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
        return 0;
    }

    public void addListEl(int sk, List el)
    {
        if(!(checkDup(sk)))
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
    public void printall(List temp)
    { 
        temp.printAll();
    }

    public void Sale(List temp, double quant, double ship_c)
    {
        temp.processSale(quant, ship_c);
    }

    public int action(Scanner cin) 
    {
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
        {
            Comparator<List> comp = new ListBySku();
            Collections.sort(datan, comp);
            if(datan.isEmpty())
                System.out.println("\nNo inventory to print.\n");
            else
            {
                for(int i = 0; i < datan.size(); i++)
                {
                    printall(datan.get(i));
                }
            }
            return 0;
        }
        if(choice == 5) 
        {
            Comparator<List> comp = new ListByTitle();
            Collections.sort(datan, comp);
            if(datan.isEmpty())
                System.out.println("\nNo inventory to print.\n");
            else
            {
                for(int i = 0; i < datan.size(); i++)
                {
                    printall(datan.get(i));
                }
            }
            return 0;
        }    
        if(choice == 6) 
        {
           System.out.println("Enter SKU of sold products: ");
           int index = find(cin.nextInt());
           System.out.println("Enter the quantity to be sold: ");
           double quan = cin.nextDouble();
           System.out.println("Enter the cost of shipping: ");
           double ship = cin.nextDouble();
           Sale(datan.get(index), quan, ship);
           return 0;
        }
        if(choice == 7)
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

   
      
