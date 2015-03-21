function addSponsor() {
  bootbox.dialog({
    title: "Add Sponsor",
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
  $('#table-sponsor tbody tr').remove();

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/sponsor/get",
  })
    .done(function (data) {
      console.log(data);
      var tableRow = '';
      data.forEach(function (sponsor) {
        
        tableRow += '<tr>' +
          '<td>' + sponsor.naam + '</td>' +
          '<td>' + sponsor.emailadres + '</td>' +
          '<td><button onclick="deleteSponsor(' + sponsor.id + ');\" type="button" class="btn btn-success btn-sm pull-right delete-user-btn">Delete</button> </td>' +
          '</tr>';
        $('#table-sponsor tbody').append(tableRow);
        tableRow = "";
      });
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
      console.log(data);
    });


});

function deleteSponsor(id) {
  $.ajax({
    type: 'DELETE',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/sponsor/delete?id=" + id,
  })
    .done(function (data) {
      console.log(data);
      location.reload();
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    });
}