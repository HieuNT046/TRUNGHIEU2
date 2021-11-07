/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.student;

import controller.auth.BaseRequiredAuthController;
import dal.MemberDBContext;
import dal.PTrainnerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
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
public class PTranController extends BaseRequiredAuthController {

   
  

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        MemberDBContext db = new MemberDBContext();
        PTrainnerDBContext dbc = new PTrainnerDBContext();
        ArrayList<PTranier> all_pt = dbc.getPTs();
        ArrayList<Member> member = db.getMember();
        request.setAttribute("member", member);
        request.setAttribute("all_pt", all_pt);
        request.getRequestDispatcher("../view/member/PTs.jsp").forward(request, response);    

    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       String[] oid = request.getParameterValues("certid");
       PTrainnerDBContext dbc = new PTrainnerDBContext();
       dbc.UpdateAll(oid);
       response.sendRedirect("pts");
   }
     @Override
    public String getServletInfo() {
        return "Short description";
    }// 

}
