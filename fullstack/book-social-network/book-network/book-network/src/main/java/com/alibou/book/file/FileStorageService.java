package com.alibou.book.file;

import jakarta.annotation.Nonnull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.io.File.separator;
import static java.lang.System.currentTimeMillis;

@Service
@Slf4j
@RequiredArgsConstructor
public class FileStorageService {

    @Value("${application.file.uploads.photos-output-path}")
    private String fileUploadPath;

    public String saveFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull Integer userId
    ) {
        final String fileUploadSubPath = "users" + separator + userId; // users/userId

        return uploadFile(sourceFile, fileUploadSubPath);
    }

    private String uploadFile(
            @Nonnull MultipartFile sourceFile,
            @Nonnull String fileUploadSubPath
    ) {
        final String finalUploadPath = fileUploadPath + separator + fileUploadSubPath; //   ./uploads/users/userId
        File targetFolder = new File(finalUploadPath);

        if (!targetFolder.exists()) {
            boolean folderCreated = targetFolder.mkdirs();
            // mkdir(): ينشئ فقط المجلد الحالي إذا كانت المجلدات الأب موجودة بالفعل، بينما
            // mkdirs() ينشئ المسار بالكامل إذا كان غير موجود.

            if (!folderCreated) {
                log.warn("Failed to create the target folder: " + targetFolder);
                return null;
            }
        }

        final String fileExtension = getFileExtension(sourceFile.getOriginalFilename()); // هي دالة تقوم باستخراج امتداد الملف (مثل jpg أو png) من اسم الملف الأصلي

        String targetFilePath = finalUploadPath + separator + currentTimeMillis() + "." + fileExtension; // EX : ./uploads/users/123/1671820370000.jpg

        Path targetPath = Paths.get(targetFilePath); //  تقوم بإنشاء كائن Path يمثل المسار الهدف الذي سيتم فيه حفظ الملف.

        try {
            // targetPath.
            // هي دالة من مكتبة java.nio.file.Files تستخدم لكتابة البيانات إلى الملف الذي يمثله المسار
            // sourceFile.getBytes():
            //هذه الدالة تستخرج محتوى الملف كمصفوفة من البايتات (byte array)
            Files.write(targetPath, sourceFile.getBytes());
            log.info("File saved to: " + targetFilePath);
            return targetFilePath;
        } catch (IOException e) {
            log.error("File was not saved", e);
        }
        return null;
    }

    private String getFileExtension(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return "";
        }
        int lastDotIndex = fileName.lastIndexOf(".");
        if (lastDotIndex == -1) {
            return "";
        }
        return fileName.substring(lastDotIndex + 1).toLowerCase();
    }
}
