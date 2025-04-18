package org.api.automation.foundation.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.Assert;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static org.api.automation.foundation.constants.Constants.*;

@Slf4j
public class FileUtil {

    public static void generateFileChecksum(String targetPath, File file) {
        try {
            File checksumFile = new File(targetPath);
            String checksumValue = DigestUtils.sha256Hex(Files.newInputStream(file.toPath())) + EMPTY_SPACE + file.getName();
            FileUtils.writeStringToFile(checksumFile, checksumValue, CHARSET_UTF);
        } catch (IOException e) {
            log.error(LOG_ERROR_CHECKSUM + file);
        }
    }

    public static void zipFiles(String zipFilePath, File... files) {
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(Paths.get(zipFilePath)))) {
            for (File file : files) {
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                FileInputStream fileInputStream = new FileInputStream(file);
                IOUtils.copy(fileInputStream, zipOutputStream);
                fileInputStream.close();
                zipOutputStream.closeEntry();
            }
        } catch (IOException e) {
            log.error(LOG_ERROR_IO_ZIP, e);
        }
    }

    public static void overwriteFile(String targetPath, String content) {
        try {
            FileWriter fileWriter = new FileWriter(targetPath, false);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            log.error(LOG_ERROR_IO_OVERWRITE, e);
        }
    }

    public static void createFile(String targetPath) {
        try {
            Files.createFile(Paths.get(targetPath));
        } catch (IOException e) {
            log.error(LOG_ERROR_IO_FILE, e);
        }
    }

    public static String getTimestamp(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(Date.from(Instant.now()));
    }

    public static boolean compareFiles(String expectedFile, String outputFile) {
        try (InputStream inputStream1 = Files.newInputStream(Paths.get(expectedFile));
             InputStream inputStream2 = Files.newInputStream(Paths.get(outputFile))) {
            int byte1;
            int byte2;

            while ((byte1 = inputStream1.read()) != -1 && (byte2 = inputStream2.read()) != -1) {
                if (byte1 != byte2) {
                    return false;
                }
            }
            return inputStream1.read() == inputStream2.read();
        } catch (IOException e) {
            log.error(LOG_ERROR_IO_COMPARE, e);
        }
        return false;
    }

    public static boolean fileExists(String filePath) {
        return new File(filePath).exists();
    }

    public static byte[] getFileStream(String filepath) {
        try {
            return Files.readAllBytes(Paths.get(filepath));
        } catch (IOException e) {
            log.error(LOG_ERROR_IO_FILE_STREAM + filepath, e);
            Assert.fail(LOG_ERROR_IO_FILE_STREAM + filepath);
        }
        return null;
    }
}
