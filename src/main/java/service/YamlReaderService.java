package service;
import controller.EntryClass;
import core.Api;
import core.Configuration;
import org.yaml.snakeyaml.Yaml;


import java.io.IOException;
import java.io.InputStream;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class YamlReaderService
{

    public List<Configuration> getYamlData(String fileName) throws IOException {
        LinkedList<Configuration> config = new LinkedList<>();
        Yaml yaml = new Yaml();
        try (InputStream in = YamlReaderService.class.getResourceAsStream(fileName)) {
            Api api = yaml.loadAs(in, Api.class);
            for (Configuration configuration : api.getApi()) {
                System.out.println(configuration);
                config.add(configuration);
            }

        }
        return config;
    }
}