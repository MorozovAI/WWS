<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="meal.title"/></h3>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>

        <br/>
        <button class="btn btn-primary" onclick="clearTable()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link " data-toggle="tab" href="#draft">Новые</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#inReview">В рассмотрении</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#submitted">Рассмотренные</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#approved">Подтвержденные</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#denied">Отказанные</a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade" id="draft">
                <table class="table table-striped" id="draftTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>Дилер</th>
                        <th>Заказ-наряд</th>
                        <th>Номер двигателя</th>
                        <th>Дата неисправности</th>
                        <th>Сумма</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane fade show active" id="inReview">
                <div class="table-responsive">
                    <table class="table table-striped " id="inReviewTable" style="width: 100%;">
                        <colgroup>
                            <col span="1" style="width: 10%;">
                            <col span="1" style="width: 20%;">
                            <col span="1" style="width: 30%;">
                            <col span="1" style="width: 20%;">
                            <col span="1" style="width: 20%;">
                        </colgroup>
                        <thead>
                        <tr>
                            <th>Дилер</th>
                            <th>Заказ-наряд</th>
                            <th>Номер двигателя</th>
                            <th>Дата неисправности</th>
                            <th>Сумма</th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tab-pane fade" id="submitted">
                <div class="table-responsive">
                <table class="table table-striped " id="submittedTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>Дилер</th>
                        <th>Заказ-наряд</th>
                        <th>Номер двигателя</th>
                        <th>Дата неисправности</th>
                        <th>Сумма</th>
                    </tr>
                    </thead>
                </table>
                </div>
            </div>
            <div class="tab-pane fade " id="approved">
                <table class="table table-striped" id="approvedTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 15%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>Дилер</th>
                        <th>Заказ-наряд</th>
                        <th>Номер двигателя</th>
                        <th>Дата неисправности</th>
                        <th>Сумма</th>
                        <th>Сумма2</th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane fade" id="denied">
                <table class="table table-striped" id="deniedTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th>Дилер</th>
                        <th>Заказ-наряд</th>
                        <th>Номер двигателя</th>
                        <th>Дата неисправности</th>
                        <th>Сумма</th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="../../resources/js/claim-pagination.js"></script>
</body>
</html>