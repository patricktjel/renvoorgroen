$(document).ready(function () {
  $('#table-bestmilestone tbody tr').remove();

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/milestone/getBest",
  })
    .done(function (data) {
      var tableRow = '';
    console.log(data);
      //Grab the best 5 milestones, or all the milestones if there are less then 5 milestones. 
      for (var i = 0; i < 5 && data.length > i; i++) {
        var milestone = data[i];
        
        tableRow += '<tr>' +
          '<td>' + milestone.sponsorNaam + '</td>' +
          '<td>' + milestone.activityNaam + '</td>' +
          '<td>' + milestone.value + '</td>' +
          '<td>&euro;' + milestone.bedrag + '</td>' +
          '</tr>';
        $('#table-bestmilestone tbody').append(tableRow);
      }

    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    });
});