function login(){

    var status;
            
    var req = {
        "usuario_Email": document.getElementById("usuario").value,
        "contrasenia": document.getElementById("contrasenia").value
    }
    if(req.usuario_Email == "" || req.contrasenia == ""){
        alert("Debes ingresar los campos")
        return;
    }
    // fetch("http://localhost:4567/patitas/iniciarSesion", {
    fetch("https://patitasback.herokuapp.com/patitas/iniciarSesion", {

    method: "POST",
    body: JSON.stringify(req)
    })
    .then(Response =>{
        status = Response.status;
        return Response.json();
    })
    .then(datos => {
        error(status, datos.mensaje);
        localStorage.setItem("IDSESION", datos.idSesion);
        localStorage.setItem("TIPO", datos.tipo);
        document.getElementById("anchorID").click();
    })
}

function nombre(){
    document.getElementById('anchorID').href =  `./indexLiviano?id=${localStorage.getItem('IDSESION')}`;
}

function error(status, mensaje){
    if(status == 400 || status == 500){
        alert(mensaje)
        return;
    }
}