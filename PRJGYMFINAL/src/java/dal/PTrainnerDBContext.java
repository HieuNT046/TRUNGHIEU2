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
import model.Certificate;
import model.PTranier;

/**
 *
 * @author HL2020
 */
public class PTrainnerDBContext extends DBContext{
    public ArrayList<PTranier> getPTs(){
       ArrayList<PTranier> PTs = new ArrayList<>(); 
       try {
            String sql = "select [pid], [pname]\n" +
                                "  from [PT]        ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next())
            {
                PTranier c = new PTranier();
                c.setId(rs.getInt("pid"));
                c.setName(rs.getString("pname"));
                PTs.add(c);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PTrainnerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return PTs;
    }
     public void UpdateAll(String[] update){
        try {
            //delete
            connection.setAutoCommit(false);
            String sql_remove_certs = "DELETE Member_PT";
            PreparedStatement stm_remove_cert = connection.prepareStatement(sql_remove_certs);
            stm_remove_cert.executeUpdate();
        //Insert into Student_Certificate
            for (String string : update) {
                String[] oid = string.split("_");
                String sql_insert_cert = "INSERT INTO [Member_PT]\n" +
                    "           ([mid]\n" +
                    "           ,[pid])\n" +
                    "     VALUES\n" +
                    "           (?\n" +
                    "           ,?)";
                PreparedStatement stm_insert_cert = connection.prepareStatement(sql_insert_cert);
                stm_insert_cert.setInt(1, Integer.parseInt(oid[0]));
                stm_insert_cert.setInt(2, Integer.parseInt(oid[1]));
                stm_insert_cert.executeUpdate();
            }
            
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(PTrainnerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PTrainnerDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally{
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(PTrainnerDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
