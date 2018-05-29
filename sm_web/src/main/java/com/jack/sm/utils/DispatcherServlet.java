package com.jack.sm.utils;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public class DispatcherServlet extends GenericServlet {

    private ApplicationContext context;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        context = new ClassPathXmlApplicationContext("spring.xml");
    }

    /*有两种格式 /xxx/.do*   /login   */
    @Override
    public void service(ServletRequest servletRequest, ServletResponse servletResponse) throws ServletException, IOException {
        HttpServletRequest req = (HttpServletRequest)servletRequest;
        HttpServletResponse resp = (HttpServletResponse)servletResponse;
        String path = req.getServletPath().substring(1);
        String beanName= null;
        String methodName=null;
        int index=path.indexOf("/");
        if(index!=-1){
            beanName=path.substring(0,index)+"Controller";
            methodName=path.substring(index+1,path.indexOf(".do"));
        }else{
            beanName="selfController";
            methodName=path.substring(0,path.indexOf(".do"));
        }
        System.out.println("beanName:"+beanName);
        Object obj = context.getBean(beanName);
        try {
            Method method = obj.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            ((Method) method).invoke(obj,req,resp);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
