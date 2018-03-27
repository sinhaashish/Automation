# Automation
Automation Tool For Testing Minio API'S

The  Automation tool is used to test different Minio Api's.  Minio-1.0-SNAPSHOT-jar-with-dependencies.jar present in target folder is a fat jar which is used to run the Automation.

# Minimum Requirements


OracleJDK 8.0

Visit http://openjdk.java.net/install/

# Download from maven
```
<dependency>
    <groupId>org.yaml</groupId>
    <artifactId>snakeyaml</artifactId>
    <version>1.20</version>
    </dependency>
<dependency>
    <groupId>io.minio</groupId>
    <artifactId>minio</artifactId>
    <version>3.0.12</version>
</dependency>
```

# Steps to execute 
**1. Create a yaml File**
  Create a Yaml file in the folder where Minio-1.0-SNAPSHOT-jar-with-dependencies.jar is downloaded . Sample 
Automation/src/main/resources/test.yml

**Example**
```
api:
- name: listBucket
  repeat: 2
  status: Test

- name: createBucket,samplebucket
  repeat: 1
  status: Test
  
- name: putObject,samplebucket,abc
  repeat: 2
  status: Test

- name: deleteBucket,samplebucke
  repeat: 2
  status: Test
  
 ```
**2. Create a property File**
  Create the Property file in the folder where Minio-1.0-SNAPSHOT-jar-with-dependencies.jar is downloaded. Sample 
Automation/src/main/resources/MinioClient.properties

**Example**
```
endpoint=http://127.0.0.1:9000
accessKey=minio
secretKey=minio123
```

**3.Start the Minio Server**


**4. Run the below command in prompt**

```
java -jar Minio-1.0-SNAPSHOT-jar-with-dependencies.jar test.yml MinioClient.properties
```

# Author

* **Ashish Sinha**
