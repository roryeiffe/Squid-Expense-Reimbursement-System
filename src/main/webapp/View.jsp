<%
    // get data from request:
    String loggedIn = (String) request.getAttribute("loggedIn");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>View My Requests</title>
</head>
<body>

    <% if (loggedIn.equals("none")) { %>
        <p>You must be logged in to view/manage requests!</p>
    <% } else { %>

        <form action = "/viewRequest" method = "get" id = "viewRequest">
            <select name="status">
                <option value="pending">Pending</option>
                <option value="past">Past</option>
                <option value="all">All</option>
            </select>
            <input id = "submit" type = "submit" value = "View" onclick = "loading()"/>
            <p id = "load"></p>
        </form>
        <%}%>


    <script>
        function loading() {
            document.getElementById("submit").style.display = "none";
            document.getElementById("load").innerHTML = "loading...";
        }
    </script>

</body>
</html>