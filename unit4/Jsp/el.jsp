<%
    request.setAttribute("name", "Anu");
    request.setAttribute("mark", 90);
%>

<html>
<body>
    Name: ${name} <br>
    Mark: ${mark} <br>

    Result: ${mark >= 40 ? "Pass" : "Fail"}
</body>
</html>
