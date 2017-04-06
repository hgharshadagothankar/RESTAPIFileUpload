

package com.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entity.FileUpload;


@Repository
public class FileUploadDAO {

	  	@Autowired
	    private SessionFactory sessionFactory;
	 
	    protected Session getSession() {
	        return sessionFactory.getCurrentSession();
	    }
	 
	    public void saveFile(FileUpload entity) {
	        getSession().persist(entity);
	    }
	    public FileUpload getFileById(int fileID){
	    	FileUpload fileup=(FileUpload) getSession().get(FileUpload.class, fileID);
	    	return fileup;
	    }
	 
	    public List<FileUpload> getFileOfLAstOnehour(){
	    	List<FileUpload> filelist = new ArrayList<FileUpload>() ;
			Query query=getSession().createQuery("from FileUpload where createdDate >= (sysdate-1/24)");
			filelist=query.list();
			
			
			return filelist;	
	    }
	
}
