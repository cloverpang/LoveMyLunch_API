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
 * �ļ�������
 * @author �
 */
public class FileUtils {
    private static final Log log = LogFactory.getLog(FileUtils.class);

    private static final String [] fileFilter={"JPG","JPEG","GIF","PNG","JPE","MP3","WMA","WAV","OGG","MP4","M4V","3GP","AVI","MPEG","WMV","MOV","WEBM",
            "3MV","XLS","XLSX","PPT","PPTX","ZIP","SVG","PDF"};
    private static final String[] pathBlackList = {"~/","../","./","%00","%0","%20"};
    private static final String SEPARATOR = File.separator;


    /**
     * �ļ��Ƿ����Ҫ��
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
     * ��ָ���ļ�ת�����ֽ�����
     * @param filePath �ļ�·��
     */
    public static byte[] getBytes(String filePath) {
        byte[] buffer = null;
        FileInputStream fis = null;
        ByteArrayOutputStream bos = null;
        try {
            log.info("-----------> ��ʼ��ȡ�ļ�" + filePath);
            File file = new File(filePath);
            fis = new FileInputStream(file);
            bos = new ByteArrayOutputStream(1024);
            byte[] b = new byte[1024];
            int n;
            while ((n = fis.read(b)) != -1) {
                bos.write(b, 0, n);
            }
            log.info("-----------> ��ȡ�ļ��ɹ�");
            buffer = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            log.error("-----------> �ļ�·��������", e);
        } catch (IOException e) {
            e.printStackTrace();
            log.error("-----------> IO������", e);
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
     * ���ֽ����鱣�浽ָ��Ŀ¼
     * @param folder �ļ����Ŀ¼
     * @param fileName ������ļ���
     * @param fileByteArry ��������ֽ�����
     * @param isCheck �Ƿ���Ҫ��֤�ļ���׺��
     * @return
     */
    public static boolean saveIOSItem(String folder, String fileName, byte[] fileByteArry,boolean isCheck) {
        folder = folder.replace(URLDecoder.decode("%00"),"");//��ȫ����
        fileName = fileName.replace(URLDecoder.decode("%00"),"");//��ȫ����
        if(!isLegitimate(fileName, FileUtils.fileFilter) && isCheck){//�ж��ļ��Ƿ����Ҫ��
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
     * �����ļ�Ŀ¼
     * @param dirPath
     */
    public static void makeDir(String dirPath) {
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }

    /**
     * ɾ��ָ��Ŀ¼������Ŀ¼���ļ���
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
        log.info("[delete]ɾ��:" + file.getAbsolutePath() + "," + flag);
    }

    /**
     * �Ƿ�ΪͼƬ
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
     * ���Ƶ����ļ�
     * @param src Դ�ļ�
     * @param des Ŀ���ļ�
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
                log.error("�ļ������쳣��src(" + src + "),des(" + des + ")",new Exception("��ֹ�޸��ļ�����"));
            }else{
                log.error("�ļ������쳣��src(" + src + "),des(" + des + ")",new Exception("�Ƿ�·��"));
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
                log.error("�ļ������쳣��src("+src+"),des("+des+")", e);
            }
        }
        return false;
    }

    public static boolean fileCopy(String src, String des){
        if(!pathFilter(src,FileUtils.pathBlackList) || !pathFilter(des, FileUtils.pathBlackList)){
            log.error("�ļ������쳣��src(" + src + "),des(" + des + ")",new Exception("�Ƿ�·��"));
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
                log.error("�ļ������쳣��src("+src+"),des("+des+")", e);
            }
        }
        return false;
    }

    /**
     * �����ļ��м������ļ����µ��ļ���ָ��Ŀ¼
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
                        makeDir(destPath);//�������½��ļ���
                        folderCopy(path, destPath);
                    }else{
                        flag=fileCopy(path, destPath,null);//�ļ����ƺ���
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
                        makeDir(destPath);//�������½��ļ���
                        folderCopy(path, destPath, except);
                    }else{
                        flag = fileCopy(path, destPath, except);//�ļ����ƺ���
                    }
                }
            }
        }
        return flag;
    }

    /**
     * ��ȡȥ����׺���ļ���
     * @param name
     * @return
     */
    public static String getPrefixName(String name) {
        return name.substring(0, name.lastIndexOf("."));
    }

    /**
     * �����ļ���չ��
     * @param name �ļ���
     * @param containDot �Ƿ���� .
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
     * ����Ψһ��ʶ�ļ���(��������չ��)
     * @return
     */
    public static String uniqueFileName() {
        return StringUtils.getRandomString(2)+System.currentTimeMillis()+(int)(Math.random()*100000);
    }

    /**
     * �������ļ���
     * @param f
     */
    public static File renameUniqueFile(File f) {
        String absolutePath = f.getAbsolutePath(); //��ȡ�ļ�����·��
        int index = absolutePath.lastIndexOf(FileUtils.SEPARATOR);
        String prefixPath = absolutePath.substring(0, index+1); //��ȡ·��ǰ׺(�޳��ļ���)
        String ext = FileUtils.getExtName(absolutePath, true); //��ȡ�ļ�����׺
        String newPath = prefixPath + FileUtils.uniqueFileName()+ext; //���ļ�����·��
        File file = new File(newPath);
        if(f.renameTo(file)){//������
            return file;
        }else{
            return f;
        }
    }

    /**
     * ����ļ�·���Ƿ�Ϸ�
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
     * �ļ���Сת����String
     * @Title: FileUtils
     * @Description:
     * @param  size �ļ���С
     * @param  digits ������λ��Ч����
     * @param @return
     * @return
     * @throws
     */
    public static String getStringSize(long size, int digits) {
        NumberFormat nt = NumberFormat.getNumberInstance();
        // ���ðٷ�����ȷ��2��������λС��
        nt.setMinimumFractionDigits(digits);
        nt.setMaximumFractionDigits(digits);
        // ����ʽ�������
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