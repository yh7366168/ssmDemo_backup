package com.yh.util;

import com.yh.util.exception.YhSimpleException;
import com.yh.util.exception.YhSystemException;
import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.nio.charset.Charset;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * @author yh create on 2019/6/28
 */
@Slf4j
public class ZipUtil {

    public static void main(String[] args) {
        testZipFile();
    }

    public static void testZipFile() {
        String zipDic = "C:\\Users\\Administrator\\Desktop\\同牛\\zip";
        String zipName = "C:\\Users\\Administrator\\Desktop\\同牛\\testZip.zip";
        int count, bufferLen = 1024;
        BufferedInputStream bis = null;
        ZipOutputStream zos = null;
        byte[] bytePool = new byte[bufferLen];

        File file = new File(zipDic);
        if (!file.isDirectory()) {
            throw new YhSimpleException("不是目录！");
        }
        try {
            zos = new ZipOutputStream(new FileOutputStream(new File(zipName)), Charset.forName("UTF-8"));
            File[] sonFileArr = file.listFiles();
            for(int i=0; i<sonFileArr.length; i++){
                File sonFile = sonFileArr[i];
                if (sonFile.isFile()) {
                    ZipEntry entry = new ZipEntry(sonFile.getName());
                    entry.setSize(sonFile.length());
                    entry.setTime(sonFile.lastModified());
                    zos.putNextEntry(entry);
                    bis = new BufferedInputStream(new FileInputStream(sonFile));
                    while ((count = bis.read(bytePool, 0, bufferLen)) != -1) {
                        zos.write(bytePool, 0, count);
                    }
                    bis.close();
                } else {
                    ZipEntry entry = new ZipEntry(sonFile.getName());
                    zos.putNextEntry(entry);
                }
            }
            zos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 压缩单个文件
     *
     * @param fileName 被压缩文件名字
     * @param zipName  压缩后文件名字
     */
    public boolean zipOneFile(String fileName, String zipName) {
        int count, bufferLen = 1024;
        ZipOutputStream zos = null;
        BufferedInputStream bis = null;
        byte[] byteArr = new byte[bufferLen];
        File file = new File(fileName);
        if (file.exists()) {
            throw new YhSimpleException("待压缩文件不存在！");
        }
        boolean checkZipNameFlag = checkZipName(zipName);
        if (!checkZipNameFlag) {
            throw new YhSimpleException("压缩文件的后缀名不正确！");
        }
        File zipParent = new File(new File(zipName).getParent());
        if (!zipParent.exists()) {
            zipParent.mkdirs();
        }
        try {
            /**
             * CRC32算法 或 Adler32算法
             * */
            CheckedOutputStream cos = new CheckedOutputStream(new FileOutputStream(new File(zipName)), new CRC32());
            zos = new ZipOutputStream(cos, Charset.forName("UTF-8"));
            bis = new BufferedInputStream(new FileInputStream(new File(fileName)));
            while ((count = bis.read(byteArr, 0, bufferLen)) != -1) {
                zos.write(byteArr, 0, count);
            }
        } catch (Exception e) {
            throw new YhSystemException("文件压缩失败！" + e);
        } finally {
            close(bis);
            close(zos);
        }
        return false;
    }

    private static void close(Closeable able) {
        try {
            if (able != null) {
                able.close();
            }
        } catch (Exception e) {
            throw new YhSystemException("关闭资源失败！");
        }
    }

    /**
     * 支持zip.tar,rar
     */
    private static boolean checkZipName(String zipName) {
        if (zipName.endsWith(".zip")) {
            return true;
        } else if (zipName.endsWith(".tar")) {
            return true;
        } else if (zipName.endsWith(".rar")) {
            return true;
        } else {
            return false;
        }
    }

}
