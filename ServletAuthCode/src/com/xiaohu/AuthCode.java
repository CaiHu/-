package com.xiaohu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 生成验证码图片
 * @author 林炳文Evankaka（博客：http://blog.csdn.net/evankaka）
 * @since 2015.6.22
 */
public class AuthCode {
	public static final int AUTHCODE_LENGTH = 5; // 验证码长度
	public static final int SINGLECODE_WIDTH = 25; // 单个验证码宽度
	public static final int SINGLECODE_HEIGHT = 30; // 单个验证码高度
	public static final int SINGLECODE_GAP = 4; // 单个验证码之间间隔
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP);
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT+10;
	public static final char[] CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static final char[] OPERATION={'+','-','*'};
	
	static Random random = new Random();
	
    /**
     * 返回图片中的数字
     * @return String
     */
	public static List<String> getAuthCode() {

		char char1 = CHARS[random.nextInt(CHARS.length)];
		char char2 = CHARS[random.nextInt(CHARS.length)];
		char opt = OPERATION[random.nextInt(OPERATION.length)];
	    
		StringBuffer buffer = new StringBuffer();
		buffer.append(char1);
		buffer.append(getOperation(opt));
		buffer.append(char2);
		
		String result=getResult(char1,char2,opt);
		List<String> list=new ArrayList<String>();	
		list.add(buffer.toString());
		list.add(result);
		return list;
	}
	
	  /**
     * 返回计算的结果
     * @param operation
     * @return String
     */
	public static String getResult(char char1,char char2,char operation){	
		int int1 = Integer.parseInt(String.valueOf(char1));
		int int2 = Integer.parseInt(String.valueOf(char2));
		if('+'==operation)
			return String.valueOf(int1+int2);
		else if ('-'==operation)
		    return String.valueOf(int1-int2);
		else if ('*'==operation)
		    return String.valueOf(int1*int2);
		else
		return null;
	}
	
    /**
     * 返回符号对应的中文
     * @param operation
     * @return String
     */
	public static String getOperation(char operation){
		if('+'==operation)
			return "加上";
		else if ('-'==operation)
		    return "减去";
		else if ('*'==operation)
		    return "乘以";
		else
			return null;
	}
	
	 /**
     * 返回带数字的图片
     * @return BufferedImage
     */
	public static BufferedImage getAuthImg(String authCode) {
		// 设置图片的高、宽、类型
		// RGB编码：red、green、blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				BufferedImage.TYPE_INT_BGR);
		// 得到图片上的一个画笔
		Graphics g = img.getGraphics();
		// 设置画笔的颜色，用来做背景色
		g.setColor(Color.YELLOW);
		// 用画笔来填充一个矩形，矩形的左上角坐标，宽，高
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		// 将画笔颜色设置为黑色，用来写字
		g.setColor(Color.BLACK);
		// 设置字体：宋体、不带格式的、字号
		g.setFont(new Font("宋体", Font.PLAIN, SINGLECODE_HEIGHT + 3));
		// 输出数字
		char c;
		for (int i = 0; i < authCode.toCharArray().length; i++) {
			// 取到对应位置的字符
			c = authCode.charAt(i);
			// 画出一个字符串：要画的内容，开始的位置，高度
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP)
					+ SINGLECODE_GAP / 2+8, IMG_HEIGHT-5);
		}
		Random random = new Random();
		// 干扰素
		for (int i = 0; i < 5; i++) {
			int x = random.nextInt(IMG_WIDTH);
			int y = random.nextInt(IMG_HEIGHT);
			int x2 = random.nextInt(IMG_WIDTH);
			int y2 = random.nextInt(IMG_HEIGHT);
			g.drawLine(x, y, x + x2, y + y2);
		}
		return img;
	}
}