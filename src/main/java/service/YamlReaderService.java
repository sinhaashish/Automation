package service;
import controller.EntryClass;
import core.Api;
import core.Configuration;
import org.yaml.snakeyaml.Yaml;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ashishsinha on 3/25/2018.
 */

/**
 * * Class to Read the Yaml File
 */
public class YamlReaderService
{
    /**
     * Method to get the Yaml Data .
     * @param fileName the name of input file
     * @return List of Configuration Objects
     * @throws IOException
     *
     */
    public List<Configuration> getYamlData(String fileName) throws IOException {
        LinkedList<Configuration> config = new LinkedList<>();
        Yaml yaml = new Yaml();
        InputStream in = new FileInputStream(fileName);
            Api api = yaml.loadAs(in, Api.class);
            for (Configuration configuration : api.getApi()) {
                config.add(configuration);
            }
            return config;
    }
}