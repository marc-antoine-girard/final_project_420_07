<%--suppress unchecked --%>
<%@ page import="models.entities.Farm" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="models.actions.FarmAction" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%
    HashMap<Integer, Farm> farms = (HashMap<Integer, Farm>) request.getAttribute(FarmAction.FARMS_PARAM);
    if (farms == null) farms = new HashMap<>(); // So that it is not null, but empty
%>
<html>
<head>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Animal page</title>
</head>
<body>
<div id="page-container">
    <jsp:include page="layouts/header.jsp"/>
    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Country</th>
            </tr>
        </thead>
        <tbody>
            <%
                for (Map.Entry<Integer, Farm> farm : farms.entrySet())
                { %>
                <tr>
                    <td><%= farm.getValue().getName() %></td>
                    <td><%= farm.getValue().getCountry() %></td>
                </tr>
                <%}
            %>
        </tbody>
    </table>
    <img src="${pageContext.request.contextPath}/img/cat.jpg" alt="cat">
    <jsp:include page="layouts/footer.jsp"/>
</div>
</body>
</html>
