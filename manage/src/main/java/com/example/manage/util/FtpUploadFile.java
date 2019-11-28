//package com.example.manage.util;
//
//import com.common.utils.Global;
//import com.google.common.collect.Lists;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * @author 杨忠赢
// * @date 2017-2-22
// */
//public class FtpUploadFile {
//
//    private static Logger log = LoggerFactory.getLogger(FtpUploadFile.class);
//
//    private static String ftpUser;
//    private static String ftpPassword;
//    private static String ftpIp;
//    private static Integer ftpPort;
//    private static String ftpBasePath;
//    private static FTPClient fTPClient;
//    private static String nginxServer;
//    private static final SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmssSSS");
//
//    static {
//        ftpUser = Global.getConfig("ftp.user");
//        ftpPassword = Global.getConfig("ftp.pasword");
//        ftpIp = Global.getConfig("ftp.ip");
//        ftpPort = Integer.parseInt(Global.getConfig("ftp.port"));
//        ftpBasePath = Global.getConfig("ftp.basePath");
//        nginxServer = Global.getConfig("ftp.web");//nginx服务器地址
//    }
//
//    /**
//     * 测试
//     * @param args
//     */
//    public static void main(String[] args) {
//        FtpEngine ftp = new FtpEngine();
//        ftp.connectServer(ftpUser, ftpPassword, ftpIp, ftpPort);
//        fTPClient = ftp.getFtpClient();
//        //创建目录
//        boolean result = false;
//        try {
//            //result = ftp.CreateDirecroty(ftpBasePath + "card2/", fTPClient);
//            File file = new File("D:/wx.txt");
//            InputStream is = new FileInputStream(file);
//            uploadFileInputStream(is, "batchPay.xml", "test");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 上传文件到FTP指定目录
//     * @param is
//     * @param fileName
//     * @param folderName
//     * @return
//     */
//    public static Map<String, Object> uploadFileInputStream(InputStream is, String fileName, String folderName) {
//        FtpEngine ftp = new FtpEngine();
//        boolean result = true;
//        Map<String, Object> map = new HashMap<String, Object>();
//        try {
//            if(is == null || folderName == null) {
//                log.error("文件对象或参数为空");
//                map.put("result", false);
//                map.put("msg", "文件对象或参数为空");
//                return map;
//            }
//            ftp.connectServer(ftpUser, ftpPassword, ftpIp, ftpPort);
//            fTPClient = ftp.getFtpClient();
//            //创建目录
//            ftp.CreateDirecroty(ftpBasePath + folderName + "/", fTPClient);
//            ftp.uploadFileUtf8(is, fileName, ftpBasePath + "/" + folderName);
//            String url = nginxServer + folderName + "/" + fileName;
//            map.put("url", url);
//            map.put("fileName", fileName);
//        } catch (Exception e) {
//            result = false;
//            map.put("result", result);
//            map.put("msg", "上传文件至服务器异常");
//            log.error("上传文件至服务器异常" + e.getMessage());
//            e.printStackTrace();
//            return map;
//        } finally {
//            if(ftp != null) {
//                ftp.closeConnect();
//            }
//        }
//        map.put("result", result);
//        return map;
//    }
//
//
//    /**
//     * 上传文件
//     * @param file
//     * @param folderName
//     * @return
//     */
//    public static Map<String, String> uploadFile(MultipartFile file, String folderName) {
//        FtpEngine ftp = new FtpEngine();
//        boolean result = true;
//        Map<String, String> map = new HashMap<String, String>();
//        try {
//            if(file == null || folderName == null) {
//                log.error("文件对象或参数为空");
//                map.put("result", "9999");
//                map.put("msg", "文件对象或参数为空");
//                return map;
//            }
//            ftp.connectServer(ftpUser, ftpPassword, ftpIp, ftpPort);
//            fTPClient = ftp.getFtpClient();
//            //创建目录
//            ftp.CreateDirecroty(ftpBasePath + folderName + "/", fTPClient);
//            // 上传 如果需要把源文件上传ftp 去掉注释即可
//            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//            String fileName = df.format(new Date()) + suffix;
//            ftp.uploadFileUtf8(file.getInputStream(), fileName, ftpBasePath + "/" + folderName);
//
//            String url = nginxServer + folderName + "/" + fileName;
//            map.put("url", url);
//            map.put("fileName", fileName);
//        } catch (Exception e) {
//            map.put("result", "9999");
//            map.put("msg", "上传文件至服务器异常");
//            log.error("上传文件至服务器异常" + e.getMessage());
//            e.printStackTrace();
//            return map;
//        } finally {
//            if(ftp != null) {
//                ftp.closeConnect();
//            }
//        }
//        map.put("result", "0000");
//        return map;
//    }
//
//
//    /**
//     * 批量上传文件
//     * @param files
//     * @param folderName
//     * @return
//     */
//    public static List<Map<String, Object>> uploadFileList(MultipartFile[] files, String folderName) {
//        FtpEngine ftp = new FtpEngine();
//        Map<String, Object> map = null;
//        List<Map<String, Object>> fileList = Lists.newArrayList();
//        try {
//            if(files == null || folderName == null || files.length == 0) {
//                log.error("文件对象或参数为空");
//                return fileList;
//            }
//            ftp.connectServer(ftpUser, ftpPassword, ftpIp, ftpPort);
//            fTPClient = ftp.getFtpClient();
//            //创建目录
//            ftp.CreateDirecroty(ftpBasePath + folderName + "/", fTPClient);
//            for(MultipartFile file : files) {
//                map = new HashMap<String, Object>();
//                String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
//                String fileName = df.format(new Date()) + (new Random()).nextInt(1000) + suffix;
//                ftp.uploadFileUtf8(file.getInputStream(), fileName, ftpBasePath + "/" + folderName);
//                String url = nginxServer + folderName + "/" + fileName;
//                map.put("url", url);
//                map.put("fileName", fileName);
//                fileList.add(map);
//            }
//        } catch (Exception e) {
//            log.error("上传文件至服务器异常" + e.getMessage());
//            e.printStackTrace();
//            return null;
//        } finally {
//            if(ftp != null) {
//                ftp.closeConnect();
//            }
//        }
//        return fileList;
//    }
//
//    public static Map<String, String> uploadFileInputStream(InputStream is, String fileName) {
//        FtpEngine ftp = new FtpEngine();
//        Map<String, String> map = new HashMap<>(16);
//        try {
//            if(is == null) {
//                log.error("文件对象或参数为空");
//                map.put("code", "9000");
//                map.put("msg", "文件对象或参数为空");
//                return map;
//            }
//            ftp.connectServer(ftpUser, ftpPassword, ftpIp, ftpPort);
//            fTPClient = ftp.getFtpClient();
//            ftp.CreateDirecroty(ftpBasePath + "/", fTPClient);
//            ftp.uploadFileUtf8(is, fileName, ftpBasePath + "/");
//            String url = nginxServer + "/" + fileName;
//            map.put("code", "0000");
//            map.put("url", url);
//            map.put("fileName", fileName);
//        } catch (Exception e) {
//            map.put("code", "9000");
//            map.put("msg", "上传文件至服务器异常");
//            log.error("上传文件至服务器异常" + e.getMessage());
//            e.printStackTrace();
//            return map;
//        } finally {
//            if(ftp != null) {
//                ftp.closeConnect();
//            }
//        }
//        return map;
//    }
//
//
//}
