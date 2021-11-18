<%
    // get data from request:
    String loggedIn = (String) request.getAttribute("loggedIn");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <title>Submit Reimbursement</title>

    <style>
        h1 {
            text-align: center;
        }

        form {
            width: 75%;
            margin: auto;
            box-shadow: 2px 2px 2px 1px rgba(0, 0, 0, 0.5);
            padding: 10px;
        }

        .btn {
            margin: auto;
            display: block;
            font-size: 20px;
        }

    </style>
</head>
<body>

    <div class = "container">

        <% if (!loggedIn.equals("employee")) { %>
            <p>You must be logged in as an employee to make a request!</p>
        <% } else { %>

            <div id = "alert">

            </div>
            <h1 class = "h1">
                Request Reimbursement
            </h1>

            <form action = "request" method = "post" >
                <input class = "form-control" type = "text" name = "title" placeholder="Enter title" required> </br>
                <textarea class = "form-control" name = "description" placeholder="Enter Description" required></textarea></br>
                <input class = "form-control" type = "number" name = "amount" placeholder="Enter amount" required/></br>
                <input class = "btn btn-primary" id = "submit" type = "submit" value = "Request" onclick="loading()"/>
                <p id = "load"></p>
            </form>
        </div>
        <%}%>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>

    <script>
        function loading() {
            document.getElementById("submit").style.display = "none";
            document.getElementById("load").innerHTML = "loading...";
        }
    </script>



</body>
</html>