<html>
<body>
<h2>Hello World!</h2>
<%

    HttpSession s = request.getSession();
    Object o = s.getAttribute("userid");
    if(o==null)
    {
        response.sendRedirect("login.html");
    }
%>
</body>



asta e cod protejat, se vede doar dupa login


<p>
    <a href="logout">Logout</a>
</p>
</html>
