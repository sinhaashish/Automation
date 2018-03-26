package service;

import com.oracle.webservices.internal.api.message.PropertySet;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by asi292 on 3/25/2018.
 */
public class ReadPropertyFile {
    public Properties getPropertiesfromFile( String propertyFile) throws IOException {
        FileReader reader = new FileReader(propertyFile);
        Properties property = new Properties();
        property.load(reader);
        System.out.print(property.getProperty("user"));
        return property;

    }

}
