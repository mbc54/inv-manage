package inv.manage;
import java.util.*;
import java.io.*;

public class ListByTitle implements Comparator<List> 
{
       public int compare(List lhs, List rhs) 
       {
           return lhs.getTitle().compareTo(rhs.getTitle());
       }
}
