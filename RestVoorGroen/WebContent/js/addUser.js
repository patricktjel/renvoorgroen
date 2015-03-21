function addUser() {
  bootbox.dialog({
    title: "Add User",
    message: '<div class="row">  ' +
      '<div class="col-md-12"> ' +
      '<form class="form-horizontal"> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="username">Username</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="username" name="username" type="text" placeholder="Username" class="form-control input-md"> ' +
      '</div> ' +
      '</div> ' +
      '<form class="form-horizontal"> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="password">Password</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="password" name="password" type="password" placeholder="Password" class="form-control input-md"> ' +
      '</div> ' +
      '</div> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="fitbitId">Fitbit Id</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="id" name="fitbitId" type="text" placeholder="Fitbit ID" class="form-control input-md"> ' +
      '<span class="help-block">You can get this ID from our Android application</span> </div> ' +
      '</div> ' +
      '</div> ' +
      '<form class="form-horizontal"> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="name">Name</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="name" name="name" type="text" placeholder="Name" class="form-control input-md"> ' +
      '</div> ' +
      '</div> ' +
      '</form> </div>  </div>',
    buttons: {
      cancel: {
        label: "Cancel",
        className: "btn-primary",
      },

      success: {
        label: "Save",
        className: "btn-success",
        callback: function () {
          var username = $('#username').val();
          var password = $('#password').val();
          var id = $('#id').val();
          var name = $('#name').val();
          var data = {
            "fitbitid": id,
            "inlognaam": username,
            "wachtwoord": password,
            "naam": name
          };
          $.ajax({
            type: 'POST',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/user/add",
          })
            .done(function (data) {
              console.log(data);
              location.reload();
            })
            .fail(function (jqXHR, textStatus) {
              console.log(jqXHR);
              location.reload();
            });
        }
      }

    }
  })
}

$(document).ready(function () {
  $('#table-users tbody tr').remove();

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/user/get",
  })
    .done(function (data) {
      console.log(data);
      var tableRow = '';
      data.forEach(function (user) {
        tableRow += '<tr class="clickable-row" data-fitbit="' + user.fitbitid + '">' +
          '<td>' + user.id + '</td>' +
          '<td>' + user.inlognaam + '</td>' +
          '<td>' + user.naam + '</td>' +
          '<td>' + user.fitbitid + '</td>' +
          '<td><button onclick="deleteUser(' + user.id + ');\" type="button" class="btn btn-success btn-sm pull-right delete-user-btn">Delete</button> </td>' +
          '</tr>';
        $('#table-users tbody').append(tableRow);
        tableRow = "";
      });
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    });

  $('#table-users').on('click', 'tr', function () {
    var fitbit = $(this).data('fitbit');
    // alert('You clicked ' + row);
    $.ajax({
      type: 'GET',
      url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/data/get?id=" + fitbit,
    })
      .done(function (data) {
        console.log(data);
        var distance = data.distance;
        var steps = data.steps;
        var stairs = data.floors;
        bootbox.dialog({
          message: '<div class="container" style="margin-bottom:40px;">' +
            '<div class=".col-xs-4 pull-left"><h2><img src="image/footsteps.png" height="200px" alt="footsteps"><br>Steps: ' + steps + '</h2></div>' +
            '<div class=".col-xs-4 pull-right"><h2><img src="image/running.png" height="200px" alt="Stairs"><br>Distance: ' + distance + 'km </h2></div></div>' +
            '<div class=".col-xs-6 col-md-offset-4"><h2><img src="image/stair.png" height="160px" alt="running man"><br>Stairs: ' + stairs + '</h2></div>' +
            '</div>',
          buttons: {
            cancel: {
              label: "Ok",
              className: "btn-primary",
            }
          }
        });
      })
      .fail(function (jqXHR, textStatus) {
        console.log(jqXHR);
      });
  });
});

function deleteUser(id) {
  $.ajax({
    type: 'DELETE',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/user/delete?id=" + id,
  })
    .done(function (data) {
      console.log(data);
      location.reload();
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    });
}