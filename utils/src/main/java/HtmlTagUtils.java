import org.apache.commons.lang3.StringUtils;
/**
 * HTML标签工具类
 *
 * @author luzh21574
 * @version 1.0 2019-08-02
 */
public class HtmlTagUtils {

    public static final String BEGIN_TD_HTML = "<td>";
    public static final String END_TD_HTML = "</td>";
    public static final String BEGIN_TR_HTML = "<tr>";
    public static final String END_TR_HTML = "</tr>";
    public static final String BEGIN_TH_HTML = "<th>";
    public static final String END_TH_HTML = "</th>";
    public static final String BEGIN_TABLE_HTML = "<table>";
    public static final String END_TABLE_HTML = "</table>";
    public static final String BEGIN_DIV_HTML = "<div>";
    public static final String END_DIV_HTML = "</div>";
    public static final String END_P_HTML = "</p>";
    public static final String END_H_HTML = "</h[1-6]>";
    public static final String BEGIN_BR_HTML = "<br>";
    public static final String END_BR_HTML = "</br>";
    /**
     * js标签
     */
    private static final String REG_EX_SCRIPT = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?script[\\s]*?>";
    /**
     * css标签
     */
    private static final String REG_EX_STYLE = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?/[\\s]*?style[\\s]*?>";
    /**
     * html标签
     */
    private static final String REG_EX_HTML = "<[^>]+>";
    /**
     * 其他转义符
     */
    private static final String REG_EX_SPECIAL = "&[a-zA-Z]{1,10};";
    /**
     * 删除标签内容正则
     */
    private static final String REG_DELETE_LABEL = "<%s((?!<%s).)*?</(%s)?>";
    private static final boolean CONTEXT_CLEAN = true;
    private static boolean SPECIAL_LABEL_PROCESS = true;

    /**
     * 清洗html前对字符串处理（清洗、特殊标签处理）
     *
     * @param srcStr
     * @return
     */
    public static String removeHtmlTagWithProcess(String srcStr) {
        if (CONTEXT_CLEAN) {
            srcStr = contextClean(srcStr);
        }

        if (SPECIAL_LABEL_PROCESS) {
            srcStr = specialLabelProcess(srcStr);
        }
        return removeHtmlTag(srcStr);
    }

    /**
     * 直接删除htnl标签，不做其他处理
     *
     * @param srcStr
     * @return
     */
    public static String removeHtmlTag(String srcStr) {
        return StringUtils.isBlank(srcStr) ? StringUtils.EMPTY :
                srcStr.replace(END_BR_HTML, "\n")
                        .replaceAll(REG_EX_SCRIPT, "")
                        .replaceAll(REG_EX_STYLE, "")
                        .replaceAll(REG_EX_HTML, "")
                        .replaceAll(REG_EX_SPECIAL, "");
    }

    public static String contextClean(String srcStr) {
        return StringUtils.isBlank(srcStr) ? StringUtils.EMPTY :
                srcStr.replaceAll("\\\\n", "\n")
                        .replaceAll("\\\\t", "\t");
    }

    /**
     * 特殊标签处理
     *
     * @return
     */
    public static String specialLabelProcess(String srcStr) {
        return StringUtils.isBlank(srcStr) ? StringUtils.EMPTY :
                srcStr.replaceAll(END_TD_HTML, END_TD_HTML + "\t")
                        .replaceAll(END_TR_HTML, END_TR_HTML + "\n")
                        .replaceAll(END_TH_HTML, END_TH_HTML + "\n")
                        .replaceAll(END_P_HTML, END_P_HTML + "\n")
                        .replaceAll(END_H_HTML, END_H_HTML + "\n")
                        .replaceAll(END_DIV_HTML, END_DIV_HTML + "\n");
    }

    /**
     * 指定标签后添加字符串
     *
     * @param str       被操作的字符串
     * @param label     指定标签
     * @param appendStr 需要添加的字符串
     * @return
     */
    public static String labelAppendProcess(String str, String label, String appendStr) {
        return StringUtils.isBlank(str) ? StringUtils.EMPTY :
                str.replaceAll(label, label + appendStr);
    }

    /**
     * 删除指定标签的内容
     *
     * @param str
     * @param labels
     * @return
     */
    public static String deleteLabel(String str, String... labels) {
        if (StringUtils.isBlank(str)) {
            return StringUtils.EMPTY;
        }
        for (String label : labels) {
            String regex = String.format(REG_DELETE_LABEL, label, label, label);
            str = str.replaceAll(regex, "");
        }
        return str;
    }
}
