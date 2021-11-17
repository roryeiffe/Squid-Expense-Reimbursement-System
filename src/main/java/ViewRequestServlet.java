import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ViewRequestServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
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

        // construct table
        out.print("<table border = '1'> <tr> <th>Title</th> <th>Description</th> <th>Amount</th> <th>Status</th> </tr>");
        for(Reimbursement reimbursement: reimbursementList) {
            // create a row for each reimbursement:
            out.print("<tr>");
            out.print("<td>" + reimbursement.getTitle() + "</td>");
            out.print("<td>" + reimbursement.getDescription() + "</td>");
            out.print("<td>" + reimbursement.getAmount() + "</td>");
            out.print("<td>" + reimbursement.getStatus() + "</td>");
            out.print("</tr>");
        }
        out.print("</table");



    }

}
