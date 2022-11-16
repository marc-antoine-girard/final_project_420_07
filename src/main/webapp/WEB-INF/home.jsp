<%@ page contentType="text/html;charset=UTF-8" %>

<% String message = (String) request.getAttribute("servlet-message"); %>

<html>
<head>
    <title>Title</title>
</head>
<body>
<div id="page-container">
    <jsp:include page="layouts/header.jsp"/>

    <p><%= message %></p>

    <form action="${pageContext.request.contextPath}/animals" method="get">
        <label for="country-input"></label>
        <input name="country" value="" id="country-input" />
        <button type="submit">Filter</button>
    </form>

    <jsp:include page="layouts/footer.jsp"/>
</div>
</body>
</html>
