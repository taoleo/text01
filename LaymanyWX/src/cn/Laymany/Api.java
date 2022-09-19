package cn.Laymany;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Api")
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public Api() {
        super();
            }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//response.getWriter().append("<html><head><meta charset='utf-8'></head><body>我来了！</body></html>");
		
		String echostr,nonce,timestamp,signature;
		signature=request.getParameter("signature");
		echostr=request.getParameter("echostr");
		nonce=request.getParameter("nonce");
		timestamp=request.getParameter("timestamp");
		
		System.out.println(timestamp);
		System.out.println(nonce);
		System.out.println(echostr);
		System.out.println(signature);
		
		if (timestamp == null || nonce == null || echostr == null || signature == null){
			System.out.println("非法访问");
			out.print("<html><head><meta charset='utf-8'></head><body>网络不是法外之地</body></html>");
			return ;
		}
			
		
//		1）将token、timestamp、nonce三个参数进行字典序排序
		
		String token = "Laymany";
		
		ArrayList<String> list = new ArrayList<String>();
		list.add(token);list.add(timestamp);list.add(nonce);
		
		Collections.sort(list);
		String newstr = list.get(0)+list.get(1)+list.get(2);
		
//		2）将三个参数字符串拼接成一个字符串进行sha1加密
		
		newstr = SHA1.encode(newstr);
		
//		3）开发者获得加密后的字符串可与 signature 对比，标识该请求来源于微信
		
		if(newstr.equals(signature)){
			out.print(echostr);
		}else{
			System.out.println("疑似非法访问！");
		}
		
		out.flush();
		out.close();
		//System.out.println("有人来了，快躲起来！");
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
