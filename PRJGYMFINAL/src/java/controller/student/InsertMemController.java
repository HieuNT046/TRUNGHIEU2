/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import controller.auth.BaseRequiredAuthController;
import dal.ClassDBContext;
import dal.MemberDBContext;
import dal.PTrainnerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Member;
import model.PTranier;

/**
 *
 * @author HL2020
 */
public class InsertMemController extends BaseRequiredAuthController {

    
    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       ClassDBContext dbclas = new ClassDBContext();
        ArrayList<model.Class> classes = dbclas.getClasses();
        request.setAttribute("classes", classes);
        PTrainnerDBContext dbPT = new PTrainnerDBContext();
        ArrayList<PTranier> PT = dbPT.getPTs();
        request.setAttribute("PT", PT);
        request.getRequestDispatcher("../view/member/insert.jsp").forward(request, response);

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         Member s = new Member();
        s.setId(Integer.parseInt(request.getParameter("id")));
        s.setName(request.getParameter("name"));
        s.setAge(Integer.parseInt(request.getParameter("age")));
        s.setGender(request.getParameter("gender").equals("male"));
        s.setDob(Date.valueOf(request.getParameter("dob")));
        s.setWeight(request.getParameter("weight"));
        s.setSmm(request.getParameter("smm"));
        s.setBodyfat(request.getParameter("bodyfat"));
        model.Class d = new model.Class();
        d.setId(Integer.parseInt(request.getParameter("classid")));
        s.setClas(d);
        String[] pids = request.getParameterValues("pid");
        if(pids!=null)
        {
            for (String cid : pids) {
                PTranier c = new PTranier();
                c.setId(Integer.parseInt(cid));
                s.getPT().add(c);
            }
        }
        
        MemberDBContext db = new MemberDBContext();
        db.insert(s);
        response.sendRedirect("list");
    }

}
