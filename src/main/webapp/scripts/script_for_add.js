$(document).ready(function() {
    getMakes();
    currentUser();
})

function getMakes() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/makes',
        dataType: 'json'
    }).done(function (data) {
        $('#make').append('<option selected disabled>Выберите</option>');
        for (let i = 0; i < data.length; i++) {
            let name = data[i]["name"];
            $('#make').append('<option value="' + name + '">' + name + '</option>')
        }
    })
}

function getModels() {
    $('#body').empty();
    $('#engine').empty();
    $('#transmission').empty();
    get('models', 'make', 'model');
}

function getCarBodies() {
    $('#engine').empty();
    $('#transmission').empty();
    get('bodies', 'model', 'body');
}

function getEngines() {
    $('#transmission').empty();
    get('engines', 'model', 'engine');
}

function getTransmissions() {
    get('transmissions', 'model', 'transmission');
}

function get(fromServlet, bySelectedName, forSelect) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/' + fromServlet,
        data: {name : $('#' + bySelectedName + ' option:selected').val()},
        dataType: 'json'
    }).done(function (data) {
        $('#' + forSelect).empty();
        $('#' + forSelect).append('<option selected disabled>Выберите</option>');
        for (let i = 0; i < data.length; i++) {
            let name = data[i]["name"];
            $('#' + forSelect).append('<option value="' + name + '">' + name + '</option>')
        }
    })
}

function add() {
    let form = $('#addForm')[0];
    let data = new FormData(form);
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/car_sale/add.do',
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        cache: false,
        data: data
    }).done(
        window.location.href = "http://localhost:8080/car_sale/index.html"
    );
}
function currentUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/user'
    }).done(function(data) {
        $('a').replaceWith('<a id="enter" class="nav-link" href="http://localhost:8080/car_sale/login.do">' + data["name"] + '</a>');
    })
}