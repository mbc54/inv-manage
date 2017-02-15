package inv.manage;
import java.util.*;
import java.io.*;

/**
* displays table sorted alphabetically by title
* @author Michael Childress
* @author Kyle Duncan
*/
public class ListByTitle implements Comparator<List> 
{
       public int compare(List lhs, List rhs) 
       {
           return lhs.getTitle().compareTo(rhs.getTitle());
       }
}
