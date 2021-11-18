package reimbursement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // set up print writer:
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();


        // get status from submitted form:
        String status = request.getParameter("status");
        // TODO get employee id from httpsession
        // TODO Also make sure user is logged in
        int id = -1;

        // create a dao:
        ReimbursementDao dao = new ReimbursementDao();
        dao.openCurrentSessionWithTransaction();

        // get list of reimbursements:
        List<Reimbursement> reimbursementList = dao.getReimbursements(id,status);
        dao.commitAndClose();

        // forward this request to jsp file:
        request.setAttribute("posts", reimbursementList);
        request.setAttribute("status", status);
        if(id == -1) request.setAttribute("type", "manager");
        else request.setAttribute("type","employee");
        request.getRequestDispatcher("displayRequests.jsp").forward(request, response);
    }

}