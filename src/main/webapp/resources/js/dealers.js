const dealerAjaxUrl = "rest/admin/dealers/";
let form;
const ctx = {
    ajaxUrl: dealerAjaxUrl,
    updateTable: function () {
        $.get(dealerAjaxUrl, updateTableByData);
    }
}

// $(document).ready(function () {
$(function () {
    makeEditable({
        "columns": [
            {
                "data": "dealerCode"
            },
            {
                "data": "dealerName",
                "render": function (data, type, row) {
                    if (type === "display") {
                        return "<a href='dealer/?dealerId=" + row.id + "'>" + data + "</a>";
                    }
                    return data;
                }
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
                "orderable": false,
                "defaultContent": "",
                "render": renderEditBtn
            }
        ],
        "order": [
            [
                0,
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
    alert(JSON.stringify(getFormData(form)));
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

function updateRow(id) {
    form.find(":input").val("");
    $("#modalTitle").html(i18n["editTitle"]);
    $.get(ctx.ajaxUrl + id, function (data) {
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(value).attr("readonly", false).attr("disabled", false);
        });
        $('#editRow').modal();
    });
}

function enable(chkbox, id) {
    var enabled = chkbox.is(":checked");
//  https://stackoverflow.com/a/22213543/548473
    $.ajax({
        url: ctx.ajaxUrl + id,
        type: "PATCH",
        data: "enabled=" + enabled
    }).done(function () {
        chkbox.closest("tr").attr("data-enabled", enabled);
        successNoty(enabled ? "common.enabled" : "common.disabled");
    }).fail(function () {
        $(chkbox).prop("checked", !enabled);
    });
}

function updateTableByData(data) {
    ctx.datatableApi.clear().rows.add(data).draw();
}