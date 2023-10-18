package cn.tedu.store.entity;

/**
 * @ClassName ProcessLog
 * @Description TODO
 * @Author lihao
 * @Date 2023/10/18 9:58
 * @Version 1.0
 */
public class ProcessLog extends BaseEntity{

    private static final long serialVersionUID = 4978379304703723724L;

    private int logid;
    private String username;

    public int getLogid() {
        return logid;
    }

    public String getUsername() {
        return username;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "ProcessLog{" +
                "logid=" + logid +
                ", username='" + username + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProcessLog that = (ProcessLog) o;

        if (logid != that.logid) return false;
        return username != null ? username.equals(that.username) : that.username == null;
    }

    @Override
    public int hashCode() {
        int result = logid;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        return result;
    }
}
