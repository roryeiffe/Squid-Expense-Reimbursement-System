package reimbursement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class RequestServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // set up print writer:
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // get data from submitted form:
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        int amount = Integer.parseInt(request.getParameter("amount"));

        // default to pending
        String status = "pending";
        HttpSession session = request.getSession(false);
        String userType = (String)session.getAttribute("userType");
        int empId = (Integer) session.getAttribute("userId");
        if(userType.equals("manager")) {
            out.print("managers cannot submit requests");
        }


        // create a reimbursement object:
        Reimbursement reimbursement = new Reimbursement(empId, title, description, amount, status);
        // create a dao TODO create a dao factory class:
        ReimbursementDao dao = new ReimbursementDao();
        dao.openCurrentSessionWithTransaction();
        // insert reimbursement and commit transaction:
        dao.insert(reimbursement);
        dao.commitAndClose();

        // TODO check for success/failure
        RequestDispatcher rd = request.getRequestDispatcher("/index.html");
        out.print("<div class=\"alert alert-success alert-dismissible\" role=\"alert\">Request submitted successfully!<button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");

        rd.include(request,response);
    }
}
