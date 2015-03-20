var links = "";
var sponsors = "";

function makelinks(data) {
  var link = '';
  data.forEach(function (activity) {
    link +=
      '<li><a href="#" data-value="' + activity.id + '">' + activity.user_activity + '</a></li>';
    links = links + link;
    link = "";
  })
}

function makeurls(data) {
  var sponsorone = '';
  data.forEach(function (sponsor) {
    sponsorone +=
      '<li><a href="#" data-value="' + sponsor.id + '">' + sponsor.naam + '</a></li>';
    sponsors = sponsors + sponsorone;
    sponsorone = "";
  })
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
        .done(function (data) {
          console.log(data);
          makeurls(data);
          var messageBox = '<div class="row"> ' +
            '<div class="col-md-12"> ' +
            '<form class="form-horizontal"> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="activity">Activies</label> ' +
            '<div class="col-md-4"> ' +
            '<div class="btn-group"> ' +
            '<button type="button" name="activity" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> ' +
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
            '<button type="button" name="sponsor" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-expanded="false"> ' +
            'Sponsor <span class="caret"></span> ' +
            '</button> ' +
            '<ul class="dropdown-menu" role="menu"> ' +
            sponsors +
            '</ul> ' +
            '</div> ' +
            '</div> ' +
            '</div> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="amount">Amount</label> ' +
            '<div class="col-md-4"> ' +
            '<input id="amount" name="amount" type="number" placeholder="Amount" class="form-control input-md"> ' +
            '</div> ' +
            '</div> ' +
            '<div class="form-group"> ' +
            '<label class="col-md-4 control-label" for="activity">Activity</label> ' +
            '<div class="col-md-4"> ' +
            '<input id="activity" name="activity" type="text" placeholder="Activity" class="form-control input-md"> ' +
            '<span class="help-block">This is the activity you want to reward.</span> </div> ' +
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
                  var firstName = $('#firstName').val();
                  var lastName = $('#lastName').val();
                  var id = $('#id').val();
                  console.log(firstName + " " + lastName + ": " + id);
                }
              }

            }
          })
        })
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
      location.reload();
    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
      console.log(data);
    });

  //    <li><a href="#">Action</a></li>
  //    <li><a href="#">Another action</a></li>
  //    <li><a href="#">Something else here</a></li>
  //    <li class="divider"></li>
  //    <li><a href="#">Separated link</a></li>

}