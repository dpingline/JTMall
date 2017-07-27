package com.mall.manage.controller;

import com.mall.common.vo.PicUploadResult;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Ccaveman
 * @Description: 图片上传类controller
 * @Date: Created in 20:30 2017/7/26
 */
@Controller
public class FileUploadController {

    private static final Logger logger = Logger.getLogger(FileUploadController.class);

    /**
     * 图片上传步骤
     * 1.采用问文件正确的接收方式接收(修改3处 配置文件/接口类型等)
     * 2.判断是否为一个图片文件.jpg .png .gif)
     * 3.判断是不是一个"正经"的图片 判断是否有width和height
     * 4.编辑磁盘目录  D:/jt-upload/images/yyyy/MM/dd/HH/mm/文件名称
     * 5.编辑相对路径url:image.jt.com/images/yyyy/MM/dd/HH/mm/文件名称
     * 6.将文件保存
     * @param uploadFile 上传的文件
     * @return 回显图片的json格式
     */
    @RequestMapping("/pic/upload")
    @ResponseBody
    public PicUploadResult picUpload(MultipartFile uploadFile){
        PicUploadResult uploadResult = new PicUploadResult();
        //获取文件名
        String fileName = uploadFile.getOriginalFilename();
        //获取文件后缀
        String endName = fileName.substring(fileName.lastIndexOf("."));
        //判断是否为图片格式
        if (!endName.matches("^.*(jpg|png|gif)$")){
            logger.error("~~~~~~~~~~文件不符合格式");
            uploadResult.setError(1);
            return uploadResult;
        }
        //判断文件是图片是否正确
        try {
            BufferedImage bufferedImage = ImageIO.read(uploadFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            //若获取时有问题则会报错
            uploadResult.setHeight(height+"");
            uploadResult.setWidth(width+"");

            String localPath = "D:/jt-upload/images/";
            String datePath = new SimpleDateFormat("yyyy/MM/dd/HH/mm").format(new Date());
            String urlPath = "http://image.mall.com/images/";
            localPath += datePath+"/"+fileName;
            urlPath +=datePath+"/"+fileName;

            //判断文件夹是否存在
            File file = new File(localPath);
            if(!file.exists()){
                file.mkdirs();//创建一个文件夹
            }

            //将文件写入
            uploadFile.transferTo(file);
            uploadResult.setUrl(urlPath);
            logger.info("~~~~~~~~文件写入成功"+localPath);
        } catch (IOException e) {
            e.printStackTrace();
            logger.error("~~~~~~~~~该文件是一个非法文件");
            uploadResult.setError(1);
            return uploadResult;
        }

        return uploadResult;
    }

}
