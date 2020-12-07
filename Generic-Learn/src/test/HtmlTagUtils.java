package test;
//import org.apache.commons.lang3.StringUtils;
/**
 * HTML标签工具类
 *
 * @author luzh21574
 * @version 1.0 2019-08-02
 */
public class HtmlTagUtils {

    /** js标签 */
    private static final String REG_EX_SCRIPT = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?script[\\s]*?>";
    /** css标签 */
    private static final String REG_EX_STYLE = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?style[\\s]*?>";
    /** html标签 */
    private static final String REG_EX_HTML = "<[^>]+>";
    /** 其他转义符 */
    private static final String REG_EX_SPECIAL = "&[a-zA-Z]{1,10};";

//    public static String removeHtmlTag(String srcStr) {
//        return StringUtils.isBlank(srcStr) ? StringUtils.EMPTY : srcStr.replaceAll(REG_EX_SCRIPT, "").replaceAll(REG_EX_STYLE, "")
//                .replaceAll(REG_EX_HTML, "").replaceAll(REG_EX_SPECIAL, "");
//    }
}
