/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import model.Class;

/**
 *
 * @author HL2020
 */
public class ClassDBContext extends DBContext{
    public ArrayList<Class> getClasses()
    {
        ArrayList<Class> classes = new ArrayList<>();
        try {
            String sql = "select classid,cname\n" +
                                        "from Class";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                Class d = new Class();
                d.setId(rs.getInt("classid"));
                d.setName(rs.getString("cname"));
                classes.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ClassDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return classes;
    } 
    
}
