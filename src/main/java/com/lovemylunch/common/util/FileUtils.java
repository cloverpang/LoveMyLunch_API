package com.lovemylunch.common.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.NumberFormat;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * 文件工具类
 * @author 李俊
 */
public class FileUtils {
    private static final Log log = LogFactory.getLog(FileUtils.class);

    private static final String [] fileFilter={"JPG","JPEG","GIF","PNG","JPE","MP3","WMA","WAV","OGG","MP4","M4V","3GP","AVI","MPEG","WMV","MOV","WEBM",
            "3MV","XLS","XLSX","PPT","PPTX","ZIP","SVG","PDF"};
    private static final String[] pathBlackList = {"~/","../","./","%00","%0","%20"};
    private static final String SEPARATOR = File.separator;


    /**
     * 文件是否符合要求
     * @param filter
     * @return boolean
     */
    public static boolean isLegitimate(String fileName,String[] filter){
        if(fileName.equals(null))
            return false;
        boolean flag = false;
        String ext = FileUtils.getExtName(fileName, false).toUpperCase();
        for(String temp:filter){
            if(temp.equals(ext))
                flag = true;
        }
        return flag;
    }

    /**
     * 将指定文件转换成字节数组
     * @param filePath 文件路径
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            log.info("-----------> 开始读取文件" + filePath);
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            log.info("-----------> 读取文件成功");
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("-----------> 文件路径不存在", e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("-----------> IO流错误", e);
        } finally {
            try {
                if (null != fis) {
                    fis.close();
                }
                if (null != bos) {
                    bos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buffer;
    }

    /**
     * 将字节数组保存到指定目录
     * @param folder 文件存放目录
     * @param fileName 保存的文件名
     * @param fileByteArry 待保存的字节数组
     * @param isCheck 是否需要验证文件后缀名
     * @return
     */
    public static boolean saveIOSItem(String folder, String fileName, byte[] fileByteArry,boolean isCheck) {
        folder = folder.replace(URLDecoder.decode("%00"),"");//安全过滤
        fileName = fileName.replace(URLDecoder.decode("%00"),"");//安全过滤
        if(!isLegitimate(fileName, FileUtils.fileFilter) && isCheck){//判断文件是否符合要求
            return false;
        }
        makeDir(folder);
        String srcPath = folder + "/" + fileName;
        FileOutputStream out = null;
        try {
            if (fileByteArry != null) {
                File savedFile = new File(srcPath);
                out  = new FileOutputStream(savedFile);
                out.write(fileByteArry);
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }finally{
            if(null != out){
                try {
                    out.flush();
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 创建文件目录
     * @param dirPath
     */
    public static void makeDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * 删除指定目录及其子目录（文件）
     * @param file
     */
    private static void deleteFile(File file) {
        if(file.isDirectory()) {
            File[] fileList = file.listFiles();
            for(File f : fileList) {
                deleteFile(f);
            }
        }

        boolean flag = file.delete();
        log.info("[delete]删除:" + file.getAbsolutePath() + "," + flag);
    }

    /**
     * 是否为图片
     * @param fileName
     * @return
     */
    public static boolean isImage(String fileName) {
        if(fileName != null){
            String[] exts = {".jpg",".gif",".png",".jpeg"};
            fileName = fileName.toLowerCase();
            for(String ext : exts) {
                if(fileName.endsWith(ext)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 复制单个文件
     * @param src 源文件
     * @param des 目标文件
     * @return
     */
    public static boolean fileCopy(String src, String des, String[] except){
        boolean flag = true;
        src.replace(URLDecoder.decode("%00"),"");
        des.replace(URLDecoder.decode("%00"),"");
        if(!pathFilter(src, FileUtils.pathBlackList)||!pathFilter(des,FileUtils.pathBlackList)){
            String fileType = FileUtils.getPrefixName(src);
            String fileType2 = FileUtils.getPrefixName(des);
            if (!fileType.equals(fileType2)){
                log.error("文件复制异常：src(" + src + "),des(" + des + ")",new Exception("禁止修改文件类型"));
            }else{
                log.error("文件复制异常：src(" + src + "),des(" + des + ")",new Exception("非法路径"));
            }
            return false;
        }
        if(except!= null && except.length > 0){
            for(String str:except){
                if(src.toLowerCase().indexOf(str.toLowerCase())!= -1){
                    flag = false;
                }
            }
        }
        File srcFile = new File(src);
        if(srcFile.exists() && flag) {
            File desFile=new File(des);
            byte[] b = new byte[1024];
            if (!desFile.getParentFile().exists()) {
                desFile.getParentFile().mkdirs();
            }
            try {
                FileInputStream fis=new FileInputStream(srcFile);
                FileOutputStream fos=new FileOutputStream(desFile,false);
                FileOutputStream bakFos = null;
                while(true){
                    int i = fis.read(b);
                    if(i == -1)break;
                    fos.write(b,0,i);
                    if(bakFos!=null) bakFos.write(b,0,i);
                }
                fos.close();
                fis.close();
                if(bakFos != null) bakFos.close();
                return true;
            } catch (Exception e){
                log.error("文件复制异常：src("+src+"),des("+des+")", e);
            }
        }
        return false;
    }

    public static boolean fileCopy(String src, String des){
        if(!pathFilter(src,FileUtils.pathBlackList) || !pathFilter(des, FileUtils.pathBlackList)){
            log.error("文件复制异常：src(" + src + "),des(" + des + ")",new Exception("非法路径"));
            return false;
        }
        File srcFile = new File(src);
        if(srcFile.exists()) {
            File desFile = new File(des);
            byte[] b = new byte[1024];
            if (!desFile.getParentFile().exists()) {
                desFile.getParentFile().mkdirs();
            }
            try {
                FileInputStream fis = new FileInputStream(srcFile);
                FileOutputStream fos = new FileOutputStream(desFile,false);
                while(true){
                    int i = fis.read(b);
                    if(i == -1)break;
                    fos.write(b,0,i);
                }
                fos.close();
                fis.close();
                return true;
            } catch (Exception e){
                log.error("文件复制异常：src("+src+"),des("+des+")", e);
            }
        }
        return false;
    }

    /**
     * 复制文件夹及其子文件夹下的文件到指定目录
     * @param src
     * @param des
     * @return
     */
    public static boolean folderCopy(String src, String des){
        File srcFile = new File(src);
        boolean flag = false;
        makeDir(des);
        if(srcFile.exists()){
            File[] files = srcFile.listFiles();
            if(files != null) {
                for(int i = 0;i < files.length;i++){
                    String path = src + "/" + files[i].getName();
                    String destPath = path.replace(src, des);
                    if(files[i].isDirectory()){
                        makeDir(destPath);//不存在新建文件夹
                        folderCopy(path, destPath);
                    }else{
                        flag=fileCopy(path, destPath,null);//文件复制函数
                    }
                }
            }
        }
        return flag;
    }

    public static boolean folderCopy(String src,String des,String[] except){
        File srcFile = new File(src);
        boolean flag = false;
        makeDir(des);
        if(srcFile.exists()){
            File[] files = srcFile.listFiles();
            if(files != null) {
                for(int i = 0;i < files.length;i++){
                    String path = src + "/" + files[i].getName();
                    String destPath = path.replace(src, des);
                    if(files[i].isDirectory()){
                        makeDir(destPath);//不存在新建文件夹
                        folderCopy(path, destPath, except);
                    }else{
                        flag = fileCopy(path, destPath, except);//文件复制函数
                    }
                }
            }
        }
        return flag;
    }

    /**
     * 获取去除后缀的文件名
     * @param name
     * @return
     */
    public static String getPrefixName(String name) {
        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * 返回文件拓展名
     * @param name 文件名
     * @param containDot 是否包含 .
     * @return
     */
    public static String getExtName(String name, boolean containDot) {
        if(containDot) {
            return name.substring(name.lastIndexOf("."));
        }else{
            return name.substring(name.lastIndexOf(".")+1);
        }
    }

    /**
     * 生成唯一标识文件名(不包含拓展名)
     * @return
     */
    public static String uniqueFileName() {
        return StringUtils.getRandomString(2)+System.currentTimeMillis()+(int)(Math.random()*100000);
    }

    /**
     * 重命名文件名
     * @param f
     */
    public static File renameUniqueFile(File f) {
        String absolutePath = f.getAbsolutePath(); //获取文件绝对路径
        int index = absolutePath.lastIndexOf(FileUtils.SEPARATOR);
        String prefixPath = absolutePath.substring(0, index+1); //获取路径前缀(剔除文件名)
        String ext = FileUtils.getExtName(absolutePath, true); //获取文件名后缀
        String newPath = prefixPath + FileUtils.uniqueFileName()+ext; //新文件绝对路径
        File file = new File(newPath);
        if(f.renameTo(file)){//重命名
            return file;
        }else{
            return f;
        }
    }

    /**
     * 检查文件路径是否合法
     * @param path
     * @param blackList
     * @return
     */
    public static boolean pathFilter(String path ,String[] blackList){
        boolean isLegitimate = true;
        if (!StringUtils.isEmpty(path)){
            if (null!= blackList && blackList.length>0){
                for(String blackPath:blackList){
                    if (path.indexOf(blackPath) >= 0) {
                        isLegitimate = false ;break;
                    }
                }
            }
        }
        return isLegitimate;
    }

    /**
     * 文件大小转换成String
     * @Title: FileUtils
     * @Description:
     * @param  size 文件大小
     * @param  digits 保留几位有效数字
     * @param @return
     * @return
     * @throws
     */
    public static String getStringSize(long size, int digits) {
        NumberFormat nt = NumberFormat.getNumberInstance();
        // 设置百分数精确度2即保留两位小数
        nt.setMinimumFractionDigits(digits);
        nt.setMaximumFractionDigits(digits);
        // 最后格式化并输出
        if (size < 1024) {// B
            return size + "B";
        } else if (1024 * 1024 > size && size >= 1024) {// KB
            return nt.format(size / (1024D)) + "K";
        } else if (1024 * 1024 * 1024 > size && size >= 1024 * 1024) {// M
            return nt.format(size / (1024D * 1024D)) + "M";
        } else if (1024 * 1024 * 1024 * 1024 > size
                && size >= 1024 * 1024 * 1024) {// G
            return nt.format(size / (1024D * 1024D * 1024D)) + "G";
        } else if (1024 * 1024 * 1024 * 1024 > size
                && size >= 1024 * 1024 * 1024) {// T
            return nt.format(size / (1024D * 1024D * 1024D * 1024)) + "T";
        }
        return null;
    }
}