package com.aws.ec2;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.Map;
import java.util.TreeMap;

/**
 * Describes your EC2 instances.
 */
public class DescribeInstanseTest {

    public static void testDescribeInstances() throws Exception {

        String accessKeyID = "<accessKeyID>";
        String secretAccessKey = "<secretAccessKey>";
        String securityToken = "<securityToken>";
        String region = "us-east-1";
        String instanceIds = "i-0552e3bfc783a9203";

        String hostHeader = "ec2." + region + ".amazonaws.com";
        String canonicalURI = "/";

        TreeMap<String, String> awsHeaders = new TreeMap<>();
        awsHeaders.put("host", hostHeader);
        awsHeaders.put("content-type", "application/x-www-form-urlencoded; charset=utf-8");
        awsHeaders.put("x-amz-security-token", securityToken);

        TreeMap<String, String> queryParametes = new TreeMap<>();
        queryParametes.put("Action", "DescribeInstances");
        queryParametes.put("InstanceId.1", instanceIds);
        queryParametes.put("Version", "2016-11-15");

        AWSSignatureV4Generator aWSV4Auth = new AWSSignatureV4Generator.Builder(accessKeyID, secretAccessKey)
                .regionName(region)
                .serviceName("ec2") // es - elastic search. use your service name
                .httpMethodName("GET") //GET, PUT, POST, DELETE, etc...
                .canonicalURI(canonicalURI) //end point
                .queryParametes(queryParametes) //query parameters if any
                .awsHeaders(awsHeaders) //aws header parameters
                .payload(null) // payload if any
                .build();

        HttpGet httpGet = new HttpGet("https://" + hostHeader + canonicalURI + "?Action=DescribeInstances&InstanceId.1="
                + instanceIds + "&Version=2016-11-15");
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        /* Get header calculated for request */
        Map<String, String> header = aWSV4Auth.getHeaders();
        for (Map.Entry<String, String> entrySet : header.entrySet()) {
            String key = entrySet.getKey();
            String value = entrySet.getValue();
            httpGet.setHeader(key, value);
        }
        httpGet.setHeader("host", hostHeader);
        httpGet.setHeader("content-type", "application/x-www-form-urlencoded; charset=utf-8");
        httpGet.setHeader("x-amz-security-token",securityToken );

        HttpResponse response = httpClient.execute(httpGet);
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            System.out.println("Status Code is : " + statusCode);
            System.out.println("Response is : " + EntityUtils.toString(entity));
        }
    }

    public static void main(String[] args) throws Exception {

        testDescribeInstances();
    }
}

