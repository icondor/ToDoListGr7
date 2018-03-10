package ir;

import org.json.JSONObject;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;



@WebServlet("/tl")
public class JSON extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) {

        String action = req.getParameter("action");

        if (action != null && action.equals("read"))
            read(req, resp);
        else if (action != null && action.equals("write"))
            write(req, resp);
        else if (action != null && action.equals("markdone"))
            markdone(req, resp);
    }

    private void markdone(HttpServletRequest req, HttpServletResponse resp) {

        String id = req.getParameter("id");

        int idtask = Integer.parseInt(id);

        int fkuser=-1;

        HttpSession s = req.getSession();
        Object o = s.getAttribute("userid");
        if(o!=null){
            fkuser=(Integer)o;
        }

        if(fkuser!=-1) {

            SingleListPersons listOfNames = new SingleListPersons();
            try {


                listOfNames.markDone(idtask, fkuser);
            } catch (ClassNotFoundException e) {

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void read(HttpServletRequest req, HttpServletResponse resp) {
        //SingleListPersons listQA = SingleListPersons.getInstance();
        SingleListPersons listQA = new SingleListPersons();

        int iduser=-1;

        HttpSession s = req.getSession();
        Object o = s.getAttribute("userid");
        if(o!=null){
            iduser=(Integer)o;
        }

        JSONObject json = new JSONObject();
        try {
            json.put("todo", listQA.getListOfNames(iduser));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        returnJsonResponse(resp, json.toString());
    }

    private void write(HttpServletRequest req, HttpServletResponse resp) {

        String name = req.getParameter("newName");
        ToDoItem ir = new ToDoItem(name);

        int fkuser=-1;

        HttpSession s = req.getSession();
        Object o = s.getAttribute("userid");
        if(o!=null){
            fkuser=(Integer)o;
        }

        if(fkuser!=-1) {

            SingleListPersons listOfNames = new SingleListPersons();
            try {
                listOfNames.addInTheListOfNames(ir, fkuser);
            } catch (ClassNotFoundException e) {

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void returnJsonResponse(HttpServletResponse response, String jsonResponse) {
        response.setContentType("application/json");
        PrintWriter pr = null;
        try {
            pr = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert pr != null;
        pr.write(jsonResponse);
        pr.close();
    }
}