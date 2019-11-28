package com.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * @author admin
 */
public class ZipToFile {

    private static final Logger logger = LoggerFactory.getLogger(ZipToFile.class);

    private static int BUFFER_SIZE = 2048;

    /**
     * 打ZIP包
     */
    public static boolean zipCompress(List<String> srcFiles, String desFile) {
        boolean isSuccessful = false;
        logger.info("压缩开始");
        String[] fileNames = new String[srcFiles.size()];
        for (int i = 0; i < srcFiles.size(); i++) {
            fileNames[i] = parse(srcFiles.get(i));
        }

        try {

            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(desFile));
            ZipOutputStream zos = new ZipOutputStream(bos);
            String entryName = null;
            if (fileNames.length > 1) {
                entryName = fileNames[0];
            }

            for (int i = 0; i < fileNames.length; i++) {
                entryName = fileNames[i];

                // 创建Zip条目
                ZipEntry entry = new ZipEntry(entryName);
                zos.putNextEntry(entry);
                BufferedInputStream bis;
                try {
                    bis = new BufferedInputStream(new FileInputStream(srcFiles.get(i)));
                } catch (IOException e) {
                    continue;
                }
                byte[] b = new byte[1024];
                while (bis.read(b, 0, 1024) != -1) {
                    zos.write(b, 0, 1024);
                }
                bis.close();
                zos.closeEntry();
            }
            zos.flush();
            zos.close();
            isSuccessful = true;
        } catch (ZipException e1) {
            logger.error("无压缩文件");
            return false;
        } catch (IOException e) {
            logger.error("压缩失败", e);
            return false;
        }
        logger.info("压缩成功");
        return isSuccessful;
    }

    /**
     * 解析文件名
     *
     * @param srcFile
     * @return
     */
    private static String parse(String srcFile) {
        int location = srcFile.lastIndexOf("/");
        String fileName = srcFile.substring(location + 1);
        return fileName;
    }

    /**
     * zip文件解压
     *
     * @param srcFile
     * @param destDirPath
     * @throws RuntimeException
     */
    public static void unZip(File srcFile, String destDirPath) throws RuntimeException {
        long start = System.currentTimeMillis();
        // 判断源文件是否存在
        if (!srcFile.exists()) {
            throw new RuntimeException(srcFile.getPath() + "所指文件不存在");
        }
        // 开始解压
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(srcFile);
            Enumeration<?> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = (ZipEntry) entries.nextElement();
                logger.info("解压" + entry.getName());
                // 如果是文件夹，就创建个文件夹
                if (entry.isDirectory()) {
                    String dirPath = destDirPath + "/" + entry.getName();
                    File dir = new File(dirPath);
                    dir.mkdirs();
                } else {
                    // 如果是文件，就先创建一个文件，然后用io流把内容copy过去
                    File targetFile = new File(destDirPath + "/" + entry.getName());
                    // 保证这个文件的父文件夹必须要存在
                    if (!targetFile.getParentFile().exists()) {
                        targetFile.getParentFile().mkdirs();
                    }
                    targetFile.createNewFile();
                    // 将压缩文件内容写入到这个文件中
                    InputStream is = zipFile.getInputStream(entry);
                    FileOutputStream fos = new FileOutputStream(targetFile);
                    int len;
                    byte[] buf = new byte[BUFFER_SIZE];
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                    }
                    // 关流顺序，先打开的后关闭
                    fos.close();
                    is.close();
                }
            }
            long end = System.currentTimeMillis();
            logger.info("解压完成，耗时：" + (end - start) + " ms");
        } catch (Exception e) {
            throw new RuntimeException("unzip error from ZipUtils", e);
        } finally {
            if (zipFile != null) {
                try {
                    zipFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
