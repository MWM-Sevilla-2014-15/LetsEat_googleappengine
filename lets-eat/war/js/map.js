//Maps
//icons at: http://kml4earth.appspot.com/icons.html
var mapOptions;
var mapLoaded = false;
var map;
function loadMap() {
	var mapOptions = {
			center:new google.maps.LatLng(37.533998, -4.631432),
            zoom: 7,
            mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
    map.fitBounds(this.map.bounds);
}
/*
if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(success, error);
} else {
    error('No soportado');
}
function success(position) {
    var latlng = new google.maps.LatLng(position.coords.latitude, position.coords.longitude);
    var mapOptions = {
        zoom: 16,
        center: latlng,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };
    var map = new google.maps.Map(document.getElementById("map-canvas"), mapOptions);
    var marker = new google.maps.Marker({
        position: latlng,
        map: map,
        title: "Aquí estás!"
    });
}
function error(msg) {
    document.getElementById("map-canvas") = msg;
}*/