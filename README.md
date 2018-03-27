# Automation
AUTOMATION TOOL FOR TESTING MINIO API'S

The Minio-1.0-SNAPSHOT-jar-with-dependencies.jar present in target folder is used to run the Application.

Steps to run 
1. Create a Yaml file in the folder where Minio-1.0-SNAPSHOT-jar-with-dependencies.jar is downloaded . Sample 
Automation/src/main/resources/test.yml

2. Create the Property file in the folder where Minio-1.0-SNAPSHOT-jar-with-dependencies.jar is downloaded. Sample 
Automation/src/main/resources/MinioClient.properties

3. Start the Minio Server 

4. Run the below command in prompt
java -jar Minio-1.0-SNAPSHOT-jar-with-dependencies.jar test.yml MinioClient.properties
