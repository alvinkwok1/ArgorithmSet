package sort.bubble;


import org.junit.Test;
import sort.ISort;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BubbleSortTest {

  private int[] tagetList={-1,1,2,6};

  private List<Integer> prepare(){
    List<Integer> data = new ArrayList<>();
    data.add(6);
    data.add(1);
    data.add(2);
    data.add(-1);
    return data;
  }

  private List<Integer> data;

  @Test
  public void sort() {
    ISort<Integer> sortor = new BubbleSort<>();
    List<Integer> result = (List<Integer>) sortor.sort(prepare());
    for (int i=0;i<tagetList.length;i++){
      System.out.println(result.get(i));
    }
  }

  @Test
  public void testSort() {

  }
}
