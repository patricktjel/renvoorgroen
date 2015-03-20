function addMilestone() {
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
          var name = $('#name').val();
          var email = $('#email').val();
          console.log(name + " " + email);
        }
      }

    }
  })
}