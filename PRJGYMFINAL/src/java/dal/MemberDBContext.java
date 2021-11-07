/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Certificate;

import model.Member;

import model.Class;
import model.PTranier;
import model.Register;
/**
 *
 * @author HL2020
 */
public class MemberDBContext extends DBContext{
    public ArrayList<Member> getMember(){
    ArrayList<Member> member = new ArrayList<>();
     try {
            String sql = "Select s.id,s.name,s.age,s.gender,s.TestDate,s.Weight,\n" +
                        "s.SMM,s.BodyFat,d.classid,d.cname\n" +
                        "from Member s INNER JOIN Class d \n" +
                        " on s.classid=d.classid ";
            PreparedStatement stm = connection.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Member s = new Member();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("TestDate"));
                s.setWeight(rs.getString("Weight"));
                s.setSmm(rs.getString("SMM"));
                s.setBodyfat(rs.getString("BodyFat"));
                Class d = new Class();
                d.setId(rs.getInt("classid"));
                d.setName(rs.getString("cname"));
                s.setClas(d);
                member.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;
    }
public Member getMembers(int id){
    try {
            String sql = "Select s.id,s.name,s.age,s.gender,s.TestDate,s.Weight,\n" +
                                    "s.SMM,s.BodyFat,d.classid,d.cname,c.pid, c.pname as pname\n" +
                                    "from Member s INNER JOIN Class d \n" +
                                    " on s.classid=d.classid \n" +
                                    " left join Member_PT sc\n" +
                                    " on sc.mid = s.id\n" +
                                    " left join PT c \n" +
                                    " on c.pid = sc.pid\n" +
                                    " where s.id = ?" ;
                            
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            ResultSet rs = stm.executeQuery();
            Member s = null;
            while (rs.next()) {
                if (s == null) {
                    s = new Member();
                 s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("TestDate"));
                s.setWeight(rs.getString("Weight"));
                s.setSmm(rs.getString("SMM"));
                s.setBodyfat(rs.getString("BodyFat"));
                Class d = new Class();
                d.setId(rs.getInt("classid"));
                d.setName(rs.getString("cname"));
                s.setClas(d);
                }
                
                int pid = rs.getInt("pid");
                if(pid != 0)
                {
                    PTranier c = new PTranier();
                    c.setId(pid);
                    c.setName(rs.getString("pname"));
                    s.getPT().add(c);
                }
            }
            return s;

        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
 public void update(Member s) {
        try {
            connection.setAutoCommit(false);
            String sql = "Update Member\n" +
                        "Set [name] = ?,\n" +
                        "   [age] = ?,\n" +
                        "   [gender] = ?,\n" +
                        "	[TestDate]=?,\n" +
                        "	[Weight]=?,\n" +
                        "	[SMM] = ?,\n" +
                        "	[BodyFat] = ?,\n" +
                        "	[classid] = ?\n" +
                        "WHERE id =?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getName());
            stm.setInt(2,s.getAge());
            stm.setBoolean(3, s.isGender());
            stm.setDate(4, s.getDob());
            stm.setString(5, s.getWeight());
            stm.setString(6, s.getSmm());
            stm.setString(7, s.getBodyfat());
            stm.setInt(8, s.getClas().getId());
            stm.setInt(9, s.getId());
            stm.executeUpdate();
            
            String sql_remove_pt = "Delete Member_PT where mid = ?";
            PreparedStatement stm_remove_pt = connection.prepareStatement(sql_remove_pt);
            stm_remove_pt.setInt(1, s.getId());
            stm_remove_pt.executeUpdate();
            
            
            for (PTranier pt : s.getPT()) {
                String sql_insert_pt = "INSERT INTO [Member_PT]\n" +
                                                "([mid]\n" +
                                                ",[pid])\n" +
                                                "VALUES\n" +
                                                "(?\n" +
                                                ",?)";
                PreparedStatement stm_insert_pt = connection.prepareStatement(sql_insert_pt);
                stm_insert_pt.setInt(1, s.getId());
                stm_insert_pt.setInt(2, pt.getId());
                stm_insert_pt.executeUpdate();
            }
            //commit transaction
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        finally
        {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
 public void delete(int id) {
        try {
            String sql = "DELETE Student WHERE id = ?";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
  public void insert(Member s) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO [Member]\n" +
                                "([id],\n" +
                                "[name],\n" +
                                "[age],\n" +
                                "[gender],\n" +
                                "[TestDate],\n" +
                                "[Weight],\n" +
                                "[SMM],\n" +
                                "[BodyFat],\n" +
                                "[classid])\n" +
                                "VALUES \n" +
                                "(?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?,\n" +
                                "?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setInt(1, s.getId());
            stm.setString(2, s.getName());
            stm.setInt(3, s.getAge());
            stm.setBoolean(4, s.isGender());
            stm.setDate(5, s.getDob());
            stm.setString(6, s.getWeight());
            stm.setString(7, s.getSmm());
            stm.setString(8, s.getBodyfat());
            stm.setInt(9, s.getClas().getId());
            stm.executeUpdate();

            //QUERY to get Student Id;
            String sql_get_id = "SELECT @@Identity as mid";
            PreparedStatement stm_get_id = connection.prepareStatement(sql_get_id);
            ResultSet rs = stm_get_id.executeQuery();
            if (rs.next()) {
                s.setId(rs.getInt("mid"));
            }
            
            for (PTranier cert : s.getPT()) {
                String sql_insert_cert = "INSERT INTO [Member_PT]\n" +
                                                "([mid]\n" +
                                                ",[pid])\n" +
                                                "VALUES\n" +
                                                "(?\n" +
                                                ",?)";
                PreparedStatement stm_insert_cert = connection.prepareStatement(sql_insert_cert);
                stm_insert_cert.setInt(1, s.getId());
                stm_insert_cert.setInt(2, cert.getId());
                stm_insert_cert.executeUpdate();
            }

            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
  public ArrayList<Member> search(int id)
          {
        ArrayList<Member> member = new ArrayList<>();
        try {
            String sql = "Select s.id,s.name,s.age,s.gender,s.TestDate,s.Weight,\n" +
                        "s.SMM,s.BodyFat,d.classid,d.cname\n" +
                        "from Member s INNER JOIN Class d \n" +
                        " on s.classid=d.classid "
                    + "Where\n"+
                    "(1=1)";
            int paramIndex = 0;
            HashMap<Integer,Object[]> params = new HashMap<>();
            if(id != -1)
            {
                sql += " AND id = ?";
                paramIndex++;
                Object[] param = new Object[2];
                param[0] = Integer.class.getName();
                param[1] = id;
                params.put(paramIndex, param);
            }
            
            PreparedStatement stm = connection.prepareStatement(sql);
            for (Map.Entry<Integer, Object[]> entry : params.entrySet()) {
                Integer index = entry.getKey();
                Object[] value = entry.getValue();
                String type = value[0].toString();
                if(type.equals(Integer.class.getName()))
                {
                    stm.setInt(index, (Integer)value[1]);
                }
                else if(type.equals(String.class.getName()))
                {
                    stm.setString(index, value[1].toString());
                }
                else if(type.equals(Date.class.getName()))
                {
                    stm.setDate(index, (Date)value[1]);
                }
                else if(type.equals(Boolean.class.getName()))
                {
                    stm.setBoolean(index, (Boolean)value[1]);
                }
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                 Member s = new Member();
                s.setId(rs.getInt("id"));
                s.setName(rs.getString("name"));
                s.setAge(rs.getInt("age"));
                s.setGender(rs.getBoolean("gender"));
                s.setDob(rs.getDate("TestDate"));
                s.setWeight(rs.getString("Weight"));
                s.setSmm(rs.getString("SMM"));
                s.setBodyfat(rs.getString("BodyFat"));
                Class d = new Class();
                d.setId(rs.getInt("classid"));
                d.setName(rs.getString("cname"));
                s.setClas(d);
                member.add(s);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return member;
    }
   public void insert(Register s) {
        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO Register\n" +
                            "([phone],\n" +
                            "[name],\n" +
                            "[email],\n" +
                            "[comment]) VALUES\n" +
                            "(?,?,?,?)";
            PreparedStatement stm = connection.prepareStatement(sql);
            stm.setString(1, s.getPhone());
            stm.setString(2, s.getName());
            stm.setString(3, s.getEmail());
            stm.setString(4, s.getComment());
         
            stm.executeUpdate();
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                connection.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(MemberDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
}
    
    


