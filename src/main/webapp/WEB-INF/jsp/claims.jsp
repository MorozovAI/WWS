<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script src="../../resources/js/common.js" ></script>
<script src="../../resources/js/claims.js" defer></script>

<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="claim.title"/></h3>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>

        <br/>
        <button class="btn btn-primary" onclick="add()">
            <span class="fa fa-plus"></span>
            <spring:message code="common.add"/>
        </button>

        <ul class="nav nav-tabs">
            <li class="nav-item">
                <a class="nav-link " data-toggle="tab" href="#draft"><spring:message code="claim.draft"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" data-toggle="tab" href="#inReview"><spring:message
                        code="claim.inReview"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#submitted"><spring:message code="claim.submitted"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#approved"><spring:message code="claim.approved"/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" data-toggle="tab" href="#denied"><spring:message code="claim.denied"/></a>
            </li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane fade" id="draft">
                <table class="dataTable compact table-striped" id="draftTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 25%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 5%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th><spring:message code="claim.dealer"/></th>
                        <th><spring:message code="claim.dealerRO"/></th>
                        <th><spring:message code="claim.esnShort"/></th>
                        <th><spring:message code="claim.failureDate"/></th>
                        <th><spring:message code="claim.claimAmountShort"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane fade show active" id="inReview">
                <table class="dataTable compact row-border table-striped" id="inReviewTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 25%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 3%;">
                        <col span="1" style="width: 3%;">
                        <col span="1" style="width: 3%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th><spring:message code="claim.dealer"/></th>
                        <th><spring:message code="claim.dealerRO"/></th>
                        <th><spring:message code="claim.esnShort"/></th>
                        <th><spring:message code="claim.failureDate"/></th>
                        <th><spring:message code="claim.claimAmountShort"/></th>
                        <th></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane fade" id="submitted">
                <div class="table-responsive">
                    <table class="dataTable compact table-striped" id="submittedTable" style="width: 100%;">
                        <colgroup>
                            <col span="1" style="width: 10%;">
                            <col span="1" style="width: 20%;">
                            <col span="1" style="width: 30%;">
                            <col span="1" style="width: 20%;">
                            <col span="1" style="width: 15%;">
                            <col span="1" style="width: 5%;">
                        </colgroup>
                        <thead>
                        <tr>
                            <th><spring:message code="claim.dealer"/></th>
                            <th><spring:message code="claim.dealerRO"/></th>
                            <th><spring:message code="claim.esnShort"/></th>
                            <th><spring:message code="claim.failureDate"/></th>
                            <th><spring:message code="claim.claimAmountShort"/></th>
                            <th></th>
                        </tr>
                        </thead>
                    </table>
                </div>
            </div>
            <div class="tab-pane fade " id="approved">
                <table class="dataTable compact table-striped" id="approvedTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th><spring:message code="claim.dealer"/></th>
                        <th><spring:message code="claim.dealerRO"/></th>
                        <th><spring:message code="claim.esnShort"/></th>
                        <th><spring:message code="claim.failureDate"/></th>
                        <th><spring:message code="claim.claimAmountShort"/></th>
                        <th><spring:message code="claim.approvedAmountShort"/></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
            <div class="tab-pane fade" id="denied">
                <table class="dataTable compact table-striped" id="deniedTable" style="width: 100%;">
                    <colgroup>
                        <col span="1" style="width: 10%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 30%;">
                        <col span="1" style="width: 20%;">
                        <col span="1" style="width: 15%;">
                        <col span="1" style="width: 5%;">
                    </colgroup>
                    <thead>
                    <tr>
                        <th><spring:message code="claim.dealer"/></th>
                        <th><spring:message code="claim.dealerRO"/></th>
                        <th><spring:message code="claim.esnShort"/></th>
                        <th><spring:message code="claim.failureDate"/></th>
                        <th><spring:message code="claim.claimAmountShort"/></th>
                        <th></th>
                    </tr>
                    </thead>
                </table>
            </div>
        </div>
    </div>
</div>


