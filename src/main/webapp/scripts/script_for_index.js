$(document).ready(function() {
    currentUser();
})

function currentUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/user'
    }).done(function(data) {
        $('a').replaceWith('<a id="enter" class="nav-link" href="http://localhost:8080/car_sale/login.do">' + data["name"] + '</a>');
    })
}