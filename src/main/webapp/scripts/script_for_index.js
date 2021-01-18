$(document).ready(function() {
    load();
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
            $('tbody').append('<tr>'
                + '<th>' + ads["id"] + '</th>'
                + '<th>' + ads["description"] + '</th>'
                + '<th>' + ads["photo"][0] + '</th>'
                + '<th>' + car["make"]["name"] + '</th>'
                + '<th>' + '15.02.2021' + '</th>'
                + '<th>' + 'Sergey' + '</th>'
                + '<th>' + 'NOT' + '</th>'
            );
        }
    });
}
// [
// {"id":1,
// "description":"descr 1",
// "sold":false,
// "car":{
    // "make":{"name":"AUDI"},
    // "model":{"name":"A6"},
    // "body":{"name":"Body 3"},
    // "engine":{"name":"Engine 3"},
    // "transmission":{"name":"Transmission 3"}},
// "photo":["13387.jpg"]},
//
// {"id":2,
// "description":"descr 2",
// "sold":false,
// "car":{
    // "make":{"name":"VW"},
    // "model":{"name":"POLO"},
    // "body":{"name":"Body 2"},
    // "engine":{"name":"Engine 1"},
    // "transmission":{"name":"Transmission 2"}},
// "photo":["duckling.jpg"]}
// ]