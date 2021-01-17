$(document).ready(function() {
    getMakes();
})

function getMakes() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/makes',
        dataType: 'json'
    }).done(function (data) {
        $('#make').append('<option disabled>Выберите</option>');
        for (let i = 0; i < data.length; i++) {
            $('#make').append('<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>')
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

function get(fromServlet, bySelectedId, forSelect) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/' + fromServlet,
        data: {id : $('#' + bySelectedId + ' option:selected').val()},
        dataType: 'json'
    }).done(function (data) {
        $('#' + forSelect).empty();
        $('#' + forSelect).append('<option disabled>Выберите</option>');
        for (let i = 0; i < data.length; i++) {
            $('#' + forSelect).append('<option value="' + data[i]["id"] + '">' + data[i]["name"] + '</option>')
        }
    })
}

function add() {
    if (validate()) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/car_sale/add',
            data: {description : $('#description').val(),
                    makeId : $('#make option:selected').val(),
                    modelId : $('#model option:selected').val(),
                    bodyId : $('#body option:selected').val(),
                    engineId : $('#engine option:selected').val(),
                    transmissionId : $('#transmission option:selected').val()},
            dataType: 'json'
        })
    }
}