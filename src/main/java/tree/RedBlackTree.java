package tree;


/**
 * <p>
 * description: 红黑树实现
 * 红黑树的红黑性质：
 * 性质1： 根节点是黑色
 * 性质2：每个叶节点NIL都是黑色
 * 性质3： 如果一个节点是红色，那么其子节点都是黑色。换句话说不可能存在两个连续的红色节点
 * 性质4： 对每一个节点，从该节点出发到其所有候带叶节点的简单路径上，均包含有相同
 * 数量的黑色节点，记作bh(x)，叫做黑高。bh(x)计算时不包含x自身
 * 例如:
 * -          C
 * -        /    \
 * -     |B|      D
 * -     /  \    /  \
 * -    A   NIL NIL  NIL
 * -  /  \
 * - NIL NIL
 * 使用|x|表示红色节点，单独的字母表示惟黑色节点。从C-A-NIL之间的简单路径的黑高是2，C-D-NIL之间的路径也是2
 * <p>
 * 红黑树的高度最多是2lg(n+1),这是根据性质4进行高度归纳得到的，也可以通过2-3-4树进行合并推导证明
 * <p>
 * 红黑树的平衡性质：
 * 红黑树是是伪平衡，平衡的定义是左子树和右子树高度相差不能超过1。红黑树的平衡是左右子树的高度不能超过2。
 * 一个简单的结构是
 * -                C
 * -             /   \
 * -           |B|    E
 * -          /
 * -        A
 * -      /   \
 * -    |F|   |G|
 * 可以看出 做 h(C.left) - h(C.right) = 2，因此红黑树不是完全平衡的
 * <p>
 * 红黑树的优点：
 * 插入log(n) + 最多两次旋转
 * 删除log(n) + 最多三次旋转
 * 红黑树是为了性能考量的一种数据结构，对比AVL，红黑树的旋转次数会少于AVL，但是搜索性能低于AVL。
 * 在插入的情况下，红黑叔和AVL都需要两次旋转保证平衡。
 * 在删除的情况下，红黑树只要进行三次旋转，但是AVL需要进行logN次旋转，AVL的旋转开销大于红黑树。
 * 在搜索的情况下因为AVL是完全平衡，而红黑是非完全平衡，因此AVL的搜索效率是大于红黑树的。
 * 引入红黑树的目的是为了权衡在性能、空间、功能的折中结果。
 * <p>
 * 结合实际情况。如果搜索远远大于插入，那么选择AVL；如果搜索、插入都基本差不多，则选择红黑树
 * </p>
 *
 * @author guopeng
 * @date 5/6/2020
 * @since v1.0.11
 */
public class RedBlackTree<V> {
  // 红色
  private static final boolean RED = false;
  // 黑色
  private static final boolean BLACK = true;

  /**
   * 红黑树数据存储结构
   */
  class Node<V> {
    // k是关键词
    int key;
    // v是卫星数据
    V v;
    // 维护树结构的数据
    Node<V> left, right, p;
    // 节点颜色
    boolean color;

    /**
     * 创建的默认节点都是红色的，目的是为了保证红黑树的黑高性质
     *
     * @param k
     * @param v
     */
    public Node(int k, V v) {
      this.key = k;
      this.v = v;
    }

    public Node(boolean color) {
      this.color = color;
    }
  }

  // 哨兵节点，降低操作复杂度，并进行辅助判断，属于外部节点，颜色是黑色
  final Node<V> NIL = new Node(BLACK);
  // 根节点
  private Node<V> root = NIL;

  /**
   * 先使用TREE-INSERT(T,x) 也就是BST的一般操作方式将节点插入到指定位置
   *
   * @param k
   * @param v
   */
  public void add(int k, V v) {
    // 根据数据创建一个节点,颜色为红色，保证树的黑高不会受到影响
    Node<V> z = new Node<>(k, v);
    Node<V> y = NIL;
    Node<V> x = root;
    // 从根开始找到一个可以插入z的位置
    while (x != NIL) { // 搜索到叶节点
      y = x;
      if (z.key < x.key)
        x = x.left;
      else x = x.right;
    }
    // 插入到y下
    z.p = y;
    if (y == NIL)
      root = z;
    else if (z.key < y.key)
      y.left = z;
    else
      y.right = z;
    // 设置Z的节点信息
    z.left = NIL;
    z.right = NIL;
    z.color = RED;
    // 上面操作都是正常的TREE-INSERT(x)过程，但是插入以后可能会导致树的红黑性质
    // 出现违背，因此需要进行调整
    insertFixUp(z);
  }

  /**
   * 对插入情况下导致红黑性质破坏后的修复操作
   * 其思路是在保证性质4的情况下，修复其他的性质。
   *
   * @param z
   */
  private void insertFixUp(Node<V> z) {
    while (z.p.color == RED) {
      if (z.p == z.p.p.left) {
        Node<V> y = z.p.p.right;
        // case 1:该情况需要将z.p.p的颜色下沉到z.p 和z.p.p.right,
        // 这样能保证不违反性质4的情况下修复性质3
        if (y.color == RED) {
          y.color = BLACK;
          z.p.p.color = RED;
          z.p.color = BLACK;
          z = z.p.p;
        }
        // case 2
        else if (z == z.p.right) {
          z = z.p;
          leftRotate(z);
        } else {
          // case 3
          z.p.color = BLACK;
          z.p.p.color = RED;
          rightRotate(z.p.p);
        }
      } else {
        Node<V> y = z.p.p.left;
        // case 1
        if (y.color == RED) {
          y.color = BLACK;
          z.p.p.color = RED;
          z.p.color = BLACK;
          z = z.p.p;
        }
        // case 2
        else if (z == z.p.left) {
          z = z.p;
          rightRotate(z);
        }
        //case 3
        else {
          z.p.color = BLACK;
          z.p.p.color = RED;
          leftRotate(z.p.p);
        }
      }
    }
    root.color = BLACK;
  }

