<%@ page import="com.basejava.webapp.model.CompanySection" %>
<%@ page import="com.basejava.webapp.model.ListSection" %>
<%@ page import="com.basejava.webapp.model.TextSection" %>
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
<div class="container">
    <div class="row">
        <div class="col-1"></div>
        <div class="col-11">
            <table>
                <tr>
                    <%--Display name and contacts--%>
                    <td class="align-bottom  view_table_name_td">
                        <h2>${resume.fullName}</h2>
                    </td>
                    <td class="align-bottom  view_table_name_td"></td>
                    <td class="align-bottom view_table_name_td">
                        <h3><a href="resume?uuid=${resume.uuid}&action=edit" title="Редактировать"><i
                                class="icon_color fa fa-pencil"></i></a></h3>
                    </td>
                    <td class="align-bottom  view_table_name_td">
                        <h3><a href="resume?uuid=${resume.uuid}&action=download" title="Скачать PDF"><i
                                class="icon_red fa fa-file-pdf-o"></i></a></h3>
                    </td>

                    <td class="align-bottom">
                        <h3><a href="resume?uuid=${resume.uuid}&action=send" title="Отправить по электронной почте"><i
                                class="icon_color fa fa-share"></i></a></h3>
                    </td>
                </tr>
            </table>

            <table>
                <c:forEach var="contactEntry" items="${resume.contacts}">
                <jsp:useBean id="contactEntry"
                             type="java.util.Map.Entry<com.basejava.webapp.model.ContactType, java.lang.String>"/>
                <tr>
                        <c:set var="contact_type" value="<%=contactEntry.getKey()%>"></c:set>
                    <td class="view_table_conact_td">
                        <c:choose>
                            <c:when test="${contact_type eq 'PHONE'}">
                                <i class='icon_color fa fa-phone'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'MOBILE'}">
                                <i class='icon_color fa fa-mobile'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'MAIL'}">
                                <i class='icon_color fa fa-envelope-o'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'SKYPE'}">
                                <i class='icon_color fa fa-skype'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'LINKEDIN'}">
                                <i class='icon_color fa fa-linkedin'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'GITHUB'}">
                                <i class='icon_color fa fa-github'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'HOME_PAGE'}">
                                <i class='icon_color fa fa-home'></i>
                            </c:when>
                            <c:when test="${contact_type eq 'STACKOVERFLOW'}">
                                <i class='icon_color fa fa-stack-overflow'></i>
                            </c:when>
                            <c:otherwise>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td class="view_table_conact_td">
                        <%=contactEntry.getKey().toHtml(contactEntry.getValue())%><br/>
                    </td>
                    </c:forEach>
            </table>
            <br>

            <%--Display resume's sections--%>
            <c:forEach var="sectionEntry" items="${resume.sections}">
                <jsp:useBean id="sectionEntry"
                             type="java.util.Map.Entry<com.basejava.webapp.model.SectionType,
                 com.basejava.webapp.model.Section>"/>
                <c:set var="sectionType" value="<%=sectionEntry.getKey().name()%>"></c:set>
                <c:set var="sectionTitle" value="<%=sectionEntry.getKey().getTitle()%>"></c:set>
                <h4>${sectionTitle}:</h4>

                <%--Switch on a SectionType--%>
                <c:choose>
                    <c:when test="${(sectionType eq 'PERSONAL') || (sectionType eq 'OBJECTIVE')}">
                        <c:set var="text" value="<%=((TextSection) sectionEntry.getValue()).getText()%>"></c:set>
                        <p>${text}</p>
                        <c:remove var="text"></c:remove>
                    </c:when>
                    <c:when test="${(sectionType eq 'ACHIEVEMENT') || (sectionType eq 'QUALIFICATIONS')}">
                        <ul>
                            <c:forEach var="listItem"
                                       items="<%=((ListSection<String>) sectionEntry.getValue()).getItems()%>">
                                <li>${listItem}</li>
                            </c:forEach>
                        </ul>
                        <c:remove var="listItem"></c:remove>
                    </c:when>
                    <c:when test="${(sectionType eq 'EXPERIENCE') || (sectionType eq 'EDUCATION')}">
                        <c:forEach var="company" items="<%=((CompanySection) sectionEntry.getValue()).getItems()%>">
                            <jsp:useBean id="company" type="com.basejava.webapp.model.Company"></jsp:useBean>
                            <c:set var="itemLink" value="${company.link}"></c:set>
                            <jsp:useBean id="itemLink" type="com.basejava.webapp.model.HyperLink"></jsp:useBean>
                            <%--Display Company  name and HTTP (if exists)--%>
                            <table width="100%">
                                <tr>
                                    <td class="company_personal_info" colspan="4">
                                        <c:if test="${not empty itemLink.link }">
                                            <h5 class="company_title"><a href="${itemLink.link}">${itemLink.title}</a>
                                            </h5>
                                        </c:if>
                                        <c:if test="${empty itemLink.link }">
                                            <h5>${itemLink.title}</h5>
                                        </c:if>
                                    </td>
                                </tr>

                                    <%--Display dates, position and description--%>

                                <c:forEach var="personalInfo" items="${company.companyPersonalInfoList}">
                                    <jsp:useBean id="personalInfo"
                                                 type="com.basejava.webapp.model.CompanyPersonalInfo"></jsp:useBean>
                                    <tr>
                                        <c:set var="end" value="${personalInfo.end}"></c:set>
                                        <c:if test="${end eq '2050-12-01'}">
                                            <c:set var="end" value="Сейчас"></c:set>
                                        </c:if>
                                        <td class="align-top company_personal_info company_personal_info_date">${personalInfo.start}</td>
                                        <td class="align-top company_personal_info company_personal_info_comma">-</td>
                                        <td class="align-top company_personal_info">${end}</td>
                                        <td class="align-top company_personal_info company_position_bold">${personalInfo.text}</td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td></td>
                                        <td></td>
                                        <td class="align-top company_personal_info company_personal_info_descr">${personalInfo.description}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                            <br>
                        </c:forEach>


                    </c:when>

                    <c:otherwise>
                    </c:otherwise>
                </c:choose>

            </c:forEach>
        </div>

    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
</html>