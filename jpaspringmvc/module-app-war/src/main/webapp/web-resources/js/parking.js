$(document).ready(function() {
    $('#buttonGetParkedCars').on('click', getParkedCars);
//    $('#buttonGetParkedCars').on('click', parkCar);
});

function unparkCar(id) {

}

function parkCar(car) {
    var frm = $('#newCarForm');
    var frmJSON = frm.serializeArray();
//                var data = JSON.serialize(frm);
    var data = frm.serialize(frm); // for http GET not POST

    $.ajax({
        url: "apache-cxf-servlet/restServicePort/parkingLot/parkCar",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        type: frm.attr('method'),
//        data: frmJSON,
//                            data: JSON.stringify(data),
        data: frmJSON,
        success: function(msg) {
            if (msg !== null) {
                alert("success" + data);
                //                            return msg.URL;
            }
            return data;
        },
        error: function(data) {
            alert("error" + data);
        }
    });
    return false;
}

function setHtmlParkedCarsList(parkedCars) {

}

function getCarsInLot() {
    var url = 'http://localhost:8088/app-war/apache-cxf-servlet/rest/restServicePort/parkingLot/listCarsInLot';
    $.ajax({
        url: url,
//        dataType: "json",
//        contentType: "application/json;charset=utf-8",
        type: 'GET',
//        data: '',
        success: function(msg) {
            if (msg !== null) {
                alert("success" + msg);
                //                            return msg.URL;
            }
            return msg;
        },
        error: function(msg) {
            alert("error" + msg);
            return msg;
        }
    }).done(function() {
        console.debug("done");
    });
    return false;
}


function getCarById() {
    var frm = $('#newCarForm');
//                var data = JSON.serialize(frm);
    var data = frm.serialize(frm);

    alert("asdfasfd");

    alert($('#newCarForm').serialize);
    alert("dd" + JSON.stringify(data));
    alert("dd" + JSON.stringify(data));
    $.ajax({
        url: "getCarById",
        dataType: "json",
        contentType: "application/json;charset=utf-8",
        type: frm.attr('method'),
        //                    data: JSON.stringify(data),
        data: frm,
        success: function(msg) {
            if (msg !== null) {
                alert("success" + data);
                //                            return msg.URL;
            }
        },
        error: function(data) {
            alert("error" + data);
        }
    });
    return false;
}


function displayCars(data) {
    $.each(data.items, function(i, item) {
        $("<img/>").attr("src", item.media.m).appendTo("#images");
        if (i === 3)
            return false;
    });
    //                
    $.each(data, function() {
        var tbl_row = "";
        $.each(this, function(k, v) {
            tbl_row += "<td>" + v + "</td>";
        });
        tbl_body += "<tr>" + tbl_row + "</tr>";
    });
    $("#target_table_id tbody").html(tbl_body);
}
