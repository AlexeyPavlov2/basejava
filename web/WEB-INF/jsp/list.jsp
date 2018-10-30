<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Список резюмеTitle</title>
    <link rel="shortcut icon" href="img/favicon.png" />
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<%--//TODO Генерация данных--%>
<jsp:include page="fragments/header.jsp"/>
<div class="container-fluid">
    <div class="row">
        <div class="col-8">
            <table class="table table-bordered table-hover">
                <tr>
                    <th class="align-middle text-center">Имя</th>
                    <th class="align-middle text-center">E-mail</th>
                    <th class="align-middle text-center" colspan="2">Действие</th>

                </tr>
                <c:forEach items="${resumes}" var="resume">
                    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume"/>
                    <tr>
                        <td class="align-middle"><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                        <td>
                            <a href="mailto:${resume.getContact(ContactType.MAIL)}">${resume.getContact(ContactType.MAIL)}</a>
                        </td>
                        <td class="align-middle text-center"><span class="text-center"><a href="resume?uuid=${resume.uuid}&action=delete" title="Удалить"><i
                                class="icon_red fa fa-times"></i></a></span></td>
                        <td class="align-middle text-center"><a href="resume?uuid=${resume.uuid}&action=edit" title="Редактировать"><i
                                class="icon_color fa fa fa-pencil"></i></a></td>
                    </tr>

                </c:forEach>
            </table>
        </div>
        <div class="col-2"></div>
        <div class="col-2"><p><a href="resume?action=generate" title="Сгенерировать тестовые данные">Тестовые данные</a></p></div>


    </div>
    <div class="row">
        <div class="col-2">
        <p><a href="resume?&action=insert" title="Добавить резюме"><i class="fa fa-address-card-o icon_color" aria-hidden="true"></i>&nbsp;&nbsp;Добавить резюме</a></p>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>
