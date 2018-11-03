<%@ page import="com.basejava.webapp.model.*" %>
<%@ page import="com.basejava.webapp.util.DateConverter" %>
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
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
    <jsp:useBean id="resume" type="com.basejava.webapp.model.Resume" scope="request"/>
    <title>Резюме ${resume.fullName.trim().length() != 0 ? resume.fullName : "Новое резюме"}</title>
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
                       placeholder="Сидоров Иван Сергеевич" value="${resume.fullName != null ? resume.fullName : ""}"
                <%--autofocus--%> required>

            </div>
        </div>
        <h3>Контакты:</h3><br>
        <div class="form-group">
            <c:set var="counter" value="${1}" scope="request"/>
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
                           value='${resume.getContact(type) != null ? resume.getContact(type) : ""}' ${fieldPattern} ${fieldPlacholder}
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
            <c:forEach var="sectionType" items="<%=SectionType.values()%>">
                <c:set var="section"
                       value='${resume.getSection(sectionType) != null ? resume.getSection(sectionType) : ""}'/>
                <jsp:useBean id="section" type="com.basejava.webapp.model.Section"/>
                <h4>${sectionType.title}</h4>
                <c:choose>
                    <c:when test="${sectionType eq 'OBJECTIVE'}">
                        <div class="form row">
                            <div class="form-group col-md-6">
                                <input type="text" name="${sectionType}" class="form-control"
                                       value='<%=section != null ? ((TextSection) section).getText() : "" %>' required>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sectionType eq 'PERSONAL'}">
                        <div class="form row">
                            <div class="form-group col-md-6">
                                <textarea name="${sectionType}" class="form-control" rows="5"
                                          required><%=section != null ? ((TextSection) section).getText() : "" %></textarea>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sectionType eq 'QUALIFICATIONS' || sectionType eq 'ACHIEVEMENT'}">
                        <div class="form row">
                            <div class="form-group col-md-6">
                    <textarea name="${sectionType}" class="form-control" rows="5"
                              required><%=section != null ? String.join("\n", ((ListSection) section).getItems()) : ""%>
                    </textarea>
                            </div>
                        </div>
                    </c:when>
                    <c:when test="${sectionType eq 'EXPERIENCE' || sectionType eq 'EDUCATION'}">

                        <div class="form row">
                            <div class="form-group col-sm-2">
                                <button type="button" class="${sectionType} form-control btn btn-primary"
                                        id="${sectionType}add_company_button"
                                        name="${sectionType}add_button"
                                        title="Добавить компанию">Добавить
                                </button>
                            </div>
                        </div>

                        <%--New Section--%>
                        <div class="d-none ${sectionType}new_div ">
                            <div class="form-group row">
                                <div class="col-md-4">
                                    <label for="${sectionType}companyTitle1" class="col-form-label">Название:</label>
                                    <input type="text" name="${sectionType}companyTitle1"
                                           id="${sectionType}companyTitle1" class="form-control"
                                           value="" placeholder="Новая компания">
                                </div>

                                <div class="col-md-4">
                                    <label for="${sectionType}companyURL1" class="col-form-label">Сайт:</label>
                                    <input type="text" name="${sectionType}companyURL1" id="${sectionType}companyURL1"
                                           class="form-control"
                                           value="" placeholder="https://google.com">
                                </div>

                            </div>
                            <div class="form-group row">
                                <div class="col-md-2">
                                    <label for="${sectionType}startDate1" class="col-form-label">Дата начала:</label>
                                    <input type="text" name="${sectionType}startDate1" id="${sectionType}startDate1"
                                           class="form-control"
                                           value="" placeholder="10.11.2018">
                                </div>
                                <div class="col-md-2">
                                    <label for="${sectionType}endDate1" class="col-form-label">Дата окончания:</label>
                                    <input type="text" name="${sectionType}endDate1" id="${sectionType}endDate1"
                                           class="form-control"
                                           value="" placeholder="Сейчас">
                                </div>

                                <div class="col-md-4">
                                    <label for="${sectionType}text1" class="col-form-label">Позиция:</label>
                                    <input type="text" name="${sectionType}text1" id="${sectionType}text1"
                                           class="form-control"
                                           value="">
                                </div>
                            </div>

                            <c:if test="${sectionType eq 'EXPERIENCE'}">
                                <div class="form-group row ">
                                    <div class="col-md-6">
                                        <label for="${sectionType}description1"
                                               class="col-form-label">Описание:</label>
                                        <textarea name="${sectionType}description1"
                                                  id="${sectionType}description1"
                                                  class="form-control" rows="8"></textarea>
                                    </div>
                                </div>
                            </c:if>


                        </div>
                        <hr>
                        <%--End New Section--%>

                        <c:forEach var="company" items="<%=((CompanySection) section).getItems()%>"
                                   varStatus="theCounter"> <%--On companies--%>
                            <div id="${sectionType}companyDiv${theCounter.index}">
                                <div class="form-group row">
                                    <div class="col-md-4">
                                        <label for="${sectionType}" class="col-form-label">Название:</label>
                                        <input type="text" name="${sectionType}" id="${sectionType}"
                                               class="form-control"
                                               value="${company.link.title}" required>
                                    </div>
                                    <div class="col-md-4">
                                        <label for="${sectionType}companyURL${theCounter.index}" class="col-form-label">Сайт:</label>
                                        <input type="text" name="${sectionType}companyURL"
                                               id="${sectionType}companyURL${theCounter.index}" class="form-control"
                                               value="${company.link.link}">
                                    </div>
                                    <div class="col-md-4">
                                        <button type="button"
                                                class="${sectionType}delete_company_button btn btn-danger vbottom"
                                                id="${sectionType}delete_company_button${theCounter.index}"
                                                data-company-div="${sectionType}companyDiv${theCounter.index}">Удалить
                                        </button>
                                    </div>

                                </div>
                                    <%--add period button--%>
                                <div class="form-group row">
                                    <div class="col-md-4">
                                        <button type="button" title="Добавить период"
                                                class="${sectionType}add_period_button btn btn-primary"
                                                id="${sectionType}add_period_button${theCounter.index}"
                                                data-company-div="${sectionType}companyDiv${theCounter.index}"
                                                data-company-period="period">
                                            Добавить период
                                        </button>
                                    </div>
                                </div>

                                <c:forEach var="info" items="${company.companyPersonalInfoList}" varStatus="thePeriod">
                                    <jsp:useBean id="info" type="com.basejava.webapp.model.CompanyPersonalInfo"/>
                                    <div id="${sectionType}_periodDiv${thePeriod.index}">
                                        <div class="form-group row">
                                            <div class="col-md-2">
                                                <label for="${sectionType}${theCounter.index}startDate"
                                                       class="col-form-label">Дата
                                                    начала:</label>
                                                <input type="text" name="${sectionType}${theCounter.index}startDate"
                                                       id="${sectionType}${theCounter.index}startDate"
                                                       class="form-control"
                                                       value="<%=DateConverter.formatLocalDate(info.getStart(),"dd.MM.yyyy")%>"
                                                       required>
                                            </div>
                                            <div class="col-md-2">
                                                <label for="${sectionType}${theCounter.index}endDate"
                                                       class="col-form-label">Дата
                                                    окончания:</label>
                                                <input type="text" name="${sectionType}${theCounter.index}endDate"
                                                       id="${sectionType}${theCounter.index}endDate"
                                                       class="form-control"
                                                       value='<%=!DateConverter.formatLocalDate(info.getEnd(),"dd.MM.yyyy").equals("01.12.2050") ? DateConverter.formatLocalDate(info.getEnd(),"dd.MM.yyyy") : "Сейчас"%>'
                                                       required>
                                            </div>

                                            <div class="col-md-4">
                                                <label for="${sectionType}${theCounter.index}text"
                                                       class="col-form-label">Позиция:</label>
                                                <input type="text" name="${sectionType}${theCounter.index}text"
                                                       id="${sectionType}${theCounter.index}text" class="form-control"
                                                       value="${info.text}" required>
                                            </div>
                                            <div class="col-md-4">
                                                    <%--<label for="" class="col-form-label"></label> <br>--%>

                                                <button type="button" title="Удалить период"
                                                        class="${sectionType}delete_period_button vbottom btn btn-link"
                                                        id="${sectionType}add_period_button"
                                                        data-company-div="${sectionType}companyDiv${theCounter.index}"
                                                        data-company-period="period${thePeriod.index}">
                                                    <i class="icon_red fa fa-minus"></i></button>
                                            </div>
                                        </div>

                                        <c:if test="${sectionType eq 'EXPERIENCE'}">
                                            <c:set var="isHidden" value=""/>
                                        </c:if>
                                        <c:if test="${sectionType eq 'EDUCATION'}">
                                            <c:set var="isHidden" value="d-none"/>
                                        </c:if>

                                        <div class="form-group row ${isHidden}">
                                            <div class="col-md-6">
                                                <label for="${sectionType}${theCounter.index}description"
                                                       class="col-form-label">Описание:</label>
                                                <textarea name="${sectionType}${theCounter.index}description"
                                                          id="${sectionType}${theCounter.index}description"
                                                          class="form-control" rows="8">${info.description}</textarea>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>

                                <hr>
                            </div> <%--CompanyDiv#--%>
                        </c:forEach>


                    </c:when>
                    <c:otherwise>
                    </c:otherwise>
                </c:choose>

            </c:forEach>

        </div>
        <%--Форм гроуп--%>

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

<script src="js/edit_utils.js"></script>

</body>
</html>