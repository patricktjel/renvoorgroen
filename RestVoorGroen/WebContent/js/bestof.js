//This function will get the best player of each category. 
function getBestResults() {
  var bestOfFloors = "";
  var bestOfSteps = "";
  var bestOfDistance = "";

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/best/distance?limit=3",
  })
    .done(function (data) {
    console.log(data);
      var index = 1;
      data.forEach(function (score) {
        $("#" + index + "place").text(score.user.naam);
        index++;
      });
      
    })
    .fail(function (jqXHR, textStatus) {
      console.log(data);
    });

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/best/floors?limit=1",
  })
    .done(function (data) {
      data.forEach(function (userObject) {
        bestOfFloors = userObject.user.naam + ": " + userObject.value;
      });
      $("#bestOfFloors").text(bestOfFloors);
    })
    .fail(function (jqXHR, textStatus) {
      bestOfFloors = "Not set yet.";
      $("#bestOfFloors").text(bestOfFloors);
    });
  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/best/steps?limit=1",
  })
    .done(function (data) {
      data.forEach(function (userObject) {
        bestOfSteps = userObject.user.naam + ": " + userObject.value;
      });
      $("#bestOfSteps").text(bestOfSteps);
    })
    .fail(function (jqXHR, textStatus) {
      bestOfSteps = "Not set yet.";
      $("#bestOfSteps").text(bestOfSteps);
    });
  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/best/distance?limit=1",
  })
    .done(function (data) {
      data.forEach(function (userObject) {
        bestOfDistance = userObject.user.naam + ": " + userObject.value + "km";
      });
      $("#bestOfDistance").text(bestOfDistance);
    })
    .fail(function (jqXHR, textStatus) {
      bestOfDistance = "Not set yet.";
      $("#bestOfDistance").text(bestOfDistance);
    });
}

$(document).ready(function () {
  getBestResults();
  $('#table-bestmilestone tbody tr').remove();

  $.ajax({
    type: 'GET',
    url: "http://grolschbak.cloudapp.net:8080/Restvoorgroen/api/milestone/getBest",
  })
    .done(function (data) {
      var tableRow = '';
      //Grab the best 5 milestones, or all the milestones if there are less then 5 milestones. 
      for (var i = 0; i < 5 && data.length > i; i++) {
        var milestone = data[i];

        tableRow += '<tr>' +
          '<td>' + milestone.sponsorNaam + '</td>' +
          '<td>' + milestone.activityNaam + '</td>' +
          '<td>' + milestone.value + '</td>' +
          '<td>&euro;' + milestone.bedrag + ',-</td>' +
          '</tr>';
        $('#table-bestmilestone tbody').append(tableRow);
      }

    })
    .fail(function (jqXHR, textStatus) {
      console.log(jqXHR);
    });
});