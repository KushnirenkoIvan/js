'use strict';

function getEntity() {
    $.ajax({
        type: 'GET',
        url: 'get/' + $('#getEntity').val(),
        async: true,
        success: function (result) {
            processList(result)
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function createEntity() {
    var entity = $('#createEntity').val();
    $.ajax({
        type: 'POST',
        url: 'add',
        contentType: 'application/json; charset=utf-8',
        data: entity,
        dataType: 'json',
        async: true,
        success: function (result) {
            alert('New entity: ' + JSON.stringify(result) + '\n was created.');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function updateEntity() {
    var entity = $('#updateEntity').val();
    $.ajax({
        type: 'PUT',
        url: 'update',
        contentType: 'application/json; charset=utf-8',
        data: entity,
        dataType: 'json',
        async: true,
        success: function (result) {
            alert('Entity: ' + JSON.stringify(result) + '\n was updated.');
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function deleteEntity() {
    $.ajax({
        type: 'DELETE',
        url: 'delete/' + $('#deleteId').val(),
        async: true,
        success: function (result) {
            alert('Deleted entity: ' + JSON.stringify(result));
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function getAll() {
    $.ajax({
        type: 'GET',
        url: 'getAll',
        async: true,
        success: function (result) {
            processList(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function getStat() {
    $.ajax({
        type: 'GET',
        url: 'getStat',
        async: true,
        success: function (result) {
            processList(result);
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}

function processList(json_lst) {
    var resp;
    $('#response').html('');
    console.log(json_lst);
    $.each(json_lst, function (index, value) {
        resp += '<p>' + JSON.stringify(value) + '</p>';
    });
    $('#response').html('<fieldset><legend>response</legend>' + resp + '</fieldset>');
}
