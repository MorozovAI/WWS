const ajaxClaimUrl = "rest/claims/";

const ctx = {
    datatableApi: [],
    ajaxUrl: ajaxClaimUrl, updateTable: function () {
        $.ajax({
            type: "GET", url: ajaxClaimUrl //+ "filter",
            // data: $("#filter").serialize()
        }).done(updateTableByData);
    }
};

let tables = [
    [$("#draftTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderDeleteBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 0],
    [$("#inReviewTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderDeleteBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 1],
    [$("#submittedTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderDeleteBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 2],
    [$("#approvedTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderDeleteBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 3],
    [$("#deniedTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderDeleteBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 4]
]


$(function () {
    tables.forEach(table =>
        makeEditable(table[0], table[1], table[2]));
});

function makeEditable(dataTable, datatableOpts, status) {
    ctx.datatableApi[status] =
        dataTable.DataTable(// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
            $.extend(true, datatableOpts, {
                "processing": true,
                "serverSide": true,
                "paging": true,
                "info": true,
                "pageLength": 10,
                "searching": false,
                "lengthMenu": [[10, 25, 50], [10, 25, 50]],
                "ajax": {
                    "url": ajaxClaimUrl,
                    "data": {
                        status: status
                    },
                    "dataSrc": "data"
                }
            }));
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function renderDeleteBtn(data, type, row) {
    if (type === "display") {
        return "<a onclick='deleteRow(" + row.id + ");'><span class='fa fa-remove'></span></a>";
    }
}

function deleteRow(id) {
    if (confirm(i18n['common.confirm'])) {
        $.ajax({
            url: ctx.ajaxUrl + id, type: "DELETE"
        }).done(function () {
            ctx.updateTable();
            // successNoty("common.deleted");
        });
    }
}

function updateTableByData(data) {
    ctx.datatableApi.forEach(api => api.clear().rows.add(data).draw());
}
