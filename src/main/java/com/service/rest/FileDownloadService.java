package com.service.rest;

import java.io.File;
import java.util.List;
import java.util.Properties;

import javax.websocket.server.PathParam;
import javax.ws.rs.GET;  
import javax.ws.rs.Path;  
import javax.ws.rs.Produces;  
import javax.ws.rs.core.Response;  
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.FileUploadDAO;
import com.entity.FileUpload;  
@Path("/files")  
@Service
public class FileDownloadService {  
	
	@Autowired
	FileUploadDAO fileuploadDAO;
	
    private static final String FILE_PATH = "c:\\myimage.png";  
    @GET  
    @Path("/image")  
    @Produces("image/png")  
    public Response getFile() {  
        File file = new File(FILE_PATH);  
        ResponseBuilder response = Response.ok((Object) file);  
        response.header("Content-Disposition","attachment;");  
        return response.build();  
   
    }  
    @GET  
    @Path("/getFileByID/{id}")  
    public Response getFileByID(@PathParam(value = "id")int id){
    	FileUpload file=fileuploadDAO.getFileById(id);
    	ResponseBuilder response = Response.ok((Object) file);  
    	return response.build();
    }
    
    
    @GET  
    @Path("/sendemail")  
    public void sendEmail(){
    List<FileUpload> fileUploadlist=fileuploadDAO.getFileOfLAstOnehour();
    for (FileUpload fileUpload : fileUploadlist) {
    	// Recipient's email ID needs to be mentioned.
        String to = fileUpload.getToEmail();

        // Sender's email ID needs to be mentioned
        String from = fileUpload.getFromEmail();

        // Assuming you are sending email from localhost
        String host = "localhost";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.setProperty("mail.smtp.host", host);

        // Get the default Session object.
        Session session = Session.getDefaultInstance(properties);

        try {
           // Create a default MimeMessage object.
           MimeMessage message = new MimeMessage(session);

           // Set From: header field of the header.
           message.setFrom(new InternetAddress(from));

           // Set To: header field of the header.
           message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));

           // Set Subject: header field
           message.setSubject("This is the Subject Line!");

           // Create the message part 
           BodyPart messageBodyPart = new MimeBodyPart();

           // Fill the message
           messageBodyPart.setText("This is message body");
           
           // Create a multipar message
           Multipart multipart = new MimeMultipart();

           // Set text message part
           multipart.addBodyPart(messageBodyPart);

           // Part two is attachment
           messageBodyPart = new MimeBodyPart();
           String filename = fileUpload.getFilename();
           DataSource source = new FileDataSource(filename);
           messageBodyPart.setDataHandler(new DataHandler(source));
           messageBodyPart.setFileName(filename);
           multipart.addBodyPart(messageBodyPart);

           // Send the complete message parts
           message.setContent(multipart );

           // Send message
           Transport.send(message);
           System.out.println("Sent message successfully....");
        }catch (MessagingException mex) {
           mex.printStackTrace();
        }
        }
	}
    
    
    
 }  
