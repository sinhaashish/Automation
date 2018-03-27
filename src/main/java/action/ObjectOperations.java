package action;

import core.Configuration;
import io.minio.MinioClient;
import io.minio.errors.MinioException;
import org.xmlpull.v1.XmlPullParserException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * Created by ashishsinha on 3/26/2018.
 */
/**
 * Clsss to perform all the Obhject level Operation
 *
 */
public class ObjectOperations {

    /**
     * Get Obejct Prresent in Bucket .
     * @param minioClient clinet object
     * @param conf Configuration Class object
     * @throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException
     *
     */

    public Configuration getObject(MinioClient minioClient, Configuration conf) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            minioClient.statObject(conf.getName().split(",")[1].trim(), conf.getName().split(",")[2].trim());
            InputStream stream = minioClient.getObject(conf.getName().split(",")[1].trim(), conf.getName().split(",")[2].trim());
            byte[] buf = new byte[16384];
            int bytesRead;
            while ((bytesRead = stream.read(buf, 0, buf.length)) >= 0) {
                System.out.println(new String(buf, 0, bytesRead));
            }
            stream.close();
            conf.setStatus("Success");
            conf.setRepeat(conf.getRepeat()-1);
        } catch (MinioException e) {
            System.out.println("Error occurred: " + e);
            conf.setStatus("Failure");
        }

    return  conf;
    }
    /**
     * Put Obejct Prresent in Bucket .
     * @param minioClient clinet object
     * @param conf Configuration Class object
     * @throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException
     *
     */


    public Configuration putObject(MinioClient minioClient, Configuration conf) throws XmlPullParserException, NoSuchAlgorithmException, InvalidKeyException, IOException {
        try {
            StringBuilder builder = new StringBuilder();
            for (int i = 0; i < 10000; i++) {

                builder.append("Cozy lummox gives smart squid who asks for job pen: A 41-letter tester sentence for Mac ");
                builder.append("computers after System 7.\n");
                builder.append("A few others we like: Amazingly few discotheques provide jukeboxes; Now fax quiz Jack! my ");
                builder.append("brave ghost pled; Watch Jeopardy!, Alex Trebeks fun TV quiz game.\n");
                builder.append("- --\n");
            }
            ByteArrayInputStream bais = new  ByteArrayInputStream(builder.toString().getBytes("UTF-8"));
            // Create an object
            minioClient.putObject(conf.getName().split(",")[1].trim(), conf.getName().split(",")[2].trim(), bais, bais.available(), "application/octet-stream");
            bais.close();
            conf.setStatus("Success");
            conf.setRepeat(conf.getRepeat()-1);
            System.out.println(conf.getName().split(",")[1].trim() +" is uploaded successfully");
        } catch(MinioException e) {
            conf.setStatus("Failure");
            System.out.println("Error occurred: " + e);
        }
        return  conf;
    }
}
