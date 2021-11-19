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
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link"  href = "/">Home</a>
                    </li >
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
        </nav>

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
            document.getElementById("load").innerHTML = "loading... <img src = 'resources/load.gif' width = '20px'/>";
        }
    </script>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>