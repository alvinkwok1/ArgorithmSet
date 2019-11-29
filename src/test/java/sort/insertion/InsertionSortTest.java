package sort.insertion;

import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.bubble.BubbleSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class InsertionSortTest {

  private int[] tagetList={-1,1,2,6};

  private Integer[] prepare(){
    return new Integer[]{6,2,-1,1};
  }

  @Test
  public void testSort() {
    ISort<Integer> sortor = new InsertionSort<>();
    Integer[] result =  sortor.sort(prepare(), new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    for (int i=0;i<tagetList.length;i++){
      Assert.assertEquals(tagetList[i],result[i].intValue());
    }
  }
}