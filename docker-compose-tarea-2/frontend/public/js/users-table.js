var baseURL = "http://localhost:8080/api"
$(document).ready(function() {
    $('#users').DataTable( {
        ajax: {
            "url": baseURL+"/users",
            "dataSrc": "",
        },
        
        columns: [
            { "data": "id" },
            { "data": "name" },
            { "data": "email" },
            { "data": "phone" }            
        ]
    } );
} );