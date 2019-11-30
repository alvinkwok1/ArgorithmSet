package sort.selection;

import org.junit.Assert;
import org.junit.Test;
import sort.ISort;
import sort.InitData;
import sort.insertion.InsertionSort;

import java.util.Comparator;

import static org.junit.Assert.*;

public class SelectionSortTest {

  @Test
  public void testSort() {
    ISort<Integer> sortor = new SelectionSort<>();
    Integer[] result =  sortor.sort(InitData.getTestData(), new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    });
    for (int i=0;i<InitData.getExpectData().length;i++){
      Assert.assertEquals(InitData.getExpectData()[i].intValue(),result[i].intValue());
    }

    System.out.println("选择排序测试结束\n结果：");
    for (int i=0;i<result.length;i++){
      System.out.print(result[i]);
    }
    System.out.println();
  }}