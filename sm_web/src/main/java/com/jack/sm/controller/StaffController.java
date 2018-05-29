package com.jack.sm.controller;

import com.jack.sm.dao.DepartmentDao;
import com.jack.sm.entity.Department;
import com.jack.sm.entity.Staff;
import com.jack.sm.service.DepartmentService;
import com.jack.sm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller("staffController")
public class StaffController {

    @Autowired
    private StaffService staffService;

    @Autowired
    private DepartmentService departmentService;
    // department/list.do  /department_list.jsp
    public void list(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{
        List<Staff> list = staffService.getAll();
       /* System.out.println("list in departmentController test1");
        if(list== null){System.out.println("list==null");}
        for(Department dep :list){
            System.out.println(dep.toString());
        }*/
        request.setAttribute("LIST",list);
        request.getRequestDispatcher("../staff_list.jsp").forward(request,response);
    }
    public void toAdd(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        List<Department> dList = departmentService.getAll();
        request.setAttribute("DLIST",dList);
        request.getRequestDispatcher("../staff_add.jsp").forward(request,response);
    }

    public void add(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info = request.getParameter("info");
        Date bornDate = null;
        Integer did = 1 ;
        try{
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
            did = Integer.parseInt(request.getParameter("did"));
        }catch (Exception e){
            e.printStackTrace();
        }

        Staff staff = new Staff();
        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);
        staff.setBornDate(bornDate);
        staff.setInfo(info);
        staff.setIdNumber(idNumber);
        staff.setDid(did);

        staffService.add(staff);
        response.sendRedirect("list.do");

    }

    public void toEdit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);
        request.setAttribute("OBJ",staff);
        List<Department> dList = departmentService.getAll();
        request.setAttribute("DLIST",dList);
        request.getRequestDispatcher("../staff_edit.jsp").forward(request,response);
    }

    public void edit(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        String account = request.getParameter("account");
        String name = request.getParameter("name");
        String sex = request.getParameter("sex");
        String idNumber = request.getParameter("idNumber");
        String info = request.getParameter("info");
        Date bornDate = null;
        try{
            bornDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("bornDate"));
        }catch (Exception e){
            e.printStackTrace();
        }
        Integer did = Integer.parseInt(request.getParameter("did"));
        Staff staff = staffService.get(id);
        staff.setId(id);
        staff.setAccount(account);
        staff.setName(name);
        staff.setSex(sex);
        staff.setBornDate(bornDate);
        staff.setInfo(info);
        staff.setIdNumber(idNumber);
        staff.setDid(did);
        staffService.edit(staff);
        response.sendRedirect("list.do");

    }
    public void remove(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        staffService.delete(id);
        response.sendRedirect("list.do");
    }

    public void detail(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
        Integer id = Integer.parseInt(request.getParameter("id"));
        Staff staff = staffService.get(id);
        request.setAttribute("OBJ",staff);
        request.getRequestDispatcher("../staff_detail.jsp").forward(request,response);
    }
}
