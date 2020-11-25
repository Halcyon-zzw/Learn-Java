package safe.transfer;

/**
 * TODO
 *
 * @Author: zhuzw
 * @Date: 2020-10-20 18:37
 * @Version: 1.0
 */
public class Account {
    private int money;

    public Account(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void transfer(Account targetAccount, int transferMoney) {
        if (this.money > transferMoney) {
            this.setMoney(this.money - transferMoney);
            targetAccount.setMoney(targetAccount.getMoney() + transferMoney);
        }
    }

    /**
     * 锁Account.class能解决安全问题，但效率低
     * 某一时刻只能有一对账户进行转账
     * @param targetAccount
     * @param transferMoney
     */
    public void transferSolve(Account targetAccount, int transferMoney) {
        synchronized(Account.class) {
            if (this.money > transferMoney) {
                this.setMoney(this.money - transferMoney);
                targetAccount.setMoney(targetAccount.getMoney() + transferMoney);
            }
        }
    }
}
