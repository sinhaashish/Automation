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
 * Created by ashishsinha on 3/22/2018.
 */
/**
 * This is the main class .
 * It reads the yml file to create the list of Configuration objects and property file
 * to create minio Object.
 * Based on the name in Yml file; different operations are performed using switch case.
 *
 */
public class EntryClass {
    public static void main(String[] args) throws IOException, InvalidPortException, InvalidEndpointException, NoSuchAlgorithmException, InvalidKeyException, XmlPullParserException, InternalException, ErrorResponseException, InsufficientDataException, InvalidBucketNameException, NoResponseException {
       if (args.length == 2) {
            List<Configuration> config = new YamlReaderService().getYamlData(args[0]);
            Properties property = new ReadPropertyFile().getPropertiesfromFile(args[1]);
            MinioClient minioClient = new MinioClient(property.getProperty("endpoint"), property.getProperty("accessKey"), property.getProperty("secretKey"));
            BucketOperations bucketOperations = new BucketOperations();
            ObjectOperations objectOperations = new ObjectOperations();
            Configuration updatedConf;
            for (Configuration conf : config) {

                switch (conf.getName().split(",")[0].trim()) {
                    case "":
                        System.out.println("The specified operation can't be blank");
                    case "createBucket":
                        while(conf.getRepeat() > 0) {
                            updatedConf = bucketOperations.makeBucket(minioClient, conf);
                            if (CheckControl.validateStatus(updatedConf)) {
                                System.out.println("Failed in createBucket ");
                                System.exit(0);
                            }
                        }
                    case "listBucket":
                        while (conf.getRepeat() > 0) {
                            updatedConf = bucketOperations.listBucket(minioClient, conf);
                            if (CheckControl.validateStatus(updatedConf)) {
                                System.out.println("Failed in listBucket ");
                                System.exit(0);
                            }
                        }
                    case "deleteBucket":
                        while (conf.getRepeat() > 0) {
                            updatedConf = bucketOperations.deleteBucket(minioClient, conf);
                            if (CheckControl.validateStatus(updatedConf)) {
                                System.out.println("Failed in deleteBucket ");
                                System.exit(0);
                            }
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
