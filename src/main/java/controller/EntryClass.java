package controller;


import action.BucketOperations;
import action.CheckControl;
import action.ObjectOperations;
import core.Configuration;
import io.minio.MinioClient;
import io.minio.errors.*;
import org.xmlpull.v1.XmlPullParserException;
import service.ReadPropertyFile;
import service.YamlReaderService;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Properties;


/**
 * Created by asi292 on 3/22/2018.
 */
public class EntryClass {
    public static void main(String[] args) throws IOException, InvalidPortException, InvalidEndpointException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException, InternalException, ErrorResponseException, InsufficientDataException, InvalidBucketNameException, NoResponseException {
        if (args.length == 2) {
            String yamlFile = args[0];
            String PropertyFile = args[1];

            List<Configuration> config = new YamlReaderService().getYamlData(yamlFile);
            System.out.println(config);
            Properties property = new ReadPropertyFile().getPropertiesfromFile(PropertyFile);//("C:\\Users\\asi292\\Desktop\\MinioClient.properties");
            System.out.println(property);
            MinioClient minioClient = new MinioClient(property.getProperty("endpoint"), property.getProperty("accessKey"), property.getProperty("secretKey"));
            BucketOperations bucketOperations = new BucketOperations();
            ObjectOperations objectOperations = new ObjectOperations();
            Configuration updatedConf;
            for (Configuration conf : config) {

                switch (conf.getName().split(",")[0].trim()) {
                    case "":
                        System.out.println("The specified operation can't be blank");
                    case "createBucket":
                        updatedConf = bucketOperations.makeBucket(minioClient, conf);
                        if (CheckControl.validateStatus(updatedConf))
                            System.exit(0);
                    case "listBucket":
                        while (conf.getRepeat() > 0) {
                            updatedConf = bucketOperations.listBucket(minioClient, conf);
                        }
                    case "deleteBucket":
                        while (conf.getRepeat() > 0) {
                            updatedConf = bucketOperations.deleteBucket(minioClient, conf);
                            if (CheckControl.validateStatus(updatedConf))
                                System.exit(0);
                        }
                    case "getObject":
                        while (conf.getRepeat() > 0) {
                            updatedConf = objectOperations.getObject(minioClient, conf);
                            if (CheckControl.validateStatus(updatedConf))
                                System.exit(0);
                        }
                    case "putObject":
                        while (conf.getRepeat() > 0) {
                            updatedConf = objectOperations.putObject(minioClient, conf);
                            if (CheckControl.validateStatus(updatedConf))
                                System.exit(0);
                        }

                }


            }

        } else {
            System.out.println(" Please Enter the path to Yaml and the Property file");
        }
    }
}
