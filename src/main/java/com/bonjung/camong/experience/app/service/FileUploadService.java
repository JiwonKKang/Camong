package com.bonjung.camong.experience.app.service;

import com.bonjung.camong.common.exception.CustomException;
import com.bonjung.camong.common.exception.ErrorCode;
import com.bonjung.camong.experience.domain.entity.MediaFile;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadService {

    private final Storage storage;

    @Value("${spring.cloud.gcp.storage.bucket}")
    private String bucketName;

    @Value("${spring.cloud.gcp.storage.url}")
    private String storagePath;

    public MediaFile uploadFileInStorage(MultipartFile file) {

        if (file == null) {
            return null;
        }

        String ext = file.getContentType();
        String uuid = UUID.randomUUID().toString();

        BlobInfo blobInfo = BlobInfo.newBuilder(bucketName, uuid)
                .setContentType(ext)
                .build();

        try {
            Blob blob = storage.create(blobInfo, file.getInputStream());
        } catch (IOException e) {
            log.warn("image Upload fail");
            throw new CustomException(ErrorCode.IMAGE_UPLOAD_FAIL_ERROR);
        }
        return MediaFile.from(storagePath + uuid);
    }
}
