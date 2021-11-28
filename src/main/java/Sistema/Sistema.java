package Sistema;

import Business.*;
import Business.publicaciones.Publicacion;
import Business.publicaciones.PublicacionDarEnAdopcion;
import Business.publicaciones.PublicacionPerdida;
import Business.services.apiHogares.apiHogares;
import Business.services.apiHogares.entities.Hogar;
import Business.services.apiHogares.entities.Mensaje;
import Notificar.notificarStrategy;
import com.google.gson.Gson;

import dominioBD.*;



import mappers.*;
import respuestas.*;
import seguridad.register;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Spark;
import spark.template.handlebars.HandlebarsTemplateEngine;
import utils.BDUtils;
import utils.SesionManager;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Sistema {

    List<Organizacion> listaDeOrganizaciones = new ArrayList<>();
    private static List<Usuario> listaDeUsuarios = new ArrayList<>();
    List<Voluntario> listaDeVoluntarios = new ArrayList<>();
    List<Publicacion> publicaciones = new ArrayList<>();
    List<Adoptante> adoptantes = new ArrayList<>();
    List<Rescatista> rescatistas = new ArrayList<>();
    HashMap<String,String> preguntasObligatorias;
    private static Sistema instancia = null;

    public static Sistema getInstancia() {
        if (instancia == null) {
            instancia = new Sistema();
        }
        return instancia;
    }

    public static boolean validarContrasenia(String contrasenia, String usuario) throws FileNotFoundException {
        return register.validarContrasenia(contrasenia, usuario);
    }

    public static boolean usuarioNoValido(String usuarioProvisorio) {
        /*if (listaDeUsuarios.stream().anyMatch(usuario -> usuario.getNombre().equals(usuarioProvisorio))){
            return true;}
        else{
            return false;
        }*/
        return !BDUtils.puedoEsteNombre(usuarioProvisorio);
        //return listaDeUsuarios.stream().anyMatch(usuario -> usuario.getNombre().equals(usuarioProvisorio));
    }

    /*   public void crearDuenio() {
           Duenio duenio = new Duenio();
       }*/
    public void crearAdmin() {
    }

    public static List<PublicacionDarEnAdopcion> publicacionesAptasParaAdoptar(Adoptante unAdoptante) {
        List<PublicacionDarEnAdopcion> publicacionesDarAdopcion = BDUtils.damePublicacionesAdopcion();
        List<PublicacionDarEnAdopcion> publicacionesAptas = unAdoptante.meSirvenLasPublicaciones(publicacionesDarAdopcion);
        return publicacionesAptas;
    }

    public static void mostrarUsuarios(){
        listaDeUsuarios.forEach(usuario -> usuario.mostrarUsuario());
    }

    public void agregarVoluntario(Voluntario unVoluntario){
        listaDeVoluntarios.add(unVoluntario);
    }

    public void recibirForms(int idMascota, String nombreResc, String apellidoResc, String telefonoResc, String fechaNac, String tipoDocResc, int numeroDocResc, List<notificarStrategy> formaNotificacion, String email, String contra, String nombreUsuario, List<Contacto> contactos, List<Foto> fotos, String descripcionEncuentro, Float posX, Float posY, boolean seLoQueda, float radioBusqHogarEnM, Especie especie){
        Rescate unRescate = new Rescate(idMascota, fotos, descripcionEncuentro, posX, posY);
       // Usuario miUsuario = new Usuario(TipoDeUsuario.RESCATISTA, nombreUsuario, contra, email);
       // Rescatista unRescatista = new Rescatista(nombreResc,apellidoResc,telefonoResc,fechaNac,tipoDocResc,numeroDocResc,formaNotificacion, contactos, miUsuario);
       /* for(Contacto c : contactos) {
            unRescatista.agregarContacto(c);
        }*/


        if(idMascota != 0) {
            //aca va rescate con chapita id != 0
            Mascota mascota = buscarMascota(idMascota);//Se supone que la encuentra
            mascota.serRescatado();
            if(seLoQueda) {

            } else {
                // devolver las opciones y esperar a que elijan una
                // COMO SE HACE PARA ESPERAR ?
              //  this.hogaresDeTransitoPosibles(posX, posY, radioBusqHogarEnM, tamanio, especie);
                // ESPERA RESPUESTA
                hogarDeTransito respuesta;
                // ACA HAY QUE LLEVAR AL PERRO AL HOGAR, CUANDO LLEGA AL HOGAR, SE "OCUPA EL LUGAR" EN EL HOGAR. SUPONEMOS QUE NO SE "QUEDA RESERVADO" HASTA QUE LLEGUE EL RESCATISTA CON EL PERRO
            }
        }
        else
        {
            //rescate sin chapita id = 0
            //this.agregarPublicacionPerdida(unRescatista, unRescate);
        }
    }

    private void agregarPublicacionPerdida(Rescatista unRescatista, Rescate unRescate) {
//        PublicacionPerdida unaPublicacion = new PublicacionPerdida(unRescate, unRescatista);
//        Organizacion orga = this.encontrarOrganizacionMasCercana(unRescate.lugarEncuentroX,unRescate.lugarEncuentroY);
//        orga.agregarPublicacionEnRevision(unaPublicacion);
    }

    private Organizacion encontrarOrganizacionMasCercana(Float lugarEncuentroX, Float lugarEncuentroY) {
        int i=0;
        int posicionElegido=0;
        for (Organizacion organiz : listaDeOrganizaciones){
            if(i==0 ){
                posicionElegido=i;
                i++;
            }
            Organizacion aComparar= listaDeOrganizaciones.get(posicionElegido);
            float distanciaComparar= pasosAdar(aComparar.getX(),aComparar.getY(),lugarEncuentroX,lugarEncuentroY);
            float distCandidato= pasosAdar(organiz.getX(),organiz.getY(),lugarEncuentroX,lugarEncuentroY);
            if(distanciaComparar<distCandidato){
                posicionElegido=i;

            }
            i++;
        }
        Organizacion orgElegidaPorElDestino = listaDeOrganizaciones.get(posicionElegido);
        return orgElegidaPorElDestino;
    }

    private float pasosAdar(float x,float y,float xIr, float yIr){ //TODO buscar como calcular con lat y long
        float difX,difY,total;
        if (x>xIr){
            difX=x-xIr;
        }else{
            difX=xIr-x;
        }
        if(y>yIr){
            difY=y-yIr;
        }else{
            difY=yIr-y;
        }
        total= difY+difX;
        return total;
    }

    public Mascota buscarMascota(int idMascota) {

        Mascota unaMascota;

        int i = 0;

        while(!listaDeOrganizaciones.get(i).tieneMascota(idMascota)){
            i++;
        }

        unaMascota = listaDeOrganizaciones.get(i).buscarMascota(idMascota);

        return unaMascota;
    }

    public static List<hogarDeTransito> getHogaresDeTransito() {
        List<hogarDeTransito> hogaresDeTransito = new ArrayList<hogarDeTransito>();
        // TODO ACA VA UN METODO QUE AGREGA LOS HOGARES QUE VA LEYENDO DE LA API AL LA LISTA
        return hogaresDeTransito;
    }

    public static List<hogarDeTransito> hogaresDeTransitoPosibles(float posXDelRescate, float posYDelRescate, float radioBusqHogarEnM, Tamanio tamanio, Especie especie) {
        // TODO FILTER DE LA LISTA DE HOGARES DE TRANSITO
        List<hogarDeTransito> hogaresDeTransitoPosibles = new ArrayList<hogarDeTransito>();
        Sistema.getHogaresDeTransito().stream().filter(unHogar -> unHogar.pasaElFiltrado(posXDelRescate, posYDelRescate, radioBusqHogarEnM, tamanio, especie));
        return hogaresDeTransitoPosibles;
    }

    public void mostrarUsuario() {
        Usuario primero = listaDeUsuarios.get(0);
        primero.mostrarUsuario();
    }

    public void agregarDuenio(String nombre, String apellido, String telefono, String fechaNacimiento, String tipoDoc, int numDocumento, List<notificarStrategy> formasDeNotificacion, List<Contacto> contactos, Usuario usuario) {
        //Duenio nuevoDuenio = new Duenio(nombre, apellido, telefono, fechaNacimiento, tipoDoc, numDocumento, formasDeNotificacion, contactos, usuario);
        //  TODO A QUE ORGANIZACION PERTENECE EL DUENIO?
    }
    public void agregarRescatista(String nombre, String apellido, String telefono, String fechaNacimiento, String tipoDoc, int numDocumento, List<notificarStrategy> formasDeNotificacion, List<Contacto> contactos, Usuario usuario) {
        //Rescatista nuevoRescatista = new Rescatista(nombre, apellido, telefono, fechaNacimiento, tipoDoc, numDocumento, formasDeNotificacion, contactos, usuario);
        //  TODO A QUE ORGANIZACION PERTENECE EL RESCATISTA?
       // rescatistas.add(nuevoRescatista);
    }
    public void agregarAdoptante(String nombre, String apellido, String telefono, String fechaNacimiento, String tipoDoc, int numDocumento, List<notificarStrategy> formasDeNotificacion, List<Contacto> contactos, Usuario usuario) {
        //Adoptante nuevoAdoptante = new Adoptante(nombre, apellido, telefono, fechaNacimiento, tipoDoc, numDocumento, formasDeNotificacion, contactos, usuario);
        //adoptantes.add(nuevoAdoptante);
    }

    public List<Publicacion> mostrarPublicacionesAprobadas() {
        List<Publicacion> aux = new ArrayList<>();
        for(Organizacion o:listaDeOrganizaciones) {
            aux.addAll(o.publicacionesAprobadas);
        }
        return aux;
    }

    public void darEnAdopcion(String usuarioDuenio, int idMascota) {
        Organizacion orgaDuenio = this.buscarOrgaConUsuario(usuarioDuenio);
        Duenio unDuenio = this.buscarDuenio(usuarioDuenio, orgaDuenio);
        HashMap<String,String> preguntasYrespuestasTotales = this.copy(preguntasObligatorias);
        HashMap<String,String> preguntasYrespuestasOrganizacion = this.copy(orgaDuenio.preguntasOrganizacion);

        preguntasYrespuestasTotales.putAll(preguntasYrespuestasOrganizacion);
        //TODO DEBERIAMOS RECIBIR LAS RESPUESTAS Y PONERLAS CADA UNA EN SU PREGUNTA, LLAMANDO A OTRO METODOS. TAREA PARA OTRO DIA
        Scanner sn = new Scanner(System.in);
        // TODO ACA ESTA PARA AGREGAR LA RTA A LA PREGUNTA, POR SI LO TENEMOS QUE TENER
        preguntasYrespuestasTotales.forEach ((k, v) -> {
            System.out.println(k);
            String rta = sn.nextLine();
            preguntasYrespuestasTotales.put(k,rta);
        });
        //
        //PublicacionDarEnAdopcion unaPublicacion = new PublicacionDarEnAdopcion(this.buscarMascota(idMascota), unDuenio, preguntasYrespuestasTotales);
        //publicaciones.add(unaPublicacion);
        //TODO VER SI HAY QUE AGREGARLA A UNA LISTA DE PUB A APROBAR O QUE ONDA
        //TODO VER SI HAY QUE APROBARLA
    }

    public static HashMap<String, String> copy(HashMap<String, String> original)
    {
        HashMap<String, String> copy = new HashMap<String, String>();
        for (Map.Entry<String, String> entry : original.entrySet())
        {
            copy.put(entry.getKey(), entry.getValue());
        }
        return copy;
    }

    public Duenio buscarDuenio(String unUsuario, Organizacion org) {
        Duenio duenio = org.buscarDuenio(unUsuario);
        return duenio;
    }

    public Organizacion buscarOrgaConUsuario(String unUsuario) {
        Organizacion org = listaDeOrganizaciones.stream().filter(organizacion -> organizacion.tieneDuenio(unUsuario)).collect(Collectors.toList()).get(0);
        return org;
    }

    public void generarPublicacionParaAdoptar(HashMap<String, String> preferenciasYComodidades) {
        //Publicacion miPub = new PublicacionAdoptar(preferenciasYComodidades);
        // TODO VER DONDE SE GUARDA ESTA PUBLICACION. EN UNA ORGANIZACION? O ACA? O EN UNA ORG AL AZAR Y DESPUES CUANDO QUEREMOS MOSTRARLO, RECORREMOS TODAS LAS ORGS
    }

    public void recomendarAdoptar(){
        List<Adoptante> adoptantes = BDUtils.dameAdoptantes();
        adoptantes.forEach(adoptante -> adoptante.recomendarAdopcion());
    }



    //FUNCIONES PRINCIPALES
    // TODO ACA ESTAN LOS SPARKS!!! :) <3 (LOS IRON MANS)
    public static void definePaths(){
        Spark.post("/user", Sistema::crearUsuario);
        Spark.post("/duenio", Sistema::crearDuenio);
        Spark.post("/voluntario", Sistema::crearVoluntario);
        Spark.post("/iniciarSesion", Sistema::iniciarSesion);
        Spark.post("/contacto", Sistema::agregarContacto);
        Spark.post("/notifCont", Sistema::agregarNotificacionCont);
        Spark.post("/notifPers", Sistema::agregarNotificacionPers);
        Spark.post("/mascotas", Sistema::crearMascota);
        Spark.get("/mascotas/:id", Sistema::devolverMascota);
        Spark.post("/mascotaCarac", Sistema::agregarCaracteristicaMascota);
        Spark.post("/mascotas/fotos", Sistema::agregarFotosMascota);
//        Spark.post("/rescate", Sistema::encontrarMascota);
        Spark.post("/rescatista", Sistema::crearRescatista);
        Spark.post("/caracAdmin", Sistema::agregarCaracteristicaAdmin);
        Spark.post("/publicacion/perdida",  Sistema::crearPublicacionPerdida);
        Spark.get("/publicacion/perdida",  Sistema::devolverPublicacionesPerdidas);
        Spark.post("/publicacion/adopcion",  Sistema::crearPublicacionAdopcion);
        Spark.post("/publicacion/adopcion/preguntas",  Sistema::agregarPreguntasPubli);
        Spark.post("/publicacion/adoptar",  Sistema::crearPublicacionAdoptar);
        Spark.post("/organizacion", Sistema::crearOrganizacion);
        Spark.get("/publicacion/adopcion", Sistema::devolverPublicacionesDarAdopcion);
        Spark.get("/misDatos", Sistema::DatosUsuario);
        Spark.get("/duenio/mascotas", Sistema::devolverMascotas);
        Spark.get("/orga/caracteristicas/:id", Sistema::dameCaracteristicas);
        Spark.get("/hogares", Sistema::dameHogares);
        Spark.get("/iniciarSesionLiviano", Sistema::iniciarSesionLiviano);
        Spark.get("/indexLiviano", Sistema::indexLiviano);
        Spark.get("/damePreguntas/:id", Sistema::damePreguntas);
        Spark.get("/damePreguntasMasc/:id", Sistema::damePreguntasMasc);
        Spark.get("/mascotaCarac/:id", Sistema::dameCaracteristicasMasc);
        Spark.get("/mascota/:id", Sistema::dameMascota);
        Spark.post("/mascota/adoptar", Sistema::adoptarMascota);
        Spark.post("/rescate", Sistema::crearRescate);
        Spark.post("/rescate/fotos", Sistema::agregarFotosRescate);
        Spark.post("/rescate/duenio", Sistema::notificarDuenioRescate);
        Spark.get("/damePublicacion/:id", Sistema::damePublicacion);
        Spark.get("/aprobarPublicacion/:id", Sistema::aprobarPublicacion);
        Spark.get("/esMia/:id", Sistema::esMia);
        Spark.get("/publicacionesRecomendadas/:id", Sistema::recomendaciones);



        //Spark.post("/publicacionPerdida", Sistema::crearPubPerdida);
    }

    private static String recomendaciones(Request req, Response res) {
        String id = req.params("id");

        res.type("application/json");

        res.status(200);

        Adoptante adoptante = BDUtils.buscarAdoptante(Long.parseLong(id));

        List<PublicacionDarEnAdopcion> publicaciones = Sistema.publicacionesAptasParaAdoptar(adoptante);

        return new Gson().toJson(publicaciones);
    }

    private static String esMia(Request req, Response res) {
        String id = req.params("id");

        res.type("application/json");

        res.status(200);

        PublicacionPerdidaBD publi = BDUtils.damePublicacionPerdida(Integer.parseInt(id));

        publi.getPper_rescate().getResc_rescatista().transformar().notificarEncontrar();

        return new mensaje("Se notifico al duenio").transformar();
    }

    private static String aprobarPublicacion(Request req, Response res) {
        String id = req.params("id");

        res.type("application/json");

        res.status(200);

        BDUtils.aprobarPublicacion(Integer.parseInt(id));

        return new mensaje("se aprobo la publi").transformar();
    }

    private static String damePublicacion(Request req, Response res) {

        String id = req.params("id");

        res.type("application/json");

        res.status(200);


        PublicacionPerdidaBD dea = BDUtils.damePublicacionPerdida( Integer.parseInt(id));

        if(dea == null){
            res.status(400);
            return new mensaje("No hay publicaciones").transformar();
        }
        publiPerdida publi = new publiPerdida(dea.getPubl_id(),dea.getPper_rescate().getResc_descripcionEstado(), dea.getPubl_estado(),dea.getPper_rescate().getResc_lugarEncuentroX(),dea.getPper_rescate().getResc_lugarEncuentroY(),dea.getPper_rescate().getResc_rescatista(), dea.getPper_rescate().getFotoRescates().stream().map(fotoRescate -> fotoRescate.transformar()).collect(Collectors.toList()));
        return new Gson().toJson(publi);
    }

    private static String devolverPublicacionesPerdidas(Request req, Response res) {

        res.type("application/json");

        res.status(200);


        List<PublicacionPerdidaBD> publicaciones = BDUtils.damePublicacionesPerdida();

        if(publicaciones.isEmpty() || publicaciones == null){
            res.status(400);
            return new mensaje("No hay publicaciones").transformar();
        }
        List<publiPerdida> lista = publicaciones.stream().map(dea ->new publiPerdida(dea.getPubl_id(),dea.getPper_rescate().getResc_descripcionEstado(), dea.getPubl_estado(),dea.getPper_rescate().getResc_lugarEncuentroX(),dea.getPper_rescate().getResc_lugarEncuentroY(),dea.getPper_rescate().getResc_rescatista(), dea.getPper_rescate().getFotoRescates().stream().map(fotoRescate -> fotoRescate.transformar()).collect(Collectors.toList()))).collect(Collectors.toList());
        return new Gson().toJson(lista);

    }

    private static String notificarDuenioRescate(Request req, Response res) {

        duenioMascota algo = new Gson().fromJson(req.body(), duenioMascota.class);
        MascotaBD mascotaBD = BDUtils.buscarMascota(Integer.parseInt(algo.getMascota()));
        mascotaBD.getMasc_duenio().transformar().notificarMascotaEncontrada(mascotaBD.transformar());

        return new mensaje("Se notifico al duenio").transformar();
    }

    private static String agregarFotosRescate(Request req, Response res) {

        fotosRescate fotos = new Gson().fromJson(req.body(), fotosRescate.class);


        fotos.getFotos().forEach(foto -> BDUtils.agregarObjeto(foto));

        res.type("application/json");
        res.status(200);

        return new mensaje("Se agregaron las fotos").transformar();
    }

    private static String crearRescate(Request req, Response res) {

        RescateBD rescate = new Gson().fromJson(req.body(), RescateBD.class);

        BDUtils.agregarObjeto(rescate);

        res.type("application/json");
        res.status(200);

        String idResc = "{\"rescate\": "+ rescate.getResc_id() + "}";

        return idResc;

    }

    private static String adoptarMascota(Request req, Response res) {

        adoptarMascota adopcion = new Gson().fromJson(req.body(), adoptarMascota.class);

        MascotaBD mascota = BDUtils.buscarMascota(adopcion.getMascota());

        Duenio duenio = BDUtils.dameDuenio(mascota.getMasc_duenio().getPers_id()).transformar();

        PersonaBD persone = BDUtils.dameIdPersona(Long.valueOf(adopcion.getAdoptante()));

        duenio.serNotificadoAdopcion(persone.getPers_nombre(), persone.getPers_apellido(), persone.getPers_telefono());

        res.type("application/json");
        res.status(200);

        return new mensaje("Se notifico al duenio").transformar();
    }

    private static String dameMascota(Request req, Response res) {
        String idMasc = req.params("id");

        MascotaBD mascota = BDUtils.buscarMascota(Integer.parseInt(idMasc));

        List<pregPublicacionDarEnAdopcion> preguntas = BDUtils.damePreguntasMasc(Integer.parseInt(idMasc));

        HashMap<String,String> caracteristicas = BDUtils.dameHashCaracteristicasMasc(Long.parseLong(idMasc));

        List<hashmapJSON> caracJSON = new ArrayList<>();
        caracteristicas.forEach((clave,valor) -> caracJSON.add(new hashmapJSON(clave,valor)));

        res.type("application/json");
        res.status(200);

        return new Gson().toJson(new superMascota(mascota,preguntas,caracJSON));
    }

    private static String dameCaracteristicasMasc(Request req, Response res) {

        String idMasc = req.params("id");

        HashMap<String,String> caracteristicas = BDUtils.dameHashCaracteristicasMasc(Long.parseLong(idMasc));

        res.type("application/json");

        if(caracteristicas.isEmpty()){
            res.status(400);
            return new mensaje("No hay preguntas").transformar();
        }

        res.status(200);

        List<hashmapJSON> caracJSON = new ArrayList<>();
        caracteristicas.forEach((clave,valor) -> caracJSON.add(new hashmapJSON(clave,valor)));

        //String algo = new Gson().toJson(caracteristicas);


        return new Gson().toJson(caracJSON);
    }

    private static String damePreguntasMasc(Request request, Response res) {

        String idMasc = request.params("id");

        List<pregPublicacionDarEnAdopcion> preguntas = BDUtils.damePreguntasMasc(Integer.parseInt(idMasc));

        res.type("application/json");

        if(preguntas.isEmpty()){
            res.status(400);
            return new mensaje("No hay preguntas").transformar();
        }

        res.status(200);

        return new Gson().toJson(new listaPreguntasMasc(preguntas));

    }


    private static String damePreguntas(Request request, Response res) {

        String idOrg = request.params("id");

        List<PreguntaOrg> preguntas = BDUtils.damePreguntas(Integer.parseInt(idOrg));

        res.type("application/json");

        if(preguntas.isEmpty()){
            res.status(400);
            return new mensaje("No hay preguntas").transformar();
        }

        res.status(200);

        return new Gson().toJson(new listaPreguntas(preguntas));

    }

    private static String indexLiviano(Request req, Response res) {

        String idSesion = req.queryParams("id");

        Usuario usuario = SesionManager.get().dameUsuario(idSesion);

        Map<String, Object> model = new HashMap<>();

        model.put("user",usuario);

        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "index.hbs"));
    }

    private static String iniciarSesionLiviano(Request request, Response res) {

        Map<String, Object> model = new HashMap<>();

        return new HandlebarsTemplateEngine().render(new ModelAndView(model, "iniciarSesion.hbs"));
    }

    private static String dameHogares(Request req, Response res) throws IOException {
        apiHogares api = apiHogares.getInstancia();
        List<Hogar> hogares = api.listadoDeHogares();

        res.type("application/json");

        if(hogares.isEmpty()){
            res.status(400);
            return new mensaje("No hay ningun hogar de transito").transformar();
        }

        res.status(200);

        return new Gson().toJson(new listaHogares(hogares));
    }


    private static String dameCaracteristicas(Request req, Response res) {
        String orgaId =  req.params(":id");

        List<CaracteristicaOrg> preguntas = BDUtils.dameCarateristicasOrg(Integer.parseInt(orgaId));

        res.type("application/json");

        res.status(200);

        return new Gson().toJson(new listaCarac(preguntas));
    }

    private static String agregarFotosMascota(Request req, Response res) {

        fotosMascota fotos = new Gson().fromJson(req.body(), fotosMascota.class);

        res.type("application/json");

        fotos.getFotos().forEach(foto -> BDUtils.agregarObjeto(foto));

        res.status(200);

        return new mensaje("Se agregaron las fotos").transformar();

    }

    private static String devolverMascotas(Request req, Response res) {

        String idSesion =  req.headers("Authorization");

        Usuario usuario = SesionManager.get().dameUsuario(idSesion);

        if(usuario == null){
            res.status(400);
            return new mensaje("No se valido el usuario").transformar();
        }

        DuenioBD duenio = BDUtils.dameDuenio(usuario.getId());

        List<mascotaSimple> mascotas = duenio.getMascotas().stream().map(mascotaBD -> new mascotaSimple(mascotaBD.transformar())).collect(Collectors.toList());

        if(mascotas.isEmpty()){
            res.status(400);
            return new mensaje("No hay mascotas para ese duenio").transformar();
        }

        res.status(200);
        return new Gson().toJson(new listaMasc(mascotas));
    }

    private static String DatosUsuario(Request req, Response res) {

        String idSesion =  req.headers("Authorization");

        Usuario usuario = SesionManager.get().dameUsuario(idSesion);

        PersonaBD persona = BDUtils.dameIdPersona(usuario.getId());

        if(usuario == null ){
            res.status(400);
            return new mensaje("No se valido el usuario").transformar();
        }
        Long persId;
        if(persona == null){
            persId = -1L;
        }
         else persId = persona.getPers_id();


        res.status(200);

        return new Gson().toJson(new userName(usuario.getNombre(), persId));

    }

    private static String devolverPublicacionesDarAdopcion(Request req, Response res) {


        res.type("application/json");

        res.status(200);


        List<PublicacionDarEnAdopcion> publicaciones = BDUtils.damePublicacionesAdopcion();

        if(publicaciones.isEmpty() || publicaciones == null){
            res.status(400);
            return new mensaje("No hay publicaciones").transformar();
        }

//        return (new devolverObjeto(publicaciones,("Aca tenes las publicaciones para el adoptante "+personaID))).transformar(); //es posible que rompa por el hashmap
        return new Gson().toJson(new listaPublicacion(publicaciones));
    }

    public static String crearUsuario(Request req, Response res) throws FileNotFoundException {

        UsuarioBD usuario = new Gson().fromJson(req.body(), UsuarioBD.class);

        res.type("application/json");

        if(!BDUtils.puedoEsteNombre(usuario.getUsu_nombre())) {
            res.status(400);
            return (new mensaje("El nombre de usuario no esta disponible")).transformar();
        }
        if(!BDUtils.puedoEsteMail(usuario.getUsu_mail())) {
            res.status(400);
            return (new mensaje("El email no esta disponible")).transformar();
        }
        if(!(Sistema.validarContrasenia(usuario.getUsu_contrasena(), usuario.getUsu_nombre()))) {
            res.status(400);
            return (new mensaje("La contrasenia ingresada no es valida")).transformar();
        }

        BDUtils.agregarObjeto(usuario);

        res.status(200);


       return (new usuarioCreado(usuario)).transformar();
    }

    public static String iniciarSesion(Request req, Response res){

        usuarioIniciarSesion usuario =  new Gson().fromJson(req.body(), usuarioIniciarSesion.class);

        res.type("application/json");

        if(!BDUtils.verificarContrasenia(usuario.getUsuario_Email(),usuario.getContrasenia())){
            res.status(400);
            return (new mensaje("Contrasenia o usuario incorrecto!").transformar());
        }

        Usuario usuarioFinal = BDUtils.dameUsuario(usuario.getUsuario_Email(),usuario.getContrasenia());

        SesionManager sesionManager = SesionManager.get();
        String idSesion = sesionManager.crear("usuario", usuarioFinal);

        res.status(200);

        return new loginResponse(idSesion, usuarioFinal.getTipoDeUsuario().toString()).transformar();
    }

    public static String crearDuenio(Request req, Response res){

        PersonaBD persona = new Gson().fromJson(req.body(), DuenioBD.class);

        String tipo = persona.getPers_usuario().getUsu_tipo();

        res.type("application/json");

        res.status(200);

        if(tipo.equalsIgnoreCase("ADOPTANTE")){
            AdoptanteBD adoptante = (AdoptanteBD) persona;

            BDUtils.agregarObjeto(adoptante);

            return new Gson().toJson(adoptante);

        }
        else if(tipo.equalsIgnoreCase("RESCATISTA")){
            RescatistaBD rescatistaBD = (RescatistaBD) persona;

            BDUtils.agregarObjeto(rescatistaBD);

            return new Gson().toJson(rescatistaBD);
        }else {
            DuenioBD duenio = (DuenioBD) persona;

            BDUtils.agregarObjeto(duenio);

            return new Gson().toJson(duenio);
        }
    }

    public static String crearOrganizacion(Request req, Response res){

        OrganizacionBD organizacion = new Gson().fromJson(req.body(), OrganizacionBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(organizacion);

        res.status(200);

        return (new mensaje("Organizacion creada").transformar());
    }


    public static String agregarNotificacionPers(Request req, Response res){
        FormaNotifPers formaNotif =  new Gson().fromJson(req.body(), FormaNotifPers.class);

        res.type("application/json");

        BDUtils.agregarObjeto(formaNotif);

        res.status(200);
        return (new mensaje("Notificacion agregada")).transformar();
    }

    public static String agregarContacto(Request req, Response res){
        ContactoBD contacto = new Gson().fromJson(req.body(), ContactoBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(contacto);

        res.status(200);

        return new Gson().toJson(contacto);
    }

    public static String agregarNotificacionCont(Request req, Response res){
        FormaNotifCont formaNotif =  new Gson().fromJson(req.body(), FormaNotifCont.class);

        res.type("application/json");

        BDUtils.agregarObjeto(formaNotif);

        res.status(200);
        return (new mensaje("Notificacion agregada")).transformar();
    }


    public static String crearMascota(Request req, Response res){
    
        MascotaBD mascotaBD =  new Gson().fromJson(req.body(), MascotaBD.class);

        //System.out.println(mascotaBD);
        
        res.type("application/json");

        BDUtils.agregarObjeto(mascotaBD);

        res.status(200);
        return new Gson().toJson(mascotaBD);
    }

    public static String devolverMascota(Request req, Response res){
        String mascotaID =  req.params(":id");

        res.type("application/json");

        MascotaBD mascota = BDUtils.buscarMascota(Integer.parseInt(mascotaID));

        if(mascota == null){
            res.status(400);
            return new mensaje("No encontre la mascota").transformar();
        }

        res.status(200);
        return (new devolverMascota(mascota)).transformar();
    }

    public static String agregarCaracteristicaMascota(Request req, Response res){

        caracMascota caracteristicas = new Gson().fromJson(req.body(), caracMascota.class);

        res.type("application/json");

        caracteristicas.getCaracteristicas().forEach(carac -> BDUtils.agregarObjeto(carac.getCarMasMas_carmas()));

        caracteristicas.getCaracteristicas().forEach(carac -> BDUtils.agregarObjeto(carac));

        res.status(200);

        return (new mensaje("Se agrego las caracteristicas a la mascota")).transformar();
    }

    public static String encontrarMascota(Request req, Response res){
        RescateBD rescateBD = new Gson().fromJson(req.body(), RescateBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(rescateBD);

        res.status(200);
        return (new mensaje("Se creo el rescate")).transformar();
    }

    public static String crearRescatista(Request req, Response res){
        RescatistaBD rescatistaBD = new Gson().fromJson(req.body(), RescatistaBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(rescatistaBD);

        res.status(200);
        return (new devolverObjeto(rescatistaBD, "Se creo un rescatista")).transformar();
    }

    public static String crearVoluntario(Request req, Response res){
        VoluntarioBD voluntarioBD = new Gson().fromJson(req.body(), VoluntarioBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(voluntarioBD);

        res.status(200);
        return (new devolverObjeto(voluntarioBD, "Se creo un voluntario")).transformar();
    }

    public static String agregarCaracteristicaAdmin(Request req, Response res){
        CaracteristicaMascota caracteristicas = new Gson().fromJson(req.body(), CaracteristicaMascota.class);

        res.type("application/json");

        BDUtils.agregarObjeto(caracteristicas);

        res.status(200);
        return (new mensaje("Se agrego la caracteristica correctamente").transformar());
    }

    private static String crearPublicacionAdoptar(Request req, Response res) {

        PublicacionAdoptarBD publicacion = new Gson().fromJson(req.body(), PublicacionAdoptarBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(publicacion);

        res.status(200);
        return new devolverObjeto(publicacion,"Se creo la publicacion de adoptar").transformar();
    }

    private static String crearPublicacionAdopcion(Request req, Response res) {

        PublicacionDarEnAdopcionBD publicacion = new Gson().fromJson(req.body(), PublicacionDarEnAdopcionBD.class);

        res.type("application/json");

        BDUtils.agregarObjeto(publicacion);

        res.status(200);
        return new devolverObjeto(publicacion,"Se creo la publicacion de dar en adopcion").transformar();
    }

    private static String agregarPreguntasPubli(Request req, Response res) {
       pregPublicacionDarEnAdopcionLista preguntas = new Gson().fromJson(req.body(), pregPublicacionDarEnAdopcionLista.class);

        res.type("application/json");

        preguntas.getPreguntas().forEach(preg -> BDUtils.agregarObjeto(preg));


        res.status(200);
        return (new mensaje("Se agregaron las preguntas correctamente").transformar());
    }

    private static String crearPublicacionPerdida(Request req, Response res) {

        PublicacionPerdidaBD publicacion = new Gson().fromJson(req.body(), PublicacionPerdidaBD.class);

        BDUtils.agregarObjeto(publicacion);

        return new mensaje("Se creo la publicacion!").transformar();


    }
























}


