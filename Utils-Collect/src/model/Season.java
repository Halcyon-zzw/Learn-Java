package model;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-07-27 14:47
 * @Version: 1.0
 */
public enum Season {
    SPRING("春"),
    SUMMER("夏"),
    AUTUMN("秋"),
    WINTER("冬");
    String season;
    Season(String season) {
        this.season = season;
    }

    public String getSeason() {
        return season;
    }
}