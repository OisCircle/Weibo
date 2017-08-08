package com.weibo.functionService;

import java.io.File;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.weibo.dao.Dao;
import com.weibo.model.User;

@Component("userService")
public class UserService {
	Dao dao;
	public Dao getDao() {
		return dao;
	}
	@Resource(name="dao")
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	public ModelAndView addMessagesIntoModel(ModelAndView mv,User u,String guest,String password){
		mv.addObject("user", u);
		mv.addObject("password", password);
		mv.addObject("weibo", dao.getWeibo());
		mv.addObject("guest", guest);
		return mv;
	}
	
	public void userImgUpload(String username,@RequestParam("file") CommonsMultipartFile file){
			   String fileName = file.getOriginalFilename();
			   String fileType = fileName.substring(fileName.lastIndexOf("."));
			   System.out.println(fileType); 
			   File file2 = new File("c:\\Users\\HASEE\\workspace2\\weibo\\WebContent\\userImg\\",username + fileType); //�½�һ���ļ�
			   try {
				    file.getFileItem().write(file2); //���ϴ����ļ�д���½����ļ���
			   } catch (Exception e) {
				    e.printStackTrace();
			   }
			   
			   System.out.println(username+".jpg have been added");
	}
}