<div class="modal fade" tabindex="-1" id="editRow">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="modalTitle"></h4>
                <button type="button" class="close" data-dismiss="modal" onclick="closeNoty()">&times;</button>
            </div>
            <div class="modal-body">
                <form id="detailsForm">
                    <input type="hidden" id="id" name="id">
                    <input type="hidden" id="status" name="status">
                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="dealerRO" class="col-form-label"><spring:message
                                        code="claim.dealerRO"/></label>
                                <input class="form-control" id="dealerRO" name="dealerRO" autocomplete="off"
                                       placeholder="<spring:message code="claim.dealerRO"/>">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="failureDate" class="col-form-label"><spring:message
                                        code="claim.failureDate"/></label>
                                <input type="date" class="form-control" id="failureDate" name="failureDate">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col">
                            <div class="form-group">
                                <label for="esn" class="col-form-label"><spring:message code="claim.esn"/></label>
                                <input type="text" class="form-control" id="esn" name="esn">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="mileage" class="col-form-label"><spring:message
                                        code="claim.mileage"/></label>
                                <div class="form-inline">
                                    <input type="text" class="form-control" id="mileage" name="mileage"
                                           style="width: 60%;">
                                    <select class="form-control" aria-label="mileage type select" id="mileageType"
                                            name="mileageType" style="width: 40%;">
                                        <option value="H">H</option>
                                        <option value="KM">KM</option>
                                        <option value="M">M</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-7">
                            <div class="form-group">
                                <label for="oem" class="col-form-label"><spring:message code="claim.oem"/></label>
                                <input type="text" class="form-control" id="oem" name="oem">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="applicationType" class="col-form-label"><spring:message
                                        code="claim.application"/></label>
                                <select class="form-control" id="applicationType" name="applicationType"
                                        aria-label="application type select">
                                    <option value="INDUSTRIAL">INDUSTRIAL</option>
                                    <option value="AUTOMOTIVE">AUTOMOTIVE</option>
                                    <option value="GENSET">GENSET</option>
                                    <option value="FIREPUM">FIREPUMP</option>
                                    <option value="RAILWAY">RAILWAY</option>
                                    <option value="BUS">BUS</option>
                                    <option value="MARINE">MARINE</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-sm-5">
                            <div class="form-group">
                                <label for="engineModel" class="col-form-label"><spring:message
                                        code="claim.engineModel"/></label>
                                <input type="text" class="form-control" id="engineModel" name="engineModel">
                            </div>
                        </div>
                        <div class="col">
                            <div class="form-group">
                                <label for="claimAmount" class="col-form-label"><spring:message
                                        code="claim.claimAmount"/></label>
                                <input type="text" class="form-control" id="claimAmount" name="claimAmount">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="narrative" class="col-form-label"><spring:message
                                code="claim.narrative"/></label>
                        <textarea class="form-control" id="narrative" name="narrative" rows="5"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="closeNoty()">
                    <span class="fa fa-close"></span>
                    <spring:message code="common.cancel"/>
                </button>
                <button id="asDraftBtn" type="button" class="btn btn-primary" onclick="saveAsDraft()">
                    <span class="fa fa-check"></span>
                    как драфт
                </button>
                <button type="button" class="btn btn-primary" onclick="save()">
                    <span class="fa fa-check"></span>
                    <spring:message code="common.save"/>
                </button>
            </div>
            <sec:authorize access="hasRole('ADVISER')">
                <div class="modal-footer">
                    <button id="toDraft" type="button" class="btn btn-primary" onclick="statusToDraft()">
                        <span class="fa fa-check"></span>
                        to draft
                    </button>
                    <button id="toSubmit" type="button" class="btn btn-primary" onclick="statusToSubmitted()">
                        <span class="fa fa-check"></span>
                        to submit
                    </button>
                    <button id="toApprove" type="button" class="btn btn-primary" onclick="statusToApproved()">
                        <span class="fa fa-check"></span>
                        to approve
                    </button>
                    <button id="toDenied" type="button" class="btn btn-primary" onclick="statusToDenied()">
                        <span class="fa fa-check"></span>
                        to denied
                    </button>
                </div>
            </sec:authorize>
        </div>
    </div>
</div>
<jsp:include page="fragments/footer.jsp"/>
</body>
<jsp:include page="fragments/i18n.jsp">
    <jsp:param name="page" value="claim"/>
</jsp:include>
</html>