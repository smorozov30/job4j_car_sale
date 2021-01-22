$(document).ready(function() {
    load("all");
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

function load(condition) {
    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/car_sale/ads',
        data: {filter : condition},
        dataType: 'json'
    }).done(function (data) {
        $("tbody").empty();
        $("#filter").empty();

        let filterText = '<option value="all">Все объявления</option>' +
            '<option value="lastDay">За последний день</option>' +
            '<option value="onlyPhoto">Только с фото</option>' +
            '<optgroup label="Марка" style="background: #f0f0f0;">';

        for (let i = 0; i < data.length; i++) {
            let ads = data[i];
            let car = data[i]["car"];

            let make = '<option style="background: #ffffff;" value="' + car["make"]["name"] + '">' + car["make"]["name"] + '</option>';
            if (!filterText.includes(make)) {
                filterText += make;
            }

            let categories = '<strong>Марка: </strong>' + car["make"]["name"]
                + '<br>' + '<strong>Модель: </strong>' + car["model"]["name"]
                + '<br>' + '<strong>Кузов: </strong>' + car["body"]["name"]
                + '<br>' + '<strong>Двигатель: </strong>' + car["engine"]["name"]
                + '<br>' + '<strong>Коробка: </strong>' + car["transmission"]["name"];

            let done = Boolean(data[i]["sold"]) ? "Продано" : "Не продано";

            let photo = ads["photo"].length === 0 ? "no_photo.jpg" : ads["photo"][0];

            $('tbody').append('<tr>'
                + '<td>' + ads["id"] + '</td>'
                + '<td>' + ads["description"] + '</td>'
                + '<td><img src="images/' + photo + '" width="200"></td>'
                + '<td>' + categories + '</td>'
                + '<td>' + ads["created"] + '</td>'
                + '<td>' + ads["user"]["name"] + '</td>'
                + '<td>' + done + '</td>'
                + '</tr>'
            );
        }
        $('#filter').append(filterText + '</optgroup>');
        $('select[name="filter"] option[value="' + condition + '"]').prop('selected', true);
    });
}