import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

/**
 * 字符工具类
 *
 * @Author: zhuzw
 * @Date: 2020-07-08 15:15
 * @Version: 1.0
 */
public class StrUtils {
    public static final String EMPTY = "";


    public static final String SYMBOL_STR = "[,，；;。?？!！\t\n\r]";

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
     * 是否为纯英文，包括字符
     *
     * @return
     */
    public static boolean isLetter(String str) {
        Matcher matcher = PatternUtil.ENGLISH_PATTERN.matcher(str);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 裁剪首尾空白字符
     * @param str
     * @return
     */
    public static String trim(String str) {
        return StrProcess.trim(str);
    }

    public static String trim(String str, char[] trimedCharArr) {
        return StrProcess.trim(str, trimedCharArr);
    }

    /**
     * 优化String的trim()方法，通过传入指定要删除字符串集合进行修剪
     *
     * @param str
     * @param trimedCharSeq 需要删除的字符集合
     * @return
     */
    public static String trim(String str, CharSequence trimedCharSeq) {
        return StrProcess.trim(str, trimedCharSeq);
    }

    /**
     * 裁剪对象为null时返回""
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : trim(str);
    }

    public static String trimToEmpty(String str, char[] trimedCharArr) {
        return str == null ? EMPTY : trim(str, trimedCharArr);
    }

    public static String trimToEmpty(String str, CharSequence charSequence) {
        return str == null ? EMPTY : trim(str, charSequence);
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
     *
     * 如[学院, 院, 法院] => 院
     * @param strList
     * @return
     */
    public static String getTailCommonCharacter(List<String> strList) {
        StringBuffer sb = new StringBuffer();

        char[] tailCharArr = new char[strList.size()];
        //获取字符的数量
        int i = 0;
        while (true) {
            //遍历每个字符串
            int index = 0;
            for (; index < strList.size(); index ++) {
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

    public static char compare(char[] tailCharArr) {
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
    public static String processConBlankCharacter(String str) {
        return StrProcess.processConBlankCharacter(str );
    }
    public static List<String> getParagraphs(String doument) {
        return StrProcess.getParagraphs(doument);
    }
    public static List<String> getSentences(String text) {
        return StrProcess.getSentences(text);
    }

    public static List<String> getMinSentences(String text) {
        return StrProcess.getMinSentences(text);
    }
}

interface StrConstant {

}

/**
 * 字符串加工相关方法
 */
class StrProcess {
    public static final String EMPTY = "";

    public static final String SPACE = " ";

    /**
     * 获取小句子的符号集合
     */
    public static final String SYMBOL_STR = "[,，；;。?？!！\t\n\r]";

    public static final String SENTENCE_SYMBOL_STR = "[。？?！!；;]";
    /**
     * 非换行符的空白字符 8194、160、12288
     */
    public static String NON_WRAP_BLANK_CHARACTER_REGEX = "(?![\n\r])[\\s  　]{1,}";
    /**
     * 所有空白字符 32、8194、160、12288
     */
    public static String BLANK_CHARATER_STR = "\n\r\t   　";
    /**
     * 裁剪首尾空白字符
     * @param str
     * @return
     */
    public static String trim(String str) {
        //空白字符hash：12288 、 160 、 32
        return trim(str, BLANK_CHARATER_STR);
    }

    public static String trim(String str, char[] trimedCharArr) {
        return trim(str, String.valueOf(trimedCharArr));
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

    /**
     * 裁剪对象为null时返回""
     * @param str
     * @return
     */
    public static String trimToEmpty(String str) {
        return str == null ? EMPTY : trim(str);
    }

    public static String trimToEmpty(String str, char[] trimedCharArr) {
        return str == null ? EMPTY : trim(str, trimedCharArr);
    }

    public static String trimToEmpty(String str, CharSequence charSequence) {
        return str == null ? EMPTY : trim(str, charSequence);
    }


    /**
     * processContinuousBlankCharacter
     *
     * 将连续的空白字符替换为一个空格
     * @return
     */
    public static String processConBlankCharacter(String str) {
        return str.replaceAll(NON_WRAP_BLANK_CHARACTER_REGEX, SPACE);
    }

    /**
     * 获取文章段落
     *
     * @param document
     * @return
     */
    public static List<String> getParagraphs(String document) {
        //句子结束符
        List<String> pargraphList = new ArrayList<>();
        String[] paragraphs = document.split("[\n\r]");

        for (int i = 0; i < paragraphs.length; i++) {
            if (! StringUtils.isEmpty(paragraphs[i])) {
                pargraphList.add(paragraphs[i]);
            }
        }
        return pargraphList;
    }

    public static List<String> getSentences(String text) {
        return getSentences(text, SENTENCE_SYMBOL_STR);
    }

    public static List<String> getSentences(String text, String sentenceSeparator) {
        List<String> sentences = new ArrayList<>();
        List<Character> symbolList = new ArrayList<>();
        int symbolIndex = 0;
        for (String sentence : text.split(sentenceSeparator)) {
            if (sentence.length() == 0) {
                continue;
            }
            symbolIndex += sentence.length();

            char c = '\0';
            if (symbolIndex < text.length()) { //  防止出现无标点结尾情况
                c = text.charAt(symbolIndex);
                sentences.add(sentence + c);
            }else {
                sentences.add(sentence);
            }
            symbolIndex ++;
        }
        return sentences;
    }

    public static List<String> getMinSentences(String text) {
        return getSentences(text, SYMBOL_STR);
    }

}