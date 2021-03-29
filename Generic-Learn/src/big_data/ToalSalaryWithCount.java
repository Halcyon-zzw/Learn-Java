package big_data;

/**
 * 总薪资及数量
 *
 * @Author: zhuzw
 * @Date: 2021-01-12 10:40
 * @Version: 1.0
 */
public class ToalSalaryWithCount {
    private int toalSalary;

    private int count;

    public ToalSalaryWithCount(int toalSalary, int count) {
        this.toalSalary = toalSalary;
        this.count = count;
    }

    public int getToalSalary() {
        return toalSalary;
    }

    public void setToalSalary(int toalSalary) {
        this.toalSalary = toalSalary;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
