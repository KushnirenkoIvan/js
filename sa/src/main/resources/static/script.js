'use strict';

function restDelete() {
    $.ajax({
        type: 'DELETE',
        url: 'delete/' + $("#deleteVal").val(),
        dataType: 'json',
        async: true,
        success: function (result) {
            alert('Deleted entity: ' + JSON.stringify(result));
        },
        error: function (jqXHR, textStatus, errorThrown) {
            alert(jqXHR.status + ' ' + jqXHR.responseText);
        }
    });
}