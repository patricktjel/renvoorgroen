var links = "";
var sponsors = "";
var selSponsors = "";
var selActivity = "";

function makelinks(data) {
  var link = '';
  data.forEach(function (activity) {
    link +=
      '<li><a href="#" onclick="selActi(' + activity.id + ');" data-value="' + activity.id + '">' + activity.user_activity + '</a></li>';
    links = links + link;
    link = "";
  })
}

function makeurls(data2) {
  var sponsorone = '';
  data2.forEach(function (sponsor) {

    sponsorone +=
      '<li><a href="#" onclick="selSpon(' + sponsor.id + ');" data-value="' + sponsor.id + '">' + sponsor.naam + '</a></li>';
    sponsors = sponsors + sponsorone;
    console.log(sponsors);
    sponsorone = "";
  })
  return sponsors;
}
//<!-- Single button -->
//<div class="btn-group">
//  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
//    Action <span class="caret"></span>
//  </button>
//  <ul class="dropdown-menu" role="menu">
//    <li><a href="#">Action</a></li>
//    <li><a href="#">Another action</a></li>
//    <li><a href="#">Something else here</a></li>
//    <li class="divider"></li>
//    <li><a href="#">Separated link</a></li>
//  </ul>
//</div>
function addMilestone() {

  var sponsors = "";
  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/activity/get",
  })
    .done(function (data) {
      console.log(data);
      makelinks(data);
      $.ajax({
        type: 'GET',
        url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/sponsor/get",
      })
        .done(function (data2) {
          console.log(data2);
          sponsors = makeurls(data2);
          var messageBox = '<div class="row"> ' +
            '<div class="col-md-12"> ' +
            '<form class="form-horizontal"> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="activity">Activies</label> ' +
            '<div class="col-md-4"> ' +
            '<div class="btn-group"> ' +
            '<button type="button" id="activity" name="activity" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> ' +
            'Activity <span class="caret"></span> ' +
            '</button> ' +
            '<ul class="dropdown-menu" role="menu"> ' +
            links +
            '</ul> ' +
            '</div> ' +
            '</div> ' +
            '</div> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="sponsor">Sponsors</label> ' +
            '<div class="col-md-4"> ' +
            '<div class="btn-group"> ' +
            '<button type="button" id="sponsor" name="sponsor" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> ' +
            'Sponsor <span class="caret"></span> ' +
            '</button> ' +
            '<ul class="dropdown-menu" role="menu"> ' +
            sponsors +
            '</ul> ' +
            '</div> ' +
            '</div> ' +
            '</div> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="amount">Needed amount to win</label> ' +
            '<div class="col-md-4"> ' +
            '<input id="amount" name="amount" type="number" placeholder="Amount" class="form-control input-md"> ' +
            '</div> ' +
            '</div> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="euro">Value in Euro</label> ' +
            '<div class="col-md-4"> ' +
            '<input id="euro" name="euro" type="number" placeholder="Euro" class="form-control input-md"> ' +
            '<span class="help-block">This is the amount you are going to fund after finishing this milestone. </span> </div> ' +
            '</div> ' +
            '</div> ' +
            '</form> </div> </div>';

          bootbox.dialog({
            title: "Add Milestone",
            message: messageBox,
            buttons: {
              cancel: {
                label: "Cancel",
                className: "btn-primary"
              },

              success: {
                label: "Save",
                className: "btn-success",
                callback: function () {
                  var amount = $('#amount').val();
                  var euro = $('#euro').val();
                  var activityid = selActivity;
                  var sponsorid = selSponsors;
                  var data = {
                    "value": amount,
                    "bedrag": euro,
                    "activity_id": activityid,
                    "sponsor_id": sponsorid
                  };
                  $.ajax({
                    type: 'POST',
                    contentType: "application/json",
                    dataType: "json",
                    data: JSON.stringify(data),
                    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/milestone/add",
                  })
                    .done(function (data) {
                      console.log(data);
                      location.reload();
                    })
                    .fail(function (jqXHR, textStatus) {
                      console.log(jqXHR);
                      location.reload();
                    });
                  console.log(amount + ":" + euro + ":" + activityid + ":" + sponsorid);
                }
              }

            }
          })
        })
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    });

  //    <li><a href="#">Action</a></li>
  //    <li><a href="#">Another action</a></li>
  //    <li><a href="#">Something else here</a></li>
  //    <li class="divider"></li>
  //    <li><a href="#">Separated link</a></li>

}

function selActi(data) {
  selActivity = data;
}

function selSpon(data) {
  selSponsors = data;
}
$(document).ready(function () {
  $('#table-users tbody tr').remove();

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/milestone/get",
  })
    .done(function (data) {
      console.log(data);
      var tableRow = '';
      data.forEach(function (user) {
        tableRow += '<tr>' +
          '<td>' + user.id + '</td>' +
          '<td>' + user.inlognaam + '</td>' +
          '<td>' + user.naam + '</td>' +
          '<td><button onclick="deleteUser(' + user.id + ');\" type="button" class="btn btn-success btn-sm pull-right delete-user-btn">Delete</button> </td>' +
          '</tr>';
        $('#table-users tbody').append(tableRow);
      });
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
      console.log(data);
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