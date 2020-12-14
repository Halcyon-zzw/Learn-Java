import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-07-22 17:10
 * @Version: 1.0
 */
public class StrUtils {
    public static final String EMPTY = "";

    public static final String SYMBOL_STR = ",，；;。?？!！\t\n\r";

    /**
     * 判断两个字符串是否相同，字符串都为null也认为相同
     *
     * @param str1
     * @param str2
     * @return
     */
    public static boolean equalsIgnoreNull(String str1, String str2) {
        if (str1 == null && str2 == null) {
            return true;
        }
        if (str1 != null && str2 != null) {
            return str1.equals(str2);
        }
        return false;
    }

    /**
     * 中文数组转成阿拉伯数字
     *
     * @param str
     * @return
     */
    public static int toNumeric(String str) {
        String[] chineseNumbers = {"〇", "零", "一", "二", "三", "四", "五", "六", "七", "八", "九"};
        String[] numbers = {"0", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};

        Map<String, String> conversionMap = new HashMap<>();
        for (int i = 0; i < chineseNumbers.length; i++) {
            conversionMap.put(chineseNumbers[i], numbers[i]);
        }
        StringBuffer sb = new StringBuffer("");
        for (char c : str.toCharArray()) {
            String charStr = String.valueOf(c);
            if (conversionMap.get(charStr) != null) {
                sb.append(conversionMap.get(charStr));
            } else {
                sb.append(charStr);
            }
        }
        return Integer.valueOf(sb.toString());
    }

    public static String trimToEmpty(String str) {
        //空白字符hash：12288 、 160 、 32
        return trimToEmpty(str, "\n\r\t  　");
    }


    /**
     * 优化String的trim()方法，通过传入指定要删除字符串集合进行修剪
     *
     * @param str
     * @param trimedCharSeq 需要删除的字符集合
     * @return
     */
    public static String trim(String str, CharSequence trimedCharSeq) {
        String headAndTailStr = trimedCharSeq.toString();
        char[] strArr = str.toCharArray();
        int headIndex = 0;
        int tailIndex = str.length();
        while ((headIndex < tailIndex) && headAndTailStr.contains(String.valueOf(strArr[headIndex]))) {
            headIndex++;
        }
        while ((headIndex < tailIndex) && headAndTailStr.contains(String.valueOf(strArr[tailIndex - 1]))) {
            tailIndex--;
        }
        if (headIndex > 0 || tailIndex < str.length()) {
            //修改成功
            return str.substring(headIndex, tailIndex);
        }
        return str;
    }

    public static String trim(String str, char[] trimedCharArr) {
        return trim(str, trimedCharArr);
    }

    public static String trimToEmpty(String str, char[] trimedCharArr) {
        return str == null ? EMPTY : trim(str, trimedCharArr);
    }

    public static String trimToEmpty(String str, CharSequence charSequence) {
        return str == null ? EMPTY : trim(str, charSequence);
    }

    /**
     * 获取所有子串的位置
     *
     * @param text
     * @param str
     * @return
     */
    public static List<Integer> getAllIndexs(String text, String str) {
        List<Integer> indexs = new ArrayList<>();
        int index = text.indexOf(str);
        while (index != -1) {
            int start = index;
            int end = index + str.length();
            indexs.add(start);
            index = text.indexOf(str, end);
        }
        return indexs;
    }


    /**
     * 获取指定位置的最小句子信息
     *
     * <p>
     * 如：测试句子，结果是什么？
     * 指定 2,3（“句子”位置） => 返回“测试句子”；
     * 指定 5,6 （“结果”位置）=> 返回“结果是什么”
     * </p>
     *
     * @param start
     * @param end
     * @param text
     * @return
     */
    public static String getNearSenectentInfo(int start, int end, String text) {
        char[] chars = text.toCharArray();

        int tailSymbol = getNextSymbol(chars, end);
        int headSymbol = getLastSymbol(chars, start);
        return text.substring(headSymbol, tailSymbol);
    }

    public static int getLastSymbol(char[] chars, int start) {
        int headSymbol = start - 1;

        while (headSymbol >= 0) {
            if (SYMBOL_STR.contains(String.valueOf(chars[headSymbol]))) {
                break;
            }
            headSymbol--;
        }
        if (headSymbol < -1) {
            headSymbol = -1;
        }

        return headSymbol + 1;
    }

    public static int getNextSymbol(char[] chars, int end) {
        int tailSymbol = end;
        while (tailSymbol < chars.length) {
            if (SYMBOL_STR.contains(String.valueOf(chars[tailSymbol]))) {
                break;
            }
            tailSymbol++;
        }
        if (tailSymbol > chars.length) {
            tailSymbol = chars.length;
        }
        return tailSymbol;
    }


    public static List<String> removeRepeat(List<String> list) {
        Map<String, Boolean> map = new HashMap<>();
        List<String> tempList = new ArrayList<>();
        for (String s : list) {
            if (map.get(s) == null) {
                tempList.add(s);
                map.put(s, true);
            }
        }
        return tempList;
    }

    /**
     * 获取尾部最长公共序列
     * <p>
     * 如[学院, 院, 法院] => 院
     *
     * @param strList
     * @return
     */
    public static String getTailCommonCharacter(List<String> strList) {
        StringBuffer sb = new StringBuffer("");

        char[] tailCharArr = new char[strList.size()];
        //获取字符的数量
        int i = 0;
        while (true) {
            //遍历每个字符串
            int index = 0;
            for (; index < strList.size(); index++) {
                String s = strList.get(index);
                if (i == s.length()) {
                    break;
                }
                char c = s.charAt(s.length() - 1 - i);
                tailCharArr[index] = c;
            }
            if (index != strList.size()) {
                break;
            }
            //比较
            char compare = compare(tailCharArr);
            if (compare != '\0') {
                sb.append(compare);
            }
            i++;
        }
        return sb.reverse().toString();
    }

    private static char compare(char[] tailCharArr) {
        char tempChar = tailCharArr[0];
        int i1 = 0;
        for (; i1 < tailCharArr.length; i1++) {
            if (tempChar != tailCharArr[i1]) {
                break;
            }
        }
        if (i1 == tailCharArr.length) {
            return tempChar;
        }
        return '\0';
    }
}
