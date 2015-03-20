function addMilestone() {
  bootbox.dialog({
    title: "Add User",
    message: '<div class="row">  ' +
      '<div class="col-md-12"> ' +
      '<form class="form-horizontal"> ' +
      '<div class="form-group"> ' +
      '<label class="col-md-4 control-label" for="sponsor">Sponsor Email</label> ' +
      '<div class="col-md-4"> ' +
      '<input id="sponsorEmail" name="sponsor" type="text" placeholder="Email Address" class="form-control input-md"> ' +
      '</div> ' +
      '</div> ' +
      '<form class="form-horizontal"> ' +
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
      '</form> </div> </div>',
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
}