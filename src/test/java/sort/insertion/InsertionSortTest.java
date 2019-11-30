package sort.insertion;

import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.InitData;
import sort.bubble.BubbleSort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class InsertionSortTest {
  @Test
  public void testSort() {
    ISort<Integer> sortor = new InsertionSort<>();
    Integer[] result =  sortor.sort(InitData.getTestData(), new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    for (int i=0;i<InitData.getExpectData().length;i++){
      Assert.assertEquals(InitData.getExpectData()[i].intValue(),result[i].intValue());
    }
  }
}