package action;

import core.Configuration;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.minio.messages.Bucket;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Created by asi292 on 3/25/2018.
 */
public class BucketOperations {

    public Configuration makeBucket(MinioClient minioClient, Configuration conf) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
          try {
            // Create bucket if it doesn't exist.
            boolean found = minioClient.bucketExists(conf.getName().split(",")[1].trim());
            if (found) {
                System.out.println("mybucket already exists");
                conf.setStatus("Failure");
            } else {
                // Create bucket 'my-bucketname'.
                minioClient.makeBucket(conf.getName().split(",")[1].trim());
                System.out.println(conf.getName().split(",")[1].trim() +" is created successfully");
                conf.setStatus("Success");
            }
        } catch (MinioException e) {
            System.out.println("Error occurred in createBucket" + e);
            conf.setStatus("Failure");
        }
        return  conf;
    }



    public Configuration listBucket(MinioClient minioClient, Configuration conf) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException, InsufficientDataException, ErrorResponseException, InvalidBucketNameException, InternalException, NoResponseException {
    try{

        List<Bucket> bucketList = minioClient.listBuckets();
        for (Bucket bucket : bucketList) {
            System.out.println(bucket.creationDate() + ", " + bucket.name());
        }
        conf.setRepeat(conf.getRepeat()-1);
    } catch (MinioException e) {
        System.out.println("Error occurred in listBucket: " + e);
        conf.setStatus("Failure");
        }
        return  conf;
    }


    public Configuration deleteBucket(MinioClient minioClient, Configuration conf) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            // Create bucket if it doesn't exist.
            boolean found = minioClient.bucketExists(conf.getName().split(",")[1].trim());
            if (found) {
                minioClient.removeBucket(conf.getName().split(",")[1].trim());
                System.out.println(conf.getName().split(",")[1].trim()+ " is removed successfully");
                conf.setStatus("Success");
            } else {
                System.out.println(conf.getName().split(",")[1].trim() +"  does not exist");
                conf.setStatus("Failure");
            }
        } catch (MinioException e) {
            System.out.println("Error occurred in deleteBucket" + e);
            conf.setStatus("Failure");
        }
        return  conf;
    }





}
