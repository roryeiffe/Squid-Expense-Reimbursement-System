<%@ page import = "reimbursement.Reimbursement"%>
<%@ page import = "java.util.*"%>
<%@ page import = "java.util.List"%>

<%
    // get data from request:
    String status = (String) request.getAttribute("status");
    String type = (String) request.getAttribute("type");
    List<Reimbursement> reimbursements = (List<Reimbursement>) request.getAttribute("posts");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>View Requests</title>

    <style>
        .container {
        margin-top: 50px;
            width: 75%;
            box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.5);
        }
        .btn {
            float: right;
            margin-top: 10px;
        }
        input[type = "radio"] {
            cursor: pointer;
            width: 20px;
            height: 20px;
        }
        tbody tr:hover {
            background: #c4fbff;
        }
    </style>
</head>
<body>
    <div class = "container">
        <form action = '/updateReimbursement' method = 'post'>
            <table class = "table table-striped table-hover" >
                <thead>
                    <tr>
                        <% if (type.equals("manager")) { %>
                            <th>Employee Id:</th>
                        <% } %>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Amount</th>
                        <th>Status</th>
                        <% if (type.equals("manager") && status.equals("pending")) { %>
                            <th>Approve</th>
                            <th>Reject</th>
                        <% } %>
                    </tr>
                </thead>
                <tbody>
                    <%for(Reimbursement reimbursement: reimbursements) {
                        if(type.equals("manager")) {
                    %>
                    <tr> <td> <%= reimbursement.getEmpId() %> </td>
                    <%} else{ %>
                        <tr>
                    <%}%>

                    <td> <%= reimbursement.getTitle() %> </td>
                    <td> <%= reimbursement.getDescription() %> </td>
                    <td> <%= reimbursement.getAmount() %> </td>
                    <td> <%= reimbursement.getStatus() %> </td>


                    <% if (type.equals("manager") && status.equals("pending")) { %>
                        <td><input type = 'radio' name = <%= reimbursement.getId() %>  value = 'approve'/></td>
                        <td><input type = 'radio' name = <%= reimbursement.getId() %>  value = 'reject'/></td>
                    <% } %>

                    </tr>
                    <%}%>
                </tbody>
            </table>

        <%if(type.equals("manager") && status.equals("pending")){%>
            <td><input class = "btn btn-primary" type = 'submit' value = 'Update Reimbursements'/></td>
        <%}%>
        </form>
    <div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>
</html>