package com.xiaohu;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 得到生成验证码图片的servlet
 * @author 林炳文Evankaka（博客：http://blog.csdn.net/evankaka）
 * @since 2015.6.22
 */
public class getAuthCodeServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		 List<String> list = AuthCode.getAuthCode();  
         
	        request.getSession().setAttribute("authCode", list.get(1));    //将验证码保存到session中，便于以后验证  
	          
	        try {  
	            //发送图片  
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
