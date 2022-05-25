var map;
var position;
var image = {
    "FIRE" : "/img/fire.png",
    "FLOOD" : "/img/flood.png",
    "LANDSLIDE" : "/img/landslide.png",
    "HURRICANE" : "/img/hurricane.png"
};

(function () {
    let filterSubmit = document.querySelector('#filter');
    let addMarkerSubmit = document.querySelector('#newFlag')
    

    _get( 'http://localhost:8080/flag' ).then( data => {
        console.log(data);
        data.forEach( (e) => addMarker(e));
    });

    filterSubmit.addEventListener('submit', filter);
    addMarkerSubmit.addEventListener('submit', add);


})();

function filter(e){
    e.preventDefault();
    let formData = new FormData(e.target);
    let realDistance = getRealDistance(formData.get("distance"));
    formData.set("distance", realDistance);
    let params = new URLSearchParams(formData).toString()
    const url = `http://localhost:8080/flag/@${position.lat},${position.lng}/?${params}`
    _get( url ).then( data => {
        data.forEach( (e) => addMarker(e));
    });
}

function add(e){
    e.preventDefault();
    let formData = new FormData(e.target)
    let json = {
        "latitude": position.lat,
        "longitude": position.lng,
        "type": formData.get("type")
    };
    console.log(json);
    _post('http://localhost:8080/flag', json).then( d =>
        _get( 'http://localhost:8080/flag' ).then( data => {
            data.forEach( (e) => addMarker(e));
        })
    );
}

function getRealDistance(fake){
    let n1 = 100,n2 = 100;
    
    for(let i = parseInt(fake); i>1; i--){
      if (i % 2 == 0)
        n1*=5;
      else
        n1*=2;
    }
    for(let i = Math.ceil(fake); i>1; i--, fake--){
      if (i % 2 == 0)
        n2*=5;
      else
        n2*=2;
    }
    return ((n2-n1) * fake) + n1;
}

async function _get (url) {
    let response = await fetch(url);
    let data = await response.json();
    return data;
}

async function _post (url, json) {
	let response = await fetch(url,{
		method: 'post',
		headers: {
			'Content-Type': 'application/json',
			'Accept': 'application/json'
		},
		body: JSON.stringify(json)
	});
    let data =  await response.json();
    return data;
}

function addMarker(flag){
    return new google.maps.Marker({
        position: { lat: flag.latitude, lng: flag.longitude },
        map,
        icon: image[flag.type]
    });
}


function getUserPosition() {
    // Simple wrapper
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function (pos) {
            position = {lat:pos.coords.latitude, lng: pos.coords.longitude};
            let p = new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude);
            map.setCenter(p);
        });
    }
/*    return new Promise((res, rej) => {
        navigator.geolocation.getCurrentPosition(res, rej);
    });
*/
}

function moveMapToUserPosition(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition( (p) =>{
            position = {lat:p.coords.latitude, lng: p.coords.longitude}
            console.log('lat:'+p.coords.latitude+', lng: '+p.coords.longitude)
            moveToLocation( p.coords.latitude,p.coords.longitude)
        });
    }else{
        status.textContent = "Navegador não suporta/permite rastrear sua localização";
    }
}

function moveToLocation(lat, lng){
    const center = new google.maps.LatLng(lat, lng);
    map.panTo(center);
}
    
function initMap() {
    getUserPosition();

    const mapOptions = {
        center: {lat:0,lng:0},//new google.maps.LatLng(pos.coords.latitude, pos.coords.longitude),
        zoom: 15,
        mapTypeId: google.maps.MapTypeId.ROADMAP
    };

    map = new google.maps.Map(
        document.getElementById("map"),
        mapOptions
    );
}