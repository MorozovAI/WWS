const profileAjaxUrl = "rest/profile";

$(function () {
    form = $('#detailsForm');
    form.find(":input").val("");

    $.get(profileAjaxUrl, function (data) {
        document.getElementById("dealer").innerText=(data.dealer.dealerCode + "-" + data.dealer.dealerName);
        $.each(data, function (key, value) {
            form.find("[name='" + key + "']").val(value).attr("readonly", false).attr("disabled", false);
        });
    });
})

function save() {
    $.ajax({
        type: "PUT",
        url: profileAjaxUrl,
        data: JSON.stringify(getFormData(form)),
        contentType: "application/json",
        dataType: "json"
    }).done(function () {
        successNoty("common.saved");
        if (document.referrer.length === 0) document.location.href = "/claims";
        else document.location.href = document.referrer;
    });
}
