package com.service.rest;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import javax.ws.rs.Consumes;  
import javax.ws.rs.POST;  
import javax.ws.rs.Path;  
import javax.ws.rs.core.MediaType;  
import javax.ws.rs.core.Response;  
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;  
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FileUploadDAO;
import com.entity.FileUpload;  
@Path("/files")  
@Service
public class FileUploadService {  
	
	@Autowired
	FileUploadDAO fileuploadDAO;
    @POST  
    @Path("/upload")  
    @Consumes(MediaType.MULTIPART_FORM_DATA)  
    public Response uploadFile(  
            @FormDataParam("file") InputStream uploadedInputStream,  
            @FormDataParam("file") FormDataContentDisposition fileDetail) {  
            String fileLocation = "e://" + fileDetail.getFileName();  
                    //saving file  
            FileUpload fileOBj=new FileUpload();
            try {  
                FileOutputStream out = new FileOutputStream(new File(fileLocation));  
                
                int read = 0;  
                byte[] bytes = new byte[1024];  
                out = new FileOutputStream(new File(fileLocation));  
                while ((read = uploadedInputStream.read(bytes)) != -1) {  
                    out.write(bytes, 0, read);  
                }  
                out.flush();  
                out.close();  
            } catch (IOException e) {e.printStackTrace();}  
            fileOBj.setFilename(fileLocation);
            fileuploadDAO.saveFile(fileOBj);
            String output = "File successfully uploaded to : " + fileLocation;  
            return Response.status(200).entity(output).build();  
        }  
    
  }  
