<%@ page import="com.basejava.webapp.model.CompanySection" %>
<%@ page import="com.basejava.webapp.model.ContactType" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.TextSection" %>
<%@ page import="java.util.stream.Collectors" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="shortcut icon" href="img/favicon.png"/>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
          integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/style.css">
    <script
            src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName}</title>
</head>
<body>
<jsp:include page="fragments/header.jsp"/>
<div class="container-fluid">
    <form method="post" action="resume" name="form_edit" enctype="application/x-www-form-urlencoded">
        <input type="hidden" name="uuid" value="${resume.uuid}">
        <div class="form-group row">
            <label for="fullName" class="col-sm-1 col-form-label">Имя:</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="fullName" name="fullName"
                       placeholder="Сидоров Иван Сергеевич" value="${resume.fullName}" required>

            </div>
        </div>
        <h3>Контакты:</h3><br>
        <div class="form-group">
            <c:set var="counter" value="${1}" scope="page"/>
            <c:forEach var="type" items="<%=ContactType.values()%>">
                <c:if test="${counter == 1}">
                    <div class="form-row">
                </c:if>
                <div class="col-md-2">
                    <label for="${type.name()}" class="col-form-label">${type.title}</label>
                </div>
                <div class="col-md-3">
                    <c:set var="isRequired"
                           value="${(type.name() eq 'MOBILE') || (type.name() eq 'MAIL') ? 'true' : 'false'}"/>

                    <c:choose>
                        <c:when test="${(type.name() eq 'MOBILE') || (type.name() eq 'PHONE') || (type.name() eq 'HOME_PHONE')}">
                            <c:set var="fieldType" value='"tel"'/>
                            <c:set var="fieldPattern"
                                   value=" 'pattern='[\\+]\d{1}\\s[\\(]\\d{3}[\\)]\s\\d{3}[\\-]\\d{2}[\\-]\\d{2}' minlength='18' maxlength='18'"/>
                            <c:set var="fieldPlacholder" value="placeholder='+7 (985) 456-76-33'"/>
                        </c:when>
                        <c:when test="${type.name() eq 'MAIL'}">
                            <c:set var="fieldType" value='"email"'/>
                            <c:set var="fieldPattern" value=""/>
                            <c:set var="fieldPlacholder" value="placeholder='sidorov@yandex.ru'"/>
                        </c:when>
                        <c:when test="${type.name() eq 'HOME_PAGE'}">
                            <c:set var="fieldType" value='"url"'/>
                            <c:set var="fieldPattern" value=""/>
                            <c:set var="fieldPlacholder" value=""/>
                        </c:when>
                        <c:when test="${type.name() eq 'LINKEDIN'}">
                            <c:set var="fieldType" value='"url"'/>
                            <c:set var="fieldPattern" value=""/>
                            <c:set var="fieldPlacholder" value="placeholder='https://linkedin.com/sidorov'"/>
                        </c:when>
                        <c:when test="${type.name() eq 'STACKOVERFLOW'}">
                            <c:set var="fieldType" value='"url"'/>
                            <c:set var="fieldPattern" value=""/>
                            <c:set var="fieldPlacholder" value="placeholder='https://stackoverflow.com/sidorov'"/>
                        </c:when>
                        <c:when test="${type.name() eq 'GITHUB'}">
                            <c:set var="fieldType" value='"url"'/>
                            <c:set var="fieldPattern" value=""/>
                            <c:set var="fieldPlacholder" value="placeholder='https://github.com/sidorov'"/>
                        </c:when>
                        <c:otherwise>
                            <c:set var="fieldType" value='"text"'/>
                            <c:set var="fieldPattern" value=""/>
                            <c:set var="fieldPlacholder" value=" "/>
                        </c:otherwise>
                    </c:choose>
                    <input class="form-control" type=${fieldType} name="${type.name()}" id="${type.name()}"
                           value="${resume.getContact(type)}" ${fieldPattern} ${fieldPattern} ${fieldPlacholder}
                        ${isRequired eq 'true' ? " required aria-required='true'" : ''}>
                </div>

                <c:if test="${counter != 1}">
                    </div>
                    <br>
                </c:if>

                <c:if test="${(counter <= 2)}">
                    <c:set var="counter" value="${counter + 1}"/>
                </c:if>
                <c:if test="${counter == 3}">
                    <c:set var="counter" value="${1}"/>
                </c:if>
            </c:forEach>
        </div>
        <br>

        <div class="form-group">
            <c:forEach var="sectionEntry" items="${resume.sections}">

                <jsp:useBean id="sectionEntry"
                             type="java.util.Map.Entry<com.basejava.webapp.model.SectionType,
                 com.basejava.webapp.model.Section>"/>

                <c:set var="sectionType" value="<%=sectionEntry.getKey().name()%>"/>
                <c:set var="sectionTitle" value="<%=sectionEntry.getKey().getTitle()%>"/>
                <h4>${sectionTitle}:</h4>
                <%--BEGIN--%>
                <c:choose>
                    <c:when test="${sectionType eq 'OBJECTIVE'}">
                        <div class="form row">
                            <div class="form-group col-md-6">
                                <input name="${sectionType}" class="form-control"
                                       value="<%=((TextSection) sectionEntry.getValue()).getText()%>"/>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sectionType eq 'PERSONAL'}">
                        <div class="form row">
                            <div class="form-group col-md-6">
                            <textarea class="form-control" name="${sectionType}"
                                      rows="4"><%=((TextSection) sectionEntry.getValue()).getText()%>
                                    </textarea>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sectionType eq 'ACHIEVEMENT' || sectionType eq 'QUALIFICATIONS'}">
                        <div class="form row">
                            <div class="form-group col-md-6">
                                    <textarea class="form-control" name="${sectionType}"
                                              rows="5"><%=((ListSection<String>) sectionEntry.getValue()).getItems().stream().collect(Collectors.joining("\n"))%>
                                    </textarea>
                            </div>
                        </div>
                    </c:when>

                    <c:when test="${sectionType eq 'EXPERIENCE'}">
                        <div class="form row">
                            <div class="form-group col-md-2">
                                <button class="form-control btn btn-primary">Добавить организацию</button>
                            </div>
                        </div>

                        <c:forEach var="company" items="<%=((CompanySection) sectionEntry.getValue()).getItems()%>"
                                   varStatus="count1">
                            <div class="company_div" id="${'company_div'.concat(count1.count)}">
                                <jsp:useBean id="company" type="com.basejava.webapp.model.Company"/>
                                <c:set var="itemLink" value="${company.link}"/>
                                <jsp:useBean id="itemLink" type="com.basejava.webapp.model.HyperLink"/>
                                <div class="form row">
                                    <div class="form-group col-md-4">
                                        <label for="${'company_title'.concat(count1.count)}" class="col-form-label">Название
                                            компании:</label>
                                        <input name="company_title" class="form-control"
                                               value="${itemLink.title}" id="${'company_title'.concat(count1.count)}"/>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="${'company_url'.concat(count1.count)}" class="col-form-label">Сайт:</label>
                                        <input name="company_url" class="form-control"
                                               value="${itemLink.link}" id="${'company_url'.concat(count1.count)}"/>
                                    </div>
                                    <div class="col-md-2">
                                        <label for="" class="col-form-label">Действия:</label>
                                        <button class="btn btn btn-danger">Удалить сведения</button>
                                    </div>

                                </div>


                                <c:forEach var="personalInfo" items="${company.companyPersonalInfoList}"
                                           varStatus="count2">
                                    <jsp:useBean id="personalInfo"
                                                 type="com.basejava.webapp.model.CompanyPersonalInfo"/>
                                    <div class="form row">
                                        <div class="form-group col-md-2">
                                            <label for=id="${'startDate'.concat(count1.count).concat('_'.concat(count2.count))}"
                                                   class="col-form-label">Дата начала:</label>
                                            <input name="startDate" class="form-control"
                                                   value="${personalInfo.start}"
                                                   id="${'startDate'.concat(count1.count).concat('_'.concat(count2.count))}"/>
                                        </div>
                                        <div class="col-md-2">
                                            <label for="${'startDate'.concat(count1.count).concat('_'.concat(count2.count))}"
                                                   class="col-form-label">Дата окончания:</label>
                                            <input name="endDate" class="form-control"
                                                   value="${personalInfo.end}"
                                                   id="${'endDate'.concat(count1.count).concat('_'.concat(count2.count))}"/>
                                        </div>
                                        <div class="col-md-4">
                                            <label for="${'position'.concat(count1.count).concat('_'.concat(count2.count))}"
                                                   class="col-form-label">Позиция:</label>
                                            <input name="position" class="form-control"
                                                   value="${personalInfo.text}"
                                                   id="${'position'.concat(count1.count).concat('_'.concat(count2.count))}"/>
                                        </div>
                                    </div>
                                    <div class="form row">
                                        <div class="col-md-8">
                                            <label for="${'description'.concat(count1.count).concat('_'.concat(count2.count))}"
                                                   class="col-form-label">Обязанности:</label>
                                            <textarea name="description" class="form-control" rows="5"
                                                      id="${'description'.concat(count1.count).concat('_'.concat(count2.count))}">${personalInfo.description}
                                            </textarea>
                                        </div>
                                    </div>
                                    <br>

                                </c:forEach> <%--on CompanyPersonalInfo--%>
                            </div>
                            <br>
                        </c:forEach> <%--on Company--%>
                    </c:when>


                    <c:otherwise>
                    </c:otherwise>
                </c:choose>
            </c:forEach> <%--on resume.section--%>
        </div>

        <%--Submit--%>
        <div class="form-group row">
            <div class="col-sm-1">
                <button type="submit" class="btn btn-primary">Сохранить</button>
            </div>
            <div class="col-sm-1">
                <button onclick="window.history.back()" class="btn btn-primary">Отменить</button>
            </div>
        </div>

    </form>


</div>
<jsp:include page="fragments/footer.jsp"/>

<script>

</script>
</body>
</html>