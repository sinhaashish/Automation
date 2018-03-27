package service;


import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by ashishsinha on 3/25/2018.
 */

/**
 * * Class to Read the Property File
 */
public class ReadPropertyFile {

/**
 * Read Property file to create the MinioClient Object
 *
 */
    public Properties getPropertiesfromFile( String propertyFile) throws IOException {
        FileReader reader = new FileReader(propertyFile);
        Properties property = new Properties();
        property.load(reader);
        return property;
    }
}
