package reimbursement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        // TODO: Get employee id from session/cookie/wherever it is stored:
        int empId = 1;

        // create a reimbursement object:
        Reimbursement reimbursement = new Reimbursement(empId, title, description, amount, status);
        // create a dao TODO create a dao factory class:
        ReimbursementDao dao = new ReimbursementDao();
        dao.openCurrentSessionWithTransaction();
        // insert reimbursement and commit transaction:
        dao.insert(reimbursement);
        dao.commitAndClose();

        // TODO check for success/failure
        out.print("<div class=\"alert alert-success\" alert-dismissible\" role=\"alert\"> Your request was submitted successfully! <button type=\"button\" class=\"btn-close\" data-bs-dismiss=\"alert\" aria-label=\"Close\"></button></div>");
        RequestDispatcher rd = request.getRequestDispatcher("/Request.html");
        rd.include(request,response);
    }
}
