package com.example.vote.util;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Author: Jerry
 * Time: 3. 15
 * Detail: 文件上传、下载工具类
 */
public class FileUtil {

    //文件上传
    public Boolean UploadFileFromBrowser(){


        return true;
    }

    //文件下载
    public Boolean DownloadFileFromServer(String url, String fileName, HttpServletResponse response){
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=" + fileName);

        FileInputStream fis = null;
        try{
            fis = new FileInputStream(new File(url));
            byte[] buffer = new byte[1024];
            int len=0;
            while ((len = fis.read(buffer)) >0){
                response.getOutputStream().write(buffer, 0, len);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                response.getOutputStream().flush();
                response.getOutputStream().close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
