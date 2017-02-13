package inv.manage;
import java.util.*;
import java.io.*;

public class ListBySku implements Comparator<List> 
{
       public int compare(List lhs, List rhs) 
       {
           float lhssku = lhs.getSku();
           float rhssku = rhs.getSku();
           if (lhssku < rhssku) return -1;
           if (lhssku == rhssku) return 0;
           return 1;
       }
}
