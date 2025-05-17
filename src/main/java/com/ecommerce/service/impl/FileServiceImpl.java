package com.ecommerce.service.impl;

import com.ecommerce.exceptions.BadApiRequest;
import com.ecommerce.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


//THIS CLASS IS CREATED TO PERFORM FILE OPERATION AND SAVE IMAGE
@Service
public class FileServiceImpl implements FileService {

    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);


    @Override
    public String uploadFile(MultipartFile file, String path) throws IOException {


        //abc.png       //taking these file and save into database
        String originalFilename = file.getOriginalFilename();
        logger.info("Filename : {}", originalFilename );              // here we takking file name
        String  filename= UUID.randomUUID().toString();     // sometimes file name is same in database so we generate random file name to make it unique
        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));     // to take the extension of file example abc.png so we said last index (.) so it cut here and we get etension of the file
        String fileNameWithExtension=filename+extension;        //add generated filename with extension

        String fullPathWithFileName=path+ File.separator + fileNameWithExtension; //here we get full path include folder using path
                //Finally we get a path to add a image into these path

        if (extension.equalsIgnoreCase(".png") || extension.equalsIgnoreCase(".jpg") || extension.equalsIgnoreCase(".jpeg"))          // if file name have only these extension then allowed
        {
            // now we are saving the file in folder we have path also bt when we dont have folder in the path then we are creating folder
            File folder=new File(path);

            if (! folder.exists()){
                //Create the folder
                folder.mkdirs();
            }

            //Upload  file into new created path
            Files.copy(file.getInputStream(), Paths.get(fullPathWithFileName));

            return fileNameWithExtension;   //return file name with extension no matter return with path or not

        }else {
            throw new BadApiRequest("File with these extension not allowed");
        }


    }

    @Override
    public InputStream geResource(String path, String name) throws FileNotFoundException {

        String fullPath=path+File.separator+name;

        InputStream inputStream=new FileInputStream(fullPath);

        return inputStream;
    }
}
