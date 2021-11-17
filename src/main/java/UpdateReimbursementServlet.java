import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class UpdateReimbursementServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // get data from request:
        int reimbursementId = Integer.parseInt(request.getParameter("reimbursementId"));
        String action = request.getParameter("action");

        // create a dao:
        ReimbursementDao dao = new ReimbursementDao();
        // create a session:
        dao.openCurrentSessionWithTransaction();
        Reimbursement reimbursement = dao.getById(reimbursementId);
        // update status, based on action chosen:
        if (action.equals("approve")) reimbursement.setStatus("approved");
        else reimbursement.setStatus("rejected");
        // update the particular reimbursement
        dao.update(reimbursement);
        // commit transaction:
        dao.commitAndClose();

        out.print("Reimbursement request updated!");
        RequestDispatcher rd = request.getRequestDispatcher("/viewMyRequests.html");
        rd.include(request, response);
    }
}
