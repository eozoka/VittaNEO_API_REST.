package br.com.vittaneo.resource;

import br.com.vittaneo.dao.DestinoDAO;
import br.com.vittaneo.entities.Destino;
import br.com.vittaneo.exception.ErrorResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/destinos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class DestinoResource {

    private DestinoDAO dao = new DestinoDAO();

    @GET
    public Response listar() throws Exception {
        List<Destino> lista = dao.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) throws Exception {
        Destino d = dao.buscarPorId(id);
        if (d == null) return Response.status(404).entity(new ErrorResponse("Destino não encontrado")).build();
        return Response.ok(d).build();
    }

    @POST
    public Response inserir(Destino destino) throws Exception {
        dao.inserir(destino);
        return Response.status(201).header("Location", "/destinos/" + destino.getId()).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Destino destino) throws Exception {
        destino.setId(id);
        dao.atualizar(destino);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws Exception {
        dao.deletar(id);
        return Response.ok().build();
    }
}