  /**
   * 根据key值查找到对应的节点
   * @param k
   * @return
   */
  private Node<V> find(int k) {
    Node<V> x = root;
    while (x != NIL) {
      if (k == x.key)
        return x;
      else if (k < x.key)
        x = x.left;
      else x = x.right;
    }
    return NIL;
  }

  /**
   * 寻找到指定节点上最小的节点，如果没有，则返回自身
   * 该操作也用来寻找一个节点的后继节点
   * 基于BST的性质，最小的节点一定在左子树上
   *
   * @param x
   * @return
   */
  private Node<V> minimum(Node<V> x) {
    while (x.left != NIL) {
      x = x.left;
    }
    return x;
  }

  /**
   * 对外暴露的方法
   * @param k
   * @return
   */
  public V get(int k) {
    Node<V> z = find(k);
    if (z == NIL) {
      return null;
    } else {
      return z.v;
    }
  }

  /**
   * 通过key值移除一个节点
   * @param k
   */
  public void remove(int k) {
    // 先获取要移出的节点
    Node<V> z = find(k);
    if (z != NIL) {
      remove(z);
    }
  }

  /**
   * 从树中移除一个指定节点
   * @param z
   */
  private void remove(Node<V> z) {
    Node<V> y = z;
    Node<V> x = NIL;
    boolean yOriginColor = y.color;
    if (z.left == NIL) {
      x = z.right;
      treeTransplant(z, z.right);
    } else if (z.right == NIL) {
      x = z.left;
      treeTransplant(z, z.left);
    } else {
      //找到后继节点
      y = minimum(z.right);
      yOriginColor = y.color;
      x = y.right;
      if (y.p == z) {
        y.p = z.p;
      } else {
        // 将y替换z
        treeTransplant(z, y);
        y.right = z.right;
        z.right.p = y;
      }
      y.left = z.left;
      y.left.p = y;
      y.color = z.color;
    }
    // 修复红黑树性质
    if (yOriginColor == BLACK) {
      deleteFixUp(x);
    }
  }


  private void deleteFixUp(Node<V> x) {
    while (x != root && x.color == BLACK) {
      if (x == x.p.left) {
        Node<V> w = x.p.right;
        // case 1 兄弟节点为红色节点
        if (w.color == RED) {
          w.color = BLACK;
          x.p.color = RED;
          leftRotate(x.p);
          w = x.p.right;
        }
        if (w.left.color == BLACK && w.right.color == BLACK) {
          x = x.p;
          w.color = RED;
        } else if (w.left.color == RED) {
          w.color = RED;
          w.left.color = BLACK;
          rightRotate(w);
          w = x.p.right;
        } else {
          w.color = x.p.color;
          x.p.color = BLACK;
          w.right.color = BLACK;
          leftRotate(x.p);
          x = root; //设定终止条件
        }
      } else {
        Node<V> w = x.p.left;
        if (w.color == RED) {
          x.color = BLACK;
          x.p.color = RED;
          rightRotate(x.p);
          w = x.p.left;
        }
        if (w.left.color == BLACK && w.right.color == BLACK) {
          x = x.p;
          w.color = RED;
        }
        else if (w.right.color == RED) {
          w.color = RED;
          w.right.color = BLACK;
          leftRotate(w);
          w = x.p.left;
        } else {
          w.color = x.p.color;
          x.p.color = BLACK;
          w.left.color = BLACK;
          rightRotate(x.p);
          x = root;// 设置终止条件
        }
      }
    }
    x.color = BLACK;
  }

  private void treeTransplant(Node<V> u, Node<V> v) {
    Node<V> y = u.right;
  }

  /**
   * 左旋转
   *
   * @param x
   */
  private void leftRotate(Node<V> x) {
    Node<V> y = x.right;
    // 交换x的右孩子为y的左孩子
    x.right = y.left;
    if (y.left != NIL) {
      y.left.p = x;
    }
    // 交换x的位置为y
    y.p = x.p;
    if (x.p == NIL)
      root = y;
    else if (x == x.p.left)
      x.p.left = y;
    else x.p.right = y;
    // 交换y的左节点为x
    x.p = y;
    y.left = x;
  }

  /**
   * 右旋转
   *
   * @param x
   */
  private void rightRotate(Node<V> x) {
    Node<V> y = x.left;
    // 交换x的左孩子为y的右孩子
    x.left = y.right;
    if (y.right != NIL) {
      y.right.p = x;
    }
    // 交换x的位置为y
    y.p = x.p;
    if (x.p == NIL)
      root = y;
    else if (x == x.p.left)
      x.p.left = y;
    else x.p.right = y;
    // 交换y的右孩子为x
    x.p = y;
    y.right = x;
  }


  public static void main(String[] args) {
    RedBlackTree rbt = new RedBlackTree();
    rbt.add(6, 6);
    rbt.add(8, 8);
    rbt.add(10, 10);
    rbt.add(12, 12);
    rbt.add(11, 11);
  }
}
