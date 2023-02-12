const ajaxClaimUrl = "rest/claims/";
const ajaxAdviserClaimUrl = "rest/adviser/claims/";
let form;

const ctx = {
    datatableApi: [],
    ajaxUrl: ajaxClaimUrl, updateTable: function () {
        $.ajax({
            type: "GET", url: ajaxClaimUrl //+ "filter",
            // data: $("#filter").serialize()
        }).done(updateTableByData);
    }
};

let tables = [[$("#draftTable"), {
    "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"},
        {"render": renderDeleteBtn, "defaultContent": "", "orderable": false},
        {"render": renderEditBtn, "defaultContent": "", "orderable": false}
    ], "order": [[3, "desc"]]
}, 0],
    [$("#inReviewTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"},
            {"render": renderDeleteBtn, "defaultContent": "", "orderable": false},
            {"render": renderEditBtn, "defaultContent": "", "orderable": false},
            {"render": renderViewBtn, "defaultContent": "", "orderable": false}
        ], "order": [[3, "desc"]]
    }, 1],
    [$("#submittedTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderViewBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 2],
    [$("#approvedTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {"data": "approvedAmount"}, {
            "render": renderViewBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 3],
    [$("#deniedTable"), {
        "columns": [{"data": "dealer",}, {"data": "dealerRO"}, {"data": "esn"}, {"data": "failureDate"}, {"data": "claimAmount"}, {
            "render": renderViewBtn, "defaultContent": "", "orderable": false
        }], "order": [[3, "desc"]]
    }, 4]]


$(function () {
    tables.forEach(table => makeEditable(table[0], table[1], table[2]));
});

function makeEditable(dataTable, datatableOpts, status) {
    ctx.datatableApi[status] = dataTable.DataTable(// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
        $.extend(true, datatableOpts, {
            "processing": true,
            "serverSide": true,
            "paging": true,
            "info": true,
            "pageLength": 10,
            "searching": false,
            "lengthMenu": [[10, 25, 50], [10, 25, 50]],
            "ajax": {
                "url": ajaxClaimUrl, "data": {
                    status: status
                }, "dataSrc": "data"
            }
        }));
}



function updateRow(id) {
    form.find(":input").val("");
    $("#asDraftBtn").hide();
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ctx.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(value).attr("readonly", false).attr("disabled", false);
        });
        $('#editRow').modal();
    });
}

function viewRow(id) {
    form.find("").val("");
    $("#asDraftBtn").hide();
    $("#modalTitle").html(i18n["viewTitle"]);
    $.get(ctx.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(value).attr("readonly", true).attr("disabled", true);
        });
        $('#editRow').modal();
    });
}

function save() {
    $("#status").val('IN_REVIEW')
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: JSON.stringify(getFormData(form)),
        contentType: "application/json",
        dataType: "json"
    }).done(function () {

        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("common.saved");
    });
}

function saveAsDraft() {
    $("#status").val('DRAFT')
    $.ajax({
        type: "POST",
        url: ctx.ajaxUrl,
        data: JSON.stringify(getFormData(form)),
        contentType: "application/json",
        dataType: "json"
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("Drafted");
    });
}

function statusToSubmitted() {
    let id = document.getElementById("id").value;
    $.ajax({
        type: "PATCH",
        url: ajaxAdviserClaimUrl + "submitted/" + id,
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("Submitted");
    });
}

function statusToApproved() {
    let id = document.getElementById("id").value;
    let approveAmount = prompt(" approved amount:")
    $.ajax({
            type: "PATCH",
            url: ajaxAdviserClaimUrl + "approved/" + id,
            data: {approveAmount: approveAmount}
        }
    ).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("Approved");
    });
}

function statusToDenied() {
    let id = document.getElementById("id").value;
    let reason = prompt(" Reason of deny:")
    $.ajax({
            type: "PATCH",
            url: ajaxAdviserClaimUrl + "denied/" + id,
            data: {reason: reason}
        }
    ).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("Denied");
    });
}

function statusToDraft() {
    let id = document.getElementById("id").value;
    let reason = prompt(" Reason of turn back:")
    $.ajax({
            type: "PATCH",
            url: ajaxAdviserClaimUrl + "denied/" + id,
            data: {reason: reason}
        }
    ).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("Denied");
    });
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

