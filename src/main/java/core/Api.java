package core;

import java.util.List;

/**
 * Created by ashishsinha on 3/22/2018.
 */

/**
 * A simple POJO class to read the Yaml file Configuration containing the list of Configurations.
 *
 */
public class Api {
    private List<Configuration> api;
    public List<Configuration> getApi() {
        return api;
    }
    public void setApi(List<Configuration> api) {
        this.api = api;
    }
}
