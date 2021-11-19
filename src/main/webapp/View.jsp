<%
    // get data from request:
    String loggedIn = "manager";
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View My Requests</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    <style>

        form {
            margin-top: 20px;
            box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.3);
            padding: 10px;
        }

        .form-select {
            width: 25%;
            margin: auto;
            margin-top: 20px;
        }
        .btn {
            margin: auto;
            margin-top: 25px;
            margin-bottom: 25px;
            display: block;
        }
        p {
            font-size: 20px;
            text-align: center;
        }
    </style>
</head>
<body>

    <% if (loggedIn.equals("none")) { %>
        <p>You must be logged in to view/manage requests!</p>
    <% } else { %>

    <div class = "container">
        <form action = "/viewRequest" method = "get" id = "viewRequest">
            <p class = "p">Please choose which reimbursement requests you would like to view:</p>
            <select class = "form-select" name="status">
                <option value="pending">Pending</option>
                <option value="past">Past</option>
                <option value="all">All</option>
            </select>
            <input class = "btn btn-primary" id = "submit" type = "submit" value = "View" onclick = "loading()"/>
            <p id = "load"></p>
        </form>
    <div>
        <%}%>


    <script>
        function loading() {
            document.getElementById("submit").style.display = "none";
            document.getElementById("load").innerHTML = "loading...";
        }
    </script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>


</body>
</html>