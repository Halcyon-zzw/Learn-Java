import org.springframework.lang.Nullable;

import java.util.Collection;
import java.util.Map;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-09-03 13:58
 * @Version: 1.0
 */
public class CollectionUtils extends org.springframework.util.CollectionUtils {

    public static boolean isNotEmpty(@Nullable Collection<?> collection) {
        return collection != null && !collection.isEmpty();
    }

    public static boolean isNotEmpty(@Nullable Map<?, ?> map) {
        return map != null && !map.isEmpty();
    }
}
