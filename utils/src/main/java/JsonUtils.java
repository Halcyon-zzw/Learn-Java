import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-07-30 13:47
 * @Version: 1.0
 */
public class JsonUtils {
    private static ObjectMapper mapper = new ObjectMapper();

    public static String objToJson(Object obj) {
        String value = null;
        try {
            value = mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            System.out.println(e);
            value = obj.toString();
        }
        return value;
    }

    public static <T> T jsonToObj(String json, Class<T> clazz) throws IOException {
        return mapper.readValue(json, clazz);
    }

}
