import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-04-22 9:37
 * @Version: 1.0
 */
public class PatternUtils {

    public static String BLANK_REGEX = "[\\s  　]";


    public static boolean find(String text, String regex) {
        return find(text, Pattern.compile(regex));
    }

    public static boolean find(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        if (matcher.find()) {
            return true;
        }
        return false;
    }

    public static List<String> getFindGroups(String text, String regex) {
        return getFindGroups(text, Pattern.compile(regex));
    }

    public static List<String> getFindGroups(String text, Pattern pattern) {
        Matcher matcher = pattern.matcher(text);
        List<String> groups = new ArrayList<>();
        while (matcher.find()) {
            String group = matcher.group();
            groups.add(group);
        }
        return groups;
    }

    /**
     * 判断所有正则是否匹配，
     * 所有正则都匹配到返回true,否则返回false
     *
     * @param str
     * @param patternArr
     */
    public static boolean patternMatchers(String str, String[] patternArr) {
        int i;
        for (i = 0; i < patternArr.length; i++) {
            Pattern pattern = Pattern.compile(patternArr[i]);
            Matcher matcher = pattern.matcher(str);
            if (!matcher.matches()) {
                break;
            }
        }
        if (i == patternArr.length) {
            return true;
        }
        return false;
    }

    public static boolean checkRegex(String regex) {
        try {
            Pattern.compile(regex);
        } catch (Exception e) {
            return false;
        }
        List<Integer> indexs = StrUtils.getAllIndexs(regex, "|");
        for (Integer index : indexs) {
            //检查"|"的前后
            if (index == 0 || index == regex.length() - 1) {
                return false;
            }
            if (regex.charAt(index - 1) == '(' || regex.charAt(index + 1) == ')') {
                return false;
            }
        }
        return true;
    }
}
