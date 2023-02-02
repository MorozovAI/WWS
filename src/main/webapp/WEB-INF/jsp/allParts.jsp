<%--https://roytuts.com/bootstrap-ajax-spring-boot-pagination/--%>
<!DOCTYPE html>
<html lang="en">
<jsp:include page="fragments/headTag.jsp"/>
<body>
<script type="text/javascript" src="../../resources/js/common.js" defer></script>
<script type="text/javascript" src="../../resources/js/parts.js" defer></script>
<jsp:include page="fragments/bodyHeader.jsp"/>
<div class="jumbotron pt-4">
    <div class="container">
        <form class="row g-3">
            <div class="col-auto">
                <label for="inputPartNumber" class="sr-only">PN</label>
                <input type="text" class="form-control" id="inputPartNumber" placeholder="Enter part number">
            </div>
            <div class="col-auto">
                <button type="submit" class="btn btn-primary mb-3" onclick="search()">Search part</button>
            </div>
        </form>

        <table id="partsTable" class="table table-striped">
            <thead>
            <tr>
                <th>Part Number</th>
                <th>Description</th>
                <th>Price</th>
            </tr>
            </thead>
            <tbody>
            </tbody>
        </table>

        <ul class="pagination justify-content-center" style="margin:20px 0; cursor: pointer;">
        </ul>
    </div>
</div>
<script>
    function search() {
        //console.log('startPage: ' +startPage);
        /**
         * get data from Backend's REST API
         */
        let partNumber = document.getElementById("inputPartNumber").value;
        $.ajax({
            type: "GET",
            url: "api/parts/search/getPartByPartNumber?pn=" + partNumber,

            success: function (response) {
                $('#partsTable tbody').empty();
                // add table rows
                $.each(response, () => {
                    let partsRow = '<tr>' +
                        '<td>' + partNumber + '</td>' +
                        '<td>' + description + '</td>' +
                        '<td>' + price + '</td>' +
                        '</tr>';
                    $('#partsTable tbody').append(partsRow);
                });
            },
            error: function (e) {
                alert("ERROR: ", e);
            }
        });
    }
</script>

<%--<script src="../../resources/js/table-pagination.js"></script>--%>
</body>
</html>