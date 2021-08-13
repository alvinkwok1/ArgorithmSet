package sort.heap;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.InitData;

public class HeapSortTest {

  @Test
  public void testSort() {
    ISort<Integer> sortor = new HeapSort<>();
    Integer[] result =  sortor.sort(InitData.getTestData());

    for (int i=0;i<InitData.getExpectData().length;i++){
      Assert.assertEquals(InitData.getExpectData()[i].intValue(),result[i].intValue());
    }
    System.out.println("冒泡排序测试结束\n结果：");
    for (int i=0;i<result.length;i++){
      System.out.print(result[i]);
    }
    System.out.println();
  }

  @After
  public void after(){

  }
}
