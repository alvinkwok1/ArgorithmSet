package sort.bubble;


import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.InitData;
import sort.heap.HeapSort;

import java.util.Comparator;

public class BubbleSortTest {

  @Test
  public void testSort() {
    ISort<Integer> sortor = new BubbleSort<>();
    Integer[] result =  sortor.sort(InitData.getTestData());

    for (int i=0;i<InitData.getExpectData().length;i++){
      Assert.assertEquals(InitData.getExpectData()[i].intValue(),result[i].intValue());
    }
    System.out.println("冒泡排序测试结束\n结果：");
    for (int i=0;i<InitData.getExpectData().length;i++){
      System.out.print(result[i]);
    }
    System.out.println();
  }

  @After
  public void after(){

  }
}
