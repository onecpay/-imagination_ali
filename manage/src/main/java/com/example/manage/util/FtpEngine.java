//package com.example.manage.util;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
///**
// * 支持断点续传的FTP实用类
// *
// * @version 0.3 实现中文目录创建及中文文件创建，添加对于中文的支持 Created by yuderen on 2016-12-20.
// */
//public class FtpEngine {
//
//    private static Logger log = LoggerFactory.getLogger(FtpEngine.class);
//    private FTPClient ftpClient = null;
//    private final String[] FILE_TYPES = {"文件", "目录", "符号链接", "未知类型"};
//
//    /**
//     * 设置FTP客服端的配置--一般可以不设置
//     *
//     * @return
//     */
//    private FTPClientConfig getFtpConfig() {
//        FTPClientConfig ftpConfig = new FTPClientConfig(FTPClientConfig.SYST_UNIX);
//        ftpConfig.setServerLanguageCode(FTP.DEFAULT_CONTROL_ENCODING);
//        return ftpConfig;
//    }
//
//    /**
//     * 连接到服务器
//     */
//    public void connectServer(String username, String password, String ip, int port) {
//        if (ftpClient == null) {
//            int reply;
//            try {
//                //setArg(configFile);
//                ftpClient = new FTPClient();
//                ftpClient.setControlEncoding("utf-8");
//                ftpClient.configure(getFtpConfig());
//                ftpClient.connect(ip);
//                ftpClient.login(username, password);
//                ftpClient.setDefaultPort(port);
//                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);//此设置可以用来上传BINARY二进制文件 比如 XXX.zip
//                log.info("Reply" + ftpClient.getReplyString());
//                reply = ftpClient.getReplyCode();
//
//                if (!FTPReply.isPositiveCompletion(reply)) {
//                    ftpClient.disconnect();
//                    System.err.println("FTP server refused connection.");
//                }
//            } catch (Exception e) {
//                log.info("登录ftp服务器【" + ip + "】失败");
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 上传文件
//     *
//     * @param inputStream --文件输入流
//     * @param newFileName --新的文件名
//     */
//    public void uploadFile(InputStream inputStream, String newFileName, String filedir) {
//
//        // 上传文件
//        BufferedInputStream buffIn = null;
//        try {
//            // 去掉   返回的值文件夹存在一直是false
//            //if (!CreateDirecroty(filedir, ftpClient)) {
//            //    return;
//            //}
//            boolean changeSucess = changeWorkingDirectory(filedir);// 进入文件夹
//            if (!changeSucess) {
//                if (CreateDirecroty(filedir, ftpClient)) {
//                    changeSucess = changeWorkingDirectory(filedir);// 进入文件夹
//                }
//            }
//            String newName = new String(newFileName.getBytes("iso-8859-1"), "UTF-8");
//            FTPFile[] files = ftpClient.listFiles();
//            if (files.length == 1) {
//            }
//            log.info(filedir);
//            buffIn = new BufferedInputStream(inputStream);
//            boolean bool = ftpClient.storeFile(newName, buffIn);
//            System.out.println(bool);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (buffIn != null) {
//                    buffIn.close();
//                }
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 下载链接配置
//     *
//     * @param localBaseDir  本地目录
//     * @param remoteBaseDir 远程目录
//     */
//    public void startDown(String localBaseDir, String remoteBaseDir) throws Exception {
//        try {
//            FTPFile[] files = null;
////            ftpClient.setControlEncoding("utf-8");
////            remoteBaseDir = new String(remoteBaseDir.getBytes("UTF-8"), "iso-8859-1");
//            System.out.println("remoteBaseDir---->" + remoteBaseDir);
//            boolean changedir = ftpClient.changeWorkingDirectory(remoteBaseDir);
//            if (changedir) {
//                files = ftpClient.listFiles(remoteBaseDir);
//                for (int i = 0; i < files.length; i++) {
//                    try {
//                        downloadFile(files[i], localBaseDir, remoteBaseDir);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                        System.out.println("<" + files[i].getName() + ">下载失败");
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("下载过程中出现异常");
//        }
//    }
//
//    /**
//     * 下载单个文件
//     *
//     * @param remotePath  远程路径
//     * @param ftpFileName 远程路径下的文件名
//     * @param localPath   本地路径
//     */
//    public void downloadFile(String remotePath, String ftpFileName, String localPath) throws Exception {
//        boolean changedir = ftpClient.changeWorkingDirectory(remotePath);
//        if (changedir) {
//            localPath = localPath.endsWith("/") ? localPath : localPath + "/";
//            File localFile = new File(localPath + ftpFileName);
//            OutputStream outputStream = null;
//            try {
//                outputStream = new FileOutputStream(localPath + ftpFileName);
//                ftpClient.retrieveFile(ftpFileName, outputStream);
//                outputStream.flush();
//                outputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                try {
//                    if (outputStream != null) {
//                        outputStream.close();
//                    }
//                } catch (IOException e) {
//                    System.out.println("输出文件流异常");
//                }
//            }
//        }
//    }
//
//    /**
//     * 下载FTP文件 当你需要下载FTP文件的时候，调用此方法 根据<b>获取的文件名，本地地址，远程地址</b>进行下载
//     *
//     * @param ftpFile
//     * @param relativeLocalPath
//     * @param relativeRemotePath
//     */
//    private void downloadFile(FTPFile ftpFile, String relativeLocalPath, String relativeRemotePath) throws Exception {
//        System.out.println("relativeLocalPath---->" + relativeLocalPath);
//        String ftpFileName = ftpFile.getName();
////        ftpFileName = new String(ftpFile.getName().getBytes("UTF-8"), "iso-8859-1");
//        System.out.println("ftpFileName--->" + ftpFileName);
//        if (ftpFile.isFile()) {
//            if (ftpFile.getName().indexOf("?") == -1) {
//                OutputStream outputStream = null;
//                try {
//                    File locaFile = new File(relativeLocalPath + ftpFileName);
//                    //判断文件是否存在，存在则返回
////                    if(locaFile.exists()){
////                        return;
////                    }else{
//                    outputStream = new FileOutputStream(relativeLocalPath + ftpFileName);
//                    ftpClient.retrieveFile(ftpFileName, outputStream);
//                    outputStream.flush();
//                    outputStream.close();
////                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    try {
//                        if (outputStream != null) {
//                            outputStream.close();
//                        }
//                    } catch (IOException e) {
//                        System.out.println("输出文件流异常");
//                    }
//                }
//            }
//        } else {
//            String newlocalRelatePath = relativeLocalPath + ftpFileName;
//            String newRemote = new String(relativeRemotePath + ftpFileName.toString());
//            File fl = new File(newlocalRelatePath);
//            if (!fl.exists()) {
//                fl.mkdirs();
//            }
//            newlocalRelatePath = newlocalRelatePath + '/';
//            newRemote = newRemote + "/";
//            String currentWorkDir = ftpFile.getName().toString();
//            boolean changedir = ftpClient.changeWorkingDirectory(currentWorkDir);
//            if (changedir) {
//                FTPFile[] files = null;
//                files = ftpClient.listFiles();
//                for (int i = 0; i < files.length; i++) {
//                    downloadFile(files[i], newlocalRelatePath, newRemote);
//                }
//            }
//            if (changedir) {
//                ftpClient.changeToParentDirectory();
//            }
//        }
//    }
//
//    /**
//     * 获取远程目录下的文件列表，不包括文件夹
//     *
//     * @param remoteBaseDir
//     * @return
//     */
//    public List<String> getFtpFileList(String remoteBaseDir) {
//        FTPFile[] files = null;
//        List<String> fileList = new ArrayList<String>();
//        try {
//            System.out.println("remoteBaseDir---->" + remoteBaseDir);
//            boolean changedir = ftpClient.changeWorkingDirectory(remoteBaseDir);
//            if (changedir) {
//                files = ftpClient.listFiles(remoteBaseDir);
//                for (FTPFile file : files) {
//                    try {
//                        if (file.isFile()) {
//                            fileList.add(file.getName());
//                        }
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return fileList;
//    }
//
//    /**
//     * 删除文件
//     */
//    public void deleteFile(String filename) {
//        try {
//            ftpClient.deleteFile(filename);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//    }
//
//    /**
//     * 关闭连接
//     */
//    public void closeConnect() {
//        try {
//            if (ftpClient != null) {
//                ftpClient.logout();
//                ftpClient.disconnect();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public String[] getFILE_TYPES() {
//        return FILE_TYPES;
//    }
//
//    public FTPClient getFtpClient() {
//        return ftpClient;
//    }
//
//    public void setFtpClient(FTPClient ftpClient) {
//        this.ftpClient = ftpClient;
//    }
//
//    /**
//     * 改变目录<br>
//     *
//     * @param path
//     * @return
//     */
//    public boolean changeWorkingDirectory(String path) {
//        boolean bool = false;
//        try {
//            bool = ftpClient.changeWorkingDirectory(path);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return bool;
//    }
//
//    public static void main(String[] args) throws Exception {
//        FtpEngine ftpEngine = new FtpEngine();
//        ftpEngine.connectServer("viong", "123456", "172.17.30.252", 21);
//        File file = new File("D:/java.txt");
//        InputStream in = new FileInputStream(file);
//        String remotePath = "/home/viong/loan/课栈/2017-02-11/厦门魅兰莎贸易有限公司_20170210/回单";
////        ftpEngine.startDown("G:/upload/ftp/",remotePath);
////        ftpEngine.downloadFile(remotePath,"杭州岩倍教育咨询有限公司_20170105.zip","G:/upload/ftp/");
//        ftpEngine.uploadFile(in, "789.jsp", remotePath);
//        ftpEngine.closeConnect();
//    }
//
//    public List<Map<String, String>> getFileList(String basePath, List<Map<String, String>> fileList,
//                                                 String parentFileName) throws Exception {
//        FTPFile[] files = null;
//        basePath = basePath + parentFileName;
//        boolean bool = changeWorkingDirectory(basePath);
//        if (bool) {
//            files = ftpClient.listFiles(basePath);
//            for (FTPFile file : files) {
//                try {
//                    if (file.isFile()) {
//                        Map<String, String> temp = new HashMap<String, String>();
//                        temp.put("id", file.getName());
//                        temp.put("name", file.getName());
//                        temp.put("parentId", parentFileName);
//                        fileList.add(temp);
//                    } else {
//                        getFileList(basePath + "/", fileList, file.getName());
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return fileList;
//    }
//
//    /**
//     * 递归创建远程服务器目录
//     *
//     * @param remote    远程服务器文件绝对路径
//     * @param ftpClient FTPClient 对象
//     * @return 目录创建是否成功
//     */
//    public boolean CreateDirecroty(String remote, FTPClient ftpClient)
//            throws IOException {
//        return ftpClient.makeDirectory(remote);
////        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
////        if (!directory.equalsIgnoreCase("/")
////                && !ftpClient.changeWorkingDirectory(new String(directory
////                .getBytes("UTF-8"), "iso-8859-1"))) {
////            // 如果远程目录不存在，则递归创建远程服务器目录
////            int start = 0;
////            int end = 0;
////            if (directory.startsWith("/")) {
////                start = 1;
////            } else {
////                start = 0;
////            }
////            end = directory.indexOf("/", start);
////            String subDirectory = "";
////            while (true) {
////                //subDirectory = subDirectory + "/" + new String(remote.substring(start, end)
////                //        .getBytes("UTF-8"), "iso-8859-1");
////                subDirectory = new String(remote.substring(start, end)
////                        .getBytes("UTF-8"), "iso-8859-1");
//////                System.out.println(subDirectory + "---->" + ftpClient.changeWorkingDirectory(subDirectory));
////                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
////                    if (ftpClient.makeDirectory(subDirectory)) {
////                        ftpClient.changeWorkingDirectory(subDirectory);
////                    } else {
////                        log.error("创建目录失败");
////                        return false;
////                    }
////                }
////
////                start = end + 1;
////                end = directory.indexOf("/", start);
////
////                // 检查所有目录是否创建完毕
////                if (end <= start) {
////                    break;
////                }
////            }
////        }
////        return true;
//    }
//
//
//    /**
//     * 根据指定文件路径返回文件输入流
//     *
//     * @param ftpClient
//     * @param filePath
//     * @return
//     */
//    public InputStream getFileInputStream(FTPClient ftpClient, String filePath) throws IOException {
//        InputStream bis = ftpClient.retrieveFileStream(filePath);
//        return bis;
//    }
//
//
//    /**
//     * 上传文件 文件名不需要转码直接utf-8
//     *
//     * @param inputStream
//     * @param newName
//     * @param filedir
//     */
//    public void uploadFileUtf8(InputStream inputStream, String newName, String filedir) {
//        // 上传文件
//        BufferedInputStream buffIn = null;
//        try {
//            boolean changeSucess = changeWorkingDirectory(filedir);// 进入文件夹
//            if (!changeSucess) {
//                if (CreateDirecroty(filedir, ftpClient)) {
//                    changeSucess = changeWorkingDirectory(filedir);// 进入文件夹
//                }
//            }
//            //String newName = new String(newFileName.getBytes("iso-8859-1"), "UTF-8");
//            FTPFile[] files = ftpClient.listFiles();
//            if (files.length == 1) {
//            }
//            log.info(filedir);
//            buffIn = new BufferedInputStream(inputStream);
//            boolean bool = ftpClient.storeFile(newName, buffIn);
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (buffIn != null) {
//                    buffIn.close();
//                }
//                inputStream.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }
//
//    /**
//     * 递归创建远程服务器目录
//     *
//     * @param remote    远程服务器文件绝对路径
//     * @param ftpClient FTPClient 对象
//     * @return 目录创建是否成功
//     */
//    public boolean CreateDirecrotyOld(String remote, FTPClient ftpClient)
//            throws IOException {
//        String directory = remote.substring(0, remote.lastIndexOf("/") + 1);
//        if (!"/".equalsIgnoreCase(directory)
//                && !ftpClient.changeWorkingDirectory(new String(directory
//                .getBytes("UTF-8"), "iso-8859-1"))) {
//            // 如果远程目录不存在，则递归创建远程服务器目录
//            int start = 0;
//            int end = 0;
//            if (directory.startsWith("/")) {
//                start = 1;
//            } else {
//                start = 0;
//            }
//            end = directory.indexOf("/", start);
//            String subDirectory = "";
//            while (true) {
//                subDirectory = new String(remote.substring(start, end)
//                        .getBytes("UTF-8"), "iso-8859-1");
//                if (!ftpClient.changeWorkingDirectory(subDirectory)) {
//                    if (ftpClient.makeDirectory(subDirectory)) {
//                        ftpClient.changeWorkingDirectory(subDirectory);
//                    } else {
//                        log.error("创建目录失败");
//                        return false;
//                    }
//                }
//                start = end + 1;
//                end = directory.indexOf("/", start);
//
//                // 检查所有目录是否创建完毕
//                if (end <= start) {
//                    break;
//                }
//            }
//        }
//        return true;
//    }
//}