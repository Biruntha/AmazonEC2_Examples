package com.aws.ec2;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2ClientBuilder;
import com.amazonaws.services.ec2.model.DescribeInstancesRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;

/**
 * Describes your EC2 instances.
 */
public class DescribeInstance {
    private static final AWSCredentials credentials;
    private static String accessKeyID = "<accessKeyID>";
    private static String secretAccessKey = "<accessKeyID>";
    private static String region = "us-east-1";
    private static String instanceIds = "i-0552e3bfc783a9203";

    static {
        // put your accesskey and secretkey here
        credentials = new BasicAWSCredentials(
                accessKeyID,
                secretAccessKey
        );
    }

    public static void main(String[] args) {
        // Set up the client
        AmazonEC2 ec2Client = AmazonEC2ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(region)
                .build();
        // Describe an Instance
        DescribeInstancesRequest describeInstancesRequest = new DescribeInstancesRequest()
                .withInstanceIds(instanceIds);
        DescribeInstancesResult response = ec2Client.describeInstances(describeInstancesRequest);
        System.out.println(response.toString());
    }
}
