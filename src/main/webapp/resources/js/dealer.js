let dealerId = new URL(document.documentURI).searchParams.get("dealerId");
const dealersUsersAjaxUrl = "rest/admin/dealers/" + dealerId + "/users";
const dealerAjaxUrl = "rest/admin/dealers/" + dealerId;
const userAjaxUrl = "rest/admin/users/"
const ctx = {
    ajaxUrl: dealersUsersAjaxUrl,
    updateTable: function () {
        $.ajax({
            type: "GET",
            url: dealersUsersAjaxUrl
        }).done(updateTableByData);
    }
}

$($.get(dealerAjaxUrl, function (data) {
    document.getElementById("dealerInfo").innerText = (data.dealerCode + "-" + data.dealerName);
}));


// $(document).ready(function () {
$(function () {
    makeEditable({
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "dealer.dealerCode"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href='mailto:" + data + "'>" + data + "</a>";
                    }
                    return data;
                }
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<input type='checkbox' " + (data ? "checked" : "") + " onclick='enable($(this)," + row.id + ");'/>";
                    }
                    return data;
                }
            },
            {
                "data": "registered",
                "render": function (date, type, row) {
                    if (type === "display") {
                        return date.substring(0, 10);
                    }
                    return date;
                }
            },
            {
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            }
        ],
        "order": [
            [
                1,
                "asc"
            ]
        ],
        "createdRow": function (row, data, dataIndex) {
            if (!data.enabled) {
                $(row).attr("data-enabled", false);
            }
        }
    });
});

function makeEditable(datatableOpts) {
    ctx.datatableApi = $("#datatable").DataTable(// https://api.jquery.com/jquery.extend/#jQuery-extend-deep-target-object1-objectN
        $.extend(true, datatableOpts, {
            "paging": true,
            "info": true,
            "pageLength": 10,
            "searching": true,
            "lengthMenu": [[10, 25, 50], [10, 25, 50]],
            "ajax": {
                "url": ctx.ajaxUrl,
                "dataSrc": ""
            }
        }));
}

function save() {
    let id = document.getElementById("id").value;
    let method = id !== "" ? "PUT" : "POST";
    $.ajax({
        type: id !== "" ? "PUT" : "POST",
        url: id !== "" ? userAjaxUrl+id : dealersUsersAjaxUrl,
        data: JSON.stringify(getFormData(form)),
        contentType: "application/json",
        dataType: "json"
    }).done(function () {
        $("#editRow").modal("hide");
        ctx.updateTable();
        successNoty("common.saved");
    });
}

function updateRow(id) {
    form.find(":input").val("");
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(userAjaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(value).attr("readonly", false).attr("disabled", false);
        });
        $('#editRow').modal();
    });
}

function updateTableByData(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}