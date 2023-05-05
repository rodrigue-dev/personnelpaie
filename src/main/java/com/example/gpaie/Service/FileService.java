package com.example.gpaie.Service;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import com.example.gpaie.Utils.StringUtil;

@Service
public class FileService {

    @Value("${files.path}")
    private String filesPath;

    public Resource download(String filename) {
        try {
            Path file = Paths.get(filesPath)
                             .resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
    public String convertImage(String image64){
        String extension;
        String fileName="";
        String[] strings=image64.split(",");
        switch(strings[0]){
            case "data:image/png;base64":
            extension = ".png";
            break;
            case "data:image/jpg;base64":
            extension = ".jpg";
            break;
            default:
            extension=".jpg";
            break;
        }
        byte[]data=DatatypeConverter.parseBase64Binary(strings[1]);
        try {
            //BufferedImage bimage=ImageIO.read(new ByteArrayInputStream(data));
            String separ = System.getProperty("file.separator");
            var path=filesPath+StringUtil.randonKey(10)+extension;
            File file = new File(path);
            FileOutputStream pdfOutputFile = new FileOutputStream(file);
            fileName=path;
            OutputStream outputStream= new BufferedOutputStream(pdfOutputFile);
            outputStream.write(data);
            outputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileName;
    }
    public byte[] convertImageByte(String image64){
        String extension;
        String fileName="";
        String[] strings=image64.split(",");
        switch(strings[0]){
            case "data:image/png;base64":
            extension = ".png";
            break;
            case "data:image/jpg;base64":
            extension = ".jpg";
            break;
            default:
            extension=".jpg";
            break;
        }
        byte[]data=DatatypeConverter.parseBase64Binary(strings[1]);
        
        return data;
    }
}
