/**
 * This file will be responsible for loading in the interactive map.
 */
/*
$(document).ready(function iniMap() {
	//map variable
	var map = L.map("map").setView([52.636878, -1.139759], 13);
	
	//my access token is for the map. 
	//mapboxgl.accessToken = 'pk.eyJ1IjoibWlrZWdtYW4iLCJhIjoiY2oyNHlqd2J4MDAxZzJ3bGx5YmwyZGZrciJ9.JWgoemUuUJCdNGuwa-qSbg';
	
//	L.titleLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
//	    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
//	    maxZoom: 18,
//	    //id: 'your.mapbox.project.id',
//	    //accessToken: 'pk.eyJ1IjoibWlrZWdtYW4iLCJhIjoiY2oyNHlqd2J4MDAxZzJ3bGx5YmwyZGZrciJ9.JWgoemUuUJCdNGuwa-qSbg'
//	    	}).addTo(map);
	
	L.tileLayer('https://api.tiles.mapbox.com/v4/{id}/{z}/{x}/{y}.png?access_token={accessToken}', {
	    attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://mapbox.com">Mapbox</a>',
	    maxZoom: 18,
	    id: 'your.mapbox.project.id',
	    accessToken: 'pk.eyJ1IjoibWlrZWdtYW4iLCJhIjoiY2oyNHlqd2J4MDAxZzJ3bGx5YmwyZGZrciJ9.JWgoemUuUJCdNGuwa-qSbg'
	}).addTo(map);
	
	var marker = L.marker([51.5, -0.09]).addTo(map);
	
	var popup = L.popup(); //variable for the Popup.
	
	function onMapClick(e) {
		popup
		
		.setLatLng([51.5, -0.09])
	    .setContent("Your Current Location.")
	    .openOn(mymap);
		
	}
	map.on('click',onMapClick);	
	
//	mapboxgl.accessToken = 'pk.eyJ1IjoibWlrZWdtYW4iLCJhIjoiY2oyNHlqd2J4MDAxZzJ3bGx5YmwyZGZrciJ9.JWgoemUuUJCdNGuwa-qSbg';
//	var map = new mapboxgl.Map({
//	container: 'map',
//	style: 'mapbox://styles/mapbox/satellite-streets-v9'
//	});
	
});



//Here is the method for initialising the map. 
//function iniMap(){ 
////these coordinates (Latitude) and (Longitute) which will be used to set the default location to Leicester
//	var location = {lat: 52.636878, lng:-1.139759};
//	//coordinates for London (51.505 -0.09)
//    var map = new Map(document.getById("map"));
//L.marker([51.5, -0.09]).addTo(map)
//.bindPopup('')
//.openPopup();
//}
//title image = 'http://{s}.somedomain.com/blabla/{z}/{x}/{y}.png'
/*
 * //Here a circle is added to the map.
	var circleLocator = L.circle([51.505 -0.09], {
		color: 'green',
		fillColor: '#f03',
		fillOpacity: 0.5,
		radius: 500
	}).addTo(map);
 * */
