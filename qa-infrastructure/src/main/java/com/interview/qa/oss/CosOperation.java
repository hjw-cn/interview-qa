package com.interview.qa.oss;

import com.interview.qa.config.CosConfig;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@RequiredArgsConstructor
public class CosOperation {

    private final CosConfig config;
    private final COSClient cosClient;

    public boolean uploadFile(String fileName, String filePath, File file) {
        PutObjectRequest putObjectRequest = new PutObjectRequest(config.getBucketName(), fileName, file);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        return putObjectResult != null;
    }
}
