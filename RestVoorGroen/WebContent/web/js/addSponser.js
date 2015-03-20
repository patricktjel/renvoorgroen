function addSponsor() {
  bootbox.dialog({
    title: "Add Milestone",
    message: '<div class="row">  ' +
      '<div class="col-md-12"> ' +
      '<form class="form-horizontal"> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="name">Name</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="name" name="name" type="text" placeholder="Name" class="form-control input-md" required> ' +
      '</div> ' +
      '</div> ' +
      '<form class="form-horizontal"> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="email">Email</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="email" name="email" type="email" placeholder="Email Address" class="form-control input-md" required> ' +
      '</div> ' +
      '</div> ' +
      '</form> </div>  </div>',
    buttons: {
      cancel: {
        label: "Cancel",
        className: "btn-primary"
      },

      success: {
        label: "Save",
        className: "btn-success",
        callback: function () {

          var naam = $('#name').val();
          var emailadres = $('#email').val();

          var data = {
            "naam": naam,
            "emailadres": emailadres,
          };
          $.ajax({
            type: 'POST',
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify(data),
            url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/sponsor/add",
          })
            .done(function (data) {
              console.log(data);
            })
            .fail(function (jqXHR, textStatus) {
              console.log(jqXHR);
            });
        }
      }

    }
  })
}