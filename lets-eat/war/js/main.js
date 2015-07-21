function initRegistroRest(){
	//Carga de Mapa
	loadMap();
	//Carga de Time Picker
	$('.time').timepicker();
	//Selector de Precio
	$("#avg_price").ionRangeSlider({
		min: 10,
	    max: 100,
	    from: 20,
	    grid: true,
	    prefix: "€"
	});
	//Selector de Puntuacion
	$("#score").ionRangeSlider({
	    grid: true,
	    min: 0.0,
	    max: 5.0,
	    from: 2.5,
	    step: 0.1
	});
}
