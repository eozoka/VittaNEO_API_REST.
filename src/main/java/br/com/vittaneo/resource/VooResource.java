package br.com.vittaneo.resource;

import br.com.vittaneo.dao.VooDAO;
import br.com.vittaneo.entities.Voo;
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
    public Response listar() {
        try {
            List<Voo> lista = dao.listar();
            return Response.ok(lista).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response buscarPorId(@PathParam("id") int id) {
        try {
            Voo v = dao.buscarPorId(id);
            if (v == null) return Response.status(404).entity("Voo não encontrado").build();
            return Response.ok(v).build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @POST
    public Response inserir(Voo voo) {
        try {
            dao.inserir(voo);
            return Response.status(201).entity("Voo criado com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response atualizar(@PathParam("id") int id, Voo voo) {
        try {
            voo.setId(id);
            dao.atualizar(voo);
            return Response.ok("Voo atualizado com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") int id) {
        try {
            dao.deletar(id);
            return Response.ok("Voo deletado com sucesso").build();
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
    }
}