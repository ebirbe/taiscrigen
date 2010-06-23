// Tiempo en segundos
var tiempo = 5;
var publicidad = new Array();
var link = new Array();
var pub_div;
var pub_link;

tiempo = tiempo * 1000;

publicidad[0] = "<img src='rotativas/imagen1.jpg'></img>";
link[0] = "http://meresidencio.com/";

publicidad[1] = "<img src='rotativas/imagen2.jpg'></img>";
link[1] = "http://jaky47.com/";

i=0;

function inicializar(){
	pub_div = document.getElementById("pub_div");
	rotarPublicidad();
}

function rotarPublicidad(){
	pub_div.innerHTML = "<a id='pub_link'>" + publicidad[i] + "</a>";
	pub_link = document.getElementById("pub_link");
	pub_link.href = link[i];
	document.title = i;
	
	if(i == publicidad.length - 1)	i = 0;
	else							i++;
	
	setTimeout("rotarPublicidad()", tiempo);
}

function aleatorio(inferior,superior){
	numPosibilidades = superior - inferior;
	aleat = Math.random() * numPosibilidades;
	aleat = Math.round(aleat);
	return parseInt(inferior) + aleat;
	} 