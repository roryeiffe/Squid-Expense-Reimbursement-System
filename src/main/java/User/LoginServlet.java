package User;

import Factory.DaoFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {
    private UserDao loginDao;

    public void init(){
        loginDao = DaoFactory.getUserDao();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        resp.sendRedirect("login.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doPost(req, resp);

        try{
            authenticate(req, resp);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void authenticate(HttpServletRequest req, HttpServletResponse resp) throws Exception{
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        User user = loginDao.validate(email, password);

        if (user == null){
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            dispatcher.include(req, resp);
            out.print("<script>alert('Login failed!','danger')</script>");
        }
        else if(user.isMang() == true){
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            HttpSession session = req.getSession();
            session.setAttribute("userType", "manager");
            session.setAttribute("userId",user.getId());
            dispatcher.include(req,resp);
            out.print("<script>alert('Successfully logged in as manager!','success')</script>");
            System.out.println("Welcome "+ user.getName() + " " + user.isMang());
        } else if(user.isMang() == false){
            RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
            HttpSession session = req.getSession();
            session.setAttribute("userType", "employee");
            session.setAttribute("userId",user.getId());
            dispatcher.include(req, resp);
            out.print("<script>alert('Successfully logged in as employee!','success')</script>");
            System.out.println("Welcome "+ user.getName());
            //throw new Exception("Login not successful...");
        }
    }
}
