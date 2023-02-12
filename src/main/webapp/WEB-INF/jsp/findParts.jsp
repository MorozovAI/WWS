<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="../../resources/js/common.js" defer></script>
<script type="text/javascript" src="../../resources/js/parts.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>

<div class="jumbotron pt-4">
    <div class="container">
        <h3 class="text-center"><spring:message code="meal.title"/></h3>
        <%--https://getbootstrap.com/docs/4.0/components/card/--%>
        <br/>

        <input type="text" class="form-control" id="pn" name="p0n"
               placeholder="enter part number">

        <button id="getPN" onclick="search()">Search</button>

        <table class="table table-striped" id="datatable">
            <thead>
            <tr>
                <th>PartNumber</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            <tr>
                <th><p id="partNumber"></p></th>
                <th><p id="partDescription"></p></th>
                <th><p id="partPrice"></p></th>
            </tr>
            </thead>
        </table>

        <script>
            function
            search() {
                let partNumber = document.getElementById("inputPartNumber").value;
                $.get("api/parts/search/getPartByPartNumber?pn=" + partNumber, success, "json");
            };

            function success(fData) {
                $('#partsTable tbody').empty();
                // add table rows
                let partsRow = '<tr>' +
                    '<td>' + parts.partNumber + '</td>' +
                    '<td>' + parts.description + '</td>' +
                    '<td>' + parts.price + '</td>' +
                    '</tr>';
                $('#partsTable tbody').append(partsRow);
            }
            ;
        </script>
    </div>


</div>
</body>

</html>