var mapObject = {
		dom : null,
		mapOptions : null,
		locateMe_Marker: null,
		restaurant_Marker: null,
		zoomLevels: {initial: 7, detailed: 18},
		state : {mapLoaded:false, restaurantAdded:false},
		msg : {errorGeoref:"Error en el proceso de georreferenciación por IP"}
};
function loadMap() {
	mapObject.mapOptions = {
		center : new google.maps.LatLng(37.533998, -4.631432),
		zoom : mapObject.zoomLevels.initial,
		mapTypeId : google.maps.MapTypeId.ROADMAP
	};
	mapObject.dom = new google.maps.Map(document.getElementById("map-canvas"),
			mapObject.mapOptions);
};
function getMyLocation() {
	if (navigator.geolocation) {
		navigator.geolocation.getCurrentPosition(success, error);
	} else {
		error(mapObject.msg.errorGeoref);
	}
	function success(position) {
		if (mapObject.locateMe_Marker!=null){
			mapObject.locateMe_Marker.setMap(null);
		}
		var latlng = new google.maps.LatLng(position.coords.latitude,
				position.coords.longitude);
		mapObject.locateMe_Marker = new google.maps.Marker({
			position : latlng,
			map : mapObject.dom,
			icon : "/assets/img/person_marker2.png",
		});
		mapObject.dom.setCenter(latlng);
		mapObject.dom.setZoom(mapObject.zoomLevels.detailed);
		mapObject.locateMe_Marker.setAnimation(google.maps.Animation.BOUNCE);
	    setTimeout(function(){ mapObject.locateMe_Marker.setAnimation(null); }, 3500);
	}
	function error(msg) {
		document.getElementById("map-canvas") = msg;
	}
};
function addRestaurant_Marker() {
	if (!mapObject.state.restaurantAdded){
		google.maps.event.addListener(mapObject.dom, 'click', function(event) {
			placeMarker(event.latLng); 
		});
		function placeMarker(latLng) {
			   mapObject.restaurant_Marker = new google.maps.Marker({
			        position: latLng, 
			        icon : "/assets/img/restaurant_marker.png",
			        map: mapObject.dom,
			        animation: google.maps.Animation.DROP
			   });
			   mapObject.state.restaurantAdded=true;
			   google.maps.event.clearInstanceListeners(mapObject.dom);
			   //load lat and lon inputs of the create restaurant form
			   $("#lat").val(latLng.lat().toFixed(3));
			   $("#lon").val(latLng.lng().toFixed(3));
		}
	}
}
function removeRestaurant_Marker() {
	mapObject.restaurant_Marker.setMap(null);
	mapObject.state.restaurantAdded=false;
}

