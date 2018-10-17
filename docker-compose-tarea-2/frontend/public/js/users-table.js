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
    });
    
    // process the form
    $('form').submit(function(event) {
        event.preventDefault(); 
        var formData = {
            'name' : $('input[name=name]').val(),
            'email' : $('input[name=email]').val(),
            'phone' : $('input[name=phone]').val()
        };

        $.ajax({
            type: 'POST', 
            url: baseURL+'/users', 
            data: JSON.stringify(formData),
            contentType: 'application/json; charset=utf-8', 
            dataType: 'json',
            encode: true,
            success: function(data){
                window.location = "/"
            },
            error: function(){
               alert(data);
            }
        })        
       
    });
} );