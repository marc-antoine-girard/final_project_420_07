<%
    String username = (String) session.getAttribute("username");

    if(username == null)
        username = "";
%>
<div id="header">
    <h1>Welcome <%= username %></h1>
    <p>header</p>
</div>