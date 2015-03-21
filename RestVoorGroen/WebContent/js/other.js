function resetGame(){
  bootbox.dialog({
    title: "YOU ARE GOING TO DELETE EVERYTHING!",
    message: 'ARE YOU SURE ABOUT THIS?',
    buttons: {
      cancel: {
        label: "NO",
        className: "btn-primary"
      },

      success: {
        label: "yes",
        className: "btn-danger",
        callback: function () {
          $.ajax({
            type: 'DELETE',
            contentType: "application/json",
            dataType: "json",
            url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/event/reset?id=1",
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