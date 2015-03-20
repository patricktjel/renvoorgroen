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
            })
            .fail(function (jqXHR, textStatus) {
              console.log(jqXHR);
              bootbox.alert("<h1>Error making a new user.</h1>" + jqXHR.statusText);
            });
        }
      }

    }
  })
}