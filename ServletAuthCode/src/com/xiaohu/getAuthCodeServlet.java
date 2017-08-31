package com.xiaohu;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * �õ�������֤��ͼƬ��servlet
 * @author �ֱ���Evankaka�����ͣ�http://blog.csdn.net/evankaka��
 * @since 2015.6.22
 */
public class getAuthCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 List<String> list = AuthCode.getAuthCode();  
         
	        request.getSession().setAttribute("authCode", list.get(1));    //����֤�뱣�浽session�У������Ժ���֤  
	          
	        try {  
	            //����ͼƬ  
	            ImageIO.write(AuthCode.getAuthImg(list.get(0)), "JPEG", response.getOutputStream());  
	        } catch (IOException e){  
	            e.printStackTrace();  
	        }  
	    
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
