package br.com.vittaneo.resource;

import br.com.vittaneo.dao.VooDAO;
import br.com.vittaneo.entities.Voo;
import br.com.vittaneo.exception.ErrorResponse;
import br.com.vittaneo.infra.DatabaseConfig;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.util.List;

@Path("/voos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class VooResource {

    private VooDAO dao;

    public VooResource() {
        DatabaseConfig.initialize();
        this.dao = new VooDAO();
    }

    @GET
    public Response listar() throws Exception {
        List<Voo> lista = dao.listar();
        return Response.ok(lista).build();
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) throws Exception {
        Voo v = dao.buscarPorId(id);
        if (v == null) return Response.status(404).entity(new ErrorResponse("Voo não encontrado")).build();
        return Response.ok(v).build();
    }

    @POST
    public Response inserir(Voo voo) throws Exception {
        dao.inserir(voo);
        return Response.status(201).header("Location", "/voos/" + voo.getId()).build();
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Voo voo) throws Exception {
        voo.setId(id);
        dao.atualizar(voo);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) throws Exception {
        dao.deletar(id);
        return Response.ok().build();
    }
}