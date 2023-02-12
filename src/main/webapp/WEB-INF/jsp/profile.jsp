<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>

<body>
<script src="../../resources/js/profile.js" defer></script>
<script src="../../resources/js/common.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <%--@elvariable id="userTo" type="ru.javawebinar.topjava.to.UserTo"--%>

        <div>
            <form id="detailsForm">
                <input type="hidden" id="id" name="id">

                <div class="form-group">
                    <label class="col-form-label"><spring:message code="user.dealer"/>: </label>
                    <label class="col-form-label" id="dealer"></label>
                </div>

                <div class="form-group">
                    <label for="name" class="col-form-label"><spring:message code="user.name"/></label>
                    <input type="text" class="form-control" id="name" name="name"
                           placeholder="<spring:message code="user.name"/>">
                </div>

                <div class="form-group">
                    <label for="email" class="col-form-label"><spring:message code="user.email"/></label>
                    <input type="email" class="form-control" id="email" name="email"
                           placeholder="<spring:message code="user.email"/>">
                </div>

                <div class="form-group">
                    <label for="password" class="col-form-label"><spring:message code="user.password"/></label>
                    <input type="password" class="form-control" id="password" name="password"
                           placeholder="<spring:message code="user.password"/>">
                </div>
            </form>
        </div>
        <div class="text-right">
            <a class="btn btn-secondary" href="#" onclick="window.history.back()">
                <span class="fa fa-close"></span>
                <spring:message code="common.cancel"/>
            </a>
            <button type="submit" class="btn btn-primary" onclick="save()">
                <span class="fa fa-check"></span>
                <spring:message code="common.save"/>
            </button>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="profile"/>
</jsp:include>
</html>