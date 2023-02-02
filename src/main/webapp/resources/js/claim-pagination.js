const ajaxClaimUrl = "rest/claims";

$(document).ready(function () {
    $('#inReviewTable').DataTable({
        "processing": true,
        "serverSide": true,
        "paging": true,
        "pageLength": 10,
        "searching": false,
        "lengthMenu": [[10, 25, 50], [10, 25, 50]],
        "ajax": {
            url: ajaxClaimUrl,
            data: {
                status:1
            }
        },
        columns: [
            {"data": "dealer",},
            {"data": "dealerRO"},
            {"data": "esn"},
            {"data": "failureDate"},
            {"data": "claimAmount"}
        ],
        "order": [
            [
                3,
                "desc"
            ]
        ],
    });
});

$(document).ready(function () {
    $('#draftTable').DataTable({
        "processing": true,
        "serverSide": true,
        "paging": true,
        "pageLength": 10,
        "searching": false,
        "lengthMenu": [[10, 25, 50], [10, 25, 50]],
        "ajax": {
            url: ajaxClaimUrl,
            data: {
                status: 0
            }
        },
        columns: [
            {"data": "dealer",},
            {"data": "dealerRO"},
            {"data": "esn"},
            {"data": "failureDate"},
            {"data": "claimAmount"}
        ],
        "order": [
            [
                3,
                "desc"
            ]
        ],
    });
});

$(document).ready(function () {
    $('#submittedTable').DataTable({
        "processing": true,
        "serverSide": true,
        "paging": true,
        "pageLength": 10,
        "searching": false,
        "lengthMenu": [[10, 25, 50], [10, 25, 50]],
        "ajax": {
            url: ajaxClaimUrl,
            data: {
                status: 2
            }
        },
        columns: [
            {"data": "dealer",},
            {"data": "dealerRO"},
            {"data": "esn"},
            {"data": "failureDate"},
            {"data": "claimAmount"}
        ],
        "order": [
            [
                3,
                "desc"
            ]
        ],
    });
});

$(document).ready(function () {
    $('#approvedTable').DataTable({
        "processing": true,
        "serverSide": true,
        "paging": true,
        "pageLength": 10,
        "searching": false,
        "lengthMenu": [[10, 25, 50], [10, 25, 50]],
        "ajax": {
            url: ajaxClaimUrl,
            data: {
                status: 3
            }


        },
        columns: [
            {"data": "dealer",},
            {"data": "dealerRO"},
            {"data": "esn"},
            {"data": "failureDate"},
            {"data": "claimAmount"}
        ],
        "order": [
            [
                3,
                "desc"
            ]
        ],
    });
});

$(document).ready(function () {
    $('#deniedTable').DataTable({
        "processing": true,
        "serverSide": true,
        "paging": true,
        "pageLength": 10,
        "searching": false,
        "lengthMenu": [[10, 25, 50], [10, 25, 50]],
        "ajax": {
            url: ajaxClaimUrl,
            data: {
                status: 4
            }
        },
        columns: [
            {"data": "dealer",},
            {"data": "dealerRO"},
            {"data": "esn"},
            {"data": "failureDate"},
            {"data": "claimAmount"}
        ],
        "order": [
            [
                3,
                "desc"
            ]
        ],
    });
});
