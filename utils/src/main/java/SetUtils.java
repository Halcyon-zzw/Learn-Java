import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 * @Author: zhuzw
 * @Date: 2020-07-17 10:01
 * @Version: 1.0
 */
public class SetUtils {

  /**
   * 获取并集
   * @param set1
   * @param set2
   * @return
   */
  public static Set<String> union(Set<String> set1, Set<String> set2) {
    if (CollectionUtils.isEmpty(set1)) {
      return set2;
    }
    if (CollectionUtils.isEmpty(set2)) {
      return set1;
    }
    set1.addAll(set2);
    return set1;
  }

  /**
   * 获取交集
   * @param set1
   * @param set2
   * @param <T>
   * @return
   */
  public static <T> Set<T> intersection(Set<T> set1, Set<T> set2) {
    if (CollectionUtils.isEmpty(set1) || CollectionUtils.isEmpty(set2)) {
      return new HashSet<>();
    }
    Set<T> intersectionSet = new HashSet<>();
    for (T t : set1) {
      if (set2.contains(t)) {
        intersectionSet.add(t);
      }
    }
    return intersectionSet;
  }

    /**
     * 比较两个set对象是否元素相同
   * @param set1
     * @param set2
     * @param <T>
     * @return
     */
  public static <T> boolean isSame(Set<T> set1, Set<T> set2) {
    if (set1 == null && set2 == null) {
      return true;
    }
    if (set1 == null || set2 == null) {
      return false;
    }
    if (set1.size() != set2.size()) {
      return false;
    }
    for (T t : set1) {
      if (!set2.contains(t)) {
        return false;
      }
    }
    return true;
  }
}
