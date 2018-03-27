package core;
/**
 * A simple POJO class to read the Yaml file Configuration.
 *
 */

public class Configuration{
    private String name;
    private int repeat;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRepeat() {
        return repeat;
    }

    public void setRepeat(int repeat) {
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Configuration{" +
                "name='" + name + '\'' +
                ", repeat=" + repeat +
                ", status='" + status + '\'' +
                '}';
    }
}