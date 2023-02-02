const url = "api/parts/search/getPartByPartNumber?pn=909700"

// https://stackoverflow.com/a/5064235/548473

const ctx = {
    ajaxUrl: url,
    updateTable: function (url) {
        $.ajax({
            type: "GET",
            url: url,
            "dataSrc": function (json) {
                let partNumber = json.partNumber;
                let description = json.description;
                let price = json.price;
                document.getElementById("partNumber").value = partNumber;
                document.getElementById("partDescription").value = description;
                document.getElementById("partPrice").value = price;
                return json;
            }
        }).done(updateTableByData);
    }
};



/*
$(function () {
    makeEditable1({
            "columns": [
                {
                    "data": "partNumber"
                },
                {
                    "data": "description"
                },
                {
                    "data": "price"
                }
            ],
            "createdRow": function (row, data, dataIndex) {
                /!*        $(row).attr("data-meal-excess", data.excess);*!/
            }
        }
    )
});

function makeEditable1(datatableOpts) {
    let result;
    ctx.datatableApi = $("#datatable").DataTable(
        // https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN

        $.extend(true, datatableOpts,
            {
                "ajax": {
                    "processing": true,
                    "serverSide": true,
                    "url": url,
                    "dataSrc": function(json){
                        let partNumber = json.partNumber;
                        let description = json.description;
                        let price = json.price;
                        result = [partNumber,description,price]
                        alert(partNumber);
                        return result;
                    },
                    "data": result
                },

                "paging": true,
                "info": true
                /!*"language": {
                    "search": i18n["common.search"]
                }*!/
            }
        ));
}
function updateTableByData(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}*/
