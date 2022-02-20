package cn.bookstore.dbpool;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;

@WebServlet(name = "BaseServlet", value = "/BaseServlet")
public class BaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("text/html;charset=UTF-8");//处理响应编码
            /**
             * 1. 获取method参数，它是用户想调用的方法 2. 把方法名称变成Method类的实例对象 3. 通过invoke()来调用这个方法
             */
            String methodName = request.getParameter("method");
            Method method = null;

            try {                           /*通过方法名称获取Method对象*/
                method = this.getClass().getMethod(methodName,HttpServletRequest.class, HttpServletResponse.class);
            } catch (Exception e) {
                throw new RuntimeException("您要调用的方法：" + methodName + "它不存在！", e);
            }

            try {               /* 通过method对象来调用（invoke）它*/
                String result = (String)method.invoke(this, request, response);
                if(result != null && !result.trim().isEmpty()) {//如果请求处理方法返回不为空
                    int index = result.indexOf(":");//获取第一个冒号的位置
                    if(index == -1) {//如果没有冒号，使用转发
                        request.getRequestDispatcher(result).forward(request, response);
                    } else {//如果存在冒号
                        String start = result.substring(0, index);//分割出前缀
                        String path = result.substring(index + 1);//分割出路径
                        if(start.equals("f")) {         //前缀为f表示转发
                            request.getRequestDispatcher(path).forward(request, response);
                        } else if(start.equals("r")) {  //前缀为r表示重定向
                            response.sendRedirect(request.getContextPath() + path);
                        }
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);        //都转给doget
    }
}
