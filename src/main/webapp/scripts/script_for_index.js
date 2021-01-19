$(document).ready(function() {
    load();
    currentUser();
})

function currentUser() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/user'
    }).done(function(data) {
        $('#enter').replaceWith('<a id="enter" class="nav-link" href="http://localhost:8080/car_sale/login.do">' + data["name"] + '</a>');
    })
}

function load() {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/ads',
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

            let done = Boolean(data[i]["sold"]) ? "Продано"
                : "Не продано";

            $('tbody').append('<tr>'
                + '<td>' + ads["id"] + '</td>'
                + '<td>' + ads["description"] + '</td>'
                + '<td><img src="images/' + ads["photo"][0] + '" width="200"></td>'
                + '<td>' + categories + '</td>'
                + '<td>' + ads["created"] + '</td>'
                + '<td>' + ads["user"]["name"] + '</td>'
                + '<td>' + done + '</td>'
                + '</tr>'
            );
        }
    });
}