<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container-fluid">
    <form method="post" action="resume" enctype="application/x-www-form-urlencoded">
        <div class="form-group row">
            <label for="inputEmail3" class="col-sm-1 col-form-label">Имя:</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="inputEmail3" value="${resume.fullName}">
            </div>
        </div>
        <h3>Контакты:</h3><br>
        <c:forEach var="type" items="<%=ContactType.values()%>">

            <div class="form-group row">
                <label for="${type.name()}" class="col-sm-2 col-form-label">${type.title}</label>
                <div class="col-sm-3">
                    <input class="form-control" name="${type.name()}" id="${type.name()}" value="${resume.getContact(type)}">
                </div>
            </div>
        </c:forEach>

        <h3>Позиция:</h3><br>








        <%--Submit--%>
        <div class="form-group row">
            <div class="col-sm-10">
                <button type="submit" class="btn btn-primary">Sign in</button>
            </div>
        </div>

    </form>



</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>