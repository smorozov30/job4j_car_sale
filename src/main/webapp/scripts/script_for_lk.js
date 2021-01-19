$(document).ready(function() {
    load();
    currentUser();
})

function currentUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/user'
    }).done(function(data) {
        $('h4').replaceWith('<h4>Объявления пользователя: ' + data["name"] + '</h4>');
    })
}

function load() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/lk.do',
        dataType: 'json'
    }).done(function (data) {
        $("tbody").empty();

        for (let i = 0; i < data.length; i++) {
            let ads = data[i];
            let car = data[i]["car"];

            let categories = '<strong>Марка: </strong>' + car["make"]["name"]
                + '<br>' + '<strong>Модель: </strong>' + car["model"]["name"]
                + '<br>' + '<strong>Кузов: </strong>' + car["body"]["name"]
                + '<br>' + '<strong>Двигатель: </strong>' + car["engine"]["name"]
                + '<br>' + '<strong>Коробка: </strong>' + car["transmission"]["name"];

            let done = Boolean(data[i]["sold"]) ? '<input type="checkbox" checked onclick="done($(this))" id="' + ads["id"] + '">'
                : '<input type="checkbox" onclick="done($(this))" id="' + ads["id"] + '">';

            $('tbody').append('<tr>'
                + '<td>' + ads["id"] + '</td>'
                + '<td>' + ads["description"] + '</td>'
                + '<td><img src="images/' + ads["photo"][0] + '" width="200"></td>'
                + '<td>' + categories + '</td>'
                + '<td>' + ads["created"] + '</td>'
                + '<td>' + done + '</td>'
                + '</tr>'
            );
        }
    });
}

function done(checkbox) {
    $.ajax({
        type: 'POST',
        url: 'http://localhost:8080/car_sale/sold',
        data: {id : checkbox.attr('id')}
    }).done(function() {
        load();
    });

}