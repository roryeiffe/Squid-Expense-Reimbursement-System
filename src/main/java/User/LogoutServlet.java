package User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        // invalidate session:
        HttpSession session = req.getSession();
        session.invalidate();

        // redirect to home page and say logout successful:
        RequestDispatcher dispatcher = req.getRequestDispatcher("index.html");
        dispatcher.include(req,resp);
        // add success alert:
        out.print("<script>alert('Logout successful!','success')</script>");
        out.close();
    }
}
