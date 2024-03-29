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
    Integer[] result =  sortor.sort(InitData.getTestData());
    for (int i=0;i<InitData.getExpectData().length;i++){
      Assert.assertEquals(InitData.getExpectData()[i].intValue(),result[i].intValue());
    }

    System.out.println("插入排序测试结束\n结果：");
    for (int i=0;i<result.length;i++){
      System.out.print(result[i]);
    }
    System.out.println();
  }
}