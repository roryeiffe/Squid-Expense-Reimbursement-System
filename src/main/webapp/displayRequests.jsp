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
        padding-bottom: 10px;
    }
        .container {
            margin-top: 50px;
            width: 75%;
            box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.5);
        }
        .btn {
            display: block;
            margin: auto;
            margin-top: 10px;
            margin-bottom: 10px;
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
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="/index.html">Squid Game</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                            <li class="nav-item active">
                                <a  class="nav-link" href = "checkLoggedIn?path=SubmitRequest">Request Reimbursement</a>
                            </li>

                            <li class="nav-item active">
                                <a class="nav-link"  href = "checkLoggedIn?path=View">View Requests</a>
                            </li>

                            <li class="nav-item active">
                                <a  class="nav-link" href = "login">Login</a>
                            </li>

                            <li class="nav-item active">
                                <a  class="nav-link" href="logout">Logout</a>
                            </li>
                    </ul>
                </div>
            </div>
        </nav>

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
            <input id = "submit" class = "btn btn-primary" type = 'submit' value = 'Update Reimbursements' onClick = "loading()"/>
            <div id = "load">
            </div>
        <%}%>


        </form>

    <div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <script>
        function loading() {
            document.getElementById("submit").style.display = "none";
            document.getElementById("load").innerHTML = "<img src = 'resources/load.gif' style = 'display: block; width: 40px; margin: auto; margin-top: 10px'/>";
        }
    </script>

</body>
</html>