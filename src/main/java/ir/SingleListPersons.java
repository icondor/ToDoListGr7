package ir;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class SingleListPersons {

    private List<IR> listofNames = new ArrayList<>();

    public void addInTheListOfNames(IR s, int fkuser) throws ClassNotFoundException, SQLException {

        if (s.getIntrebare().trim().length() > 0 && s.getRaspuns().trim().length() > 0) {
            Class.forName("org.postgresql.Driver");

            final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
            final String USERNAME = "fasttrackit_dev";
            final String PASSWORD = "fasttrackit_dev";

            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

            PreparedStatement pSt = conn.prepareStatement("INSERT INTO ir (intrebare, raspuns, fkuser) VALUES (?,?, ?)");
            pSt.setString(1, s.getIntrebare());
            pSt.setString(2, s.getRaspuns());
            pSt.setInt(3, fkuser);

            int rowsInserted = pSt.executeUpdate();

            pSt.close();
            conn.close();
        }
    }

    public List getListOfNames(int iduser) throws ClassNotFoundException, SQLException{

        Class.forName("org.postgresql.Driver");

        final String URL = "jdbc:postgresql://54.93.65.5:5432/laura7";
        final String USERNAME = "fasttrackit_dev";
        final String PASSWORD = "fasttrackit_dev";

        Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        PreparedStatement pSt = conn.prepareStatement("SELECT intrebare, raspuns FROM ir where fkuser="+iduser);
        ResultSet rs = pSt.executeQuery();
        while(rs.next()) {
            IR ir = new IR();
            ir.setIntrebare(rs.getString("intrebare"));
            ir.setRaspuns(rs.getString("raspuns"));
            listofNames.add(ir);
        }

        pSt.close();
        conn.close();

        return listofNames;
    }

}



