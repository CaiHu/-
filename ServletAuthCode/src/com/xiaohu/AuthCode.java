package com.xiaohu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * ������֤��ͼƬ
 * @author �ֱ���Evankaka�����ͣ�http://blog.csdn.net/evankaka��
 * @since 2015.6.22
 */
public class AuthCode {
	public static final int AUTHCODE_LENGTH = 5; // ��֤�볤��
	public static final int SINGLECODE_WIDTH = 25; // ������֤����
	public static final int SINGLECODE_HEIGHT = 30; // ������֤��߶�
	public static final int SINGLECODE_GAP = 4; // ������֤��֮����
	public static final int IMG_WIDTH = AUTHCODE_LENGTH * (SINGLECODE_WIDTH + SINGLECODE_GAP);
	public static final int IMG_HEIGHT = SINGLECODE_HEIGHT+10;
	public static final char[] CHARS = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9' };
	public static final char[] OPERATION={'+','-','*'};
	
	static Random random = new Random();
	
    /**
     * ����ͼƬ�е�����
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
     * ���ؼ���Ľ��
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
     * ���ط��Ŷ�Ӧ������
     * @param operation
     * @return String
     */
	public static String getOperation(char operation){
		if('+'==operation)
			return "����";
		else if ('-'==operation)
		    return "��ȥ";
		else if ('*'==operation)
		    return "����";
		else
			return null;
	}
	
	 /**
     * ���ش����ֵ�ͼƬ
     * @return BufferedImage
     */
	public static BufferedImage getAuthImg(String authCode) {
		// ����ͼƬ�ĸߡ�������
		// RGB���룺red��green��blue
		BufferedImage img = new BufferedImage(IMG_WIDTH, IMG_HEIGHT,
				BufferedImage.TYPE_INT_BGR);
		// �õ�ͼƬ�ϵ�һ������
		Graphics g = img.getGraphics();
		// ���û��ʵ���ɫ������������ɫ
		g.setColor(Color.YELLOW);
		// �û��������һ�����Σ����ε����Ͻ����꣬����
		g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
		// ��������ɫ����Ϊ��ɫ������д��
		g.setColor(Color.BLACK);
		// �������壺���塢������ʽ�ġ��ֺ�
		g.setFont(new Font("����", Font.PLAIN, SINGLECODE_HEIGHT + 3));
		// �������
		char c;
		for (int i = 0; i < authCode.toCharArray().length; i++) {
			// ȡ����Ӧλ�õ��ַ�
			c = authCode.charAt(i);
			// ����һ���ַ�����Ҫ�������ݣ���ʼ��λ�ã��߶�
			g.drawString(c + "", i * (SINGLECODE_WIDTH + SINGLECODE_GAP)
					+ SINGLECODE_GAP / 2+8, IMG_HEIGHT-5);
		}
		Random random = new Random();
		// ������
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