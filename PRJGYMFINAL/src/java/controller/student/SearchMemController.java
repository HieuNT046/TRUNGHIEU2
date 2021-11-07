/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import controller.auth.BaseRequiredAuthController;
import dal.MemberDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Member;
import dal.ClassDBContext;

/**
 *
 * @author HL2020
 */
public class SearchMemController extends BaseRequiredAuthController {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    

    

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String raw_id = request.getParameter("id");
       if(raw_id == null || raw_id.length() ==0)
            raw_id = "-1";
        int id = Integer.parseInt(raw_id);
        MemberDBContext memdb = new MemberDBContext();
        ArrayList<Member> members = memdb.search(id);
        ClassDBContext clasdb = new ClassDBContext();
        ArrayList<model.Class> clas = clasdb.getClasses();
        request.setAttribute("members", members);
        request.setAttribute("clas", clas);
        request.getRequestDispatcher("../view/member/search.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
