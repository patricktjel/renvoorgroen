$(document).ready(function () {
  $("#menu").load("menu.html");
  $("#footer").load("footer.html");
  $("#topnavbar").load("navbar.html");
  
  $('[data-toggle=offcanvas]').click(function () {
    $('.row-offcanvas').toggleClass('active');
  });
});