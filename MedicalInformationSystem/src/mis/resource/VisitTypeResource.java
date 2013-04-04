package mis.resource;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import mis.dao.VisitTypeDao;
import mis.model.VisitType;

@Path("/visittype")
public class VisitTypeResource {

		UriInfo uriInfo;
		Request request;
		String id;
		
		@GET
		@Path("/{tenantid}")
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public List<VisitType> getAllDrug(@PathParam("tenantid") int tenantid) {
			List<VisitType> drugObjs = new ArrayList<VisitType>();
			drugObjs.addAll(VisitTypeDao.instance.getAllVisitType(tenantid).values());
			return drugObjs;
		}
		
		@GET
		@Path("/{tenantid}/{visittypeid}")
		@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
		public VisitType getDrug(@PathParam("tenantid") int tenantid, @PathParam("visittypeid") int visittypeid)
		{
			return VisitTypeDao.instance.getVisitTypeById(visittypeid);
		}
		
		@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response putDrug(VisitType visittype) {
			// return Response.status(201).entity(result).build();
			//System.out.println(patient.getUsername());
			return putAndGetResponse(visittype);
		}

		private Response putAndGetResponse(VisitType visittype) {
			Response res;
			// if (UserDao.instance.getAllUsers().containsKey(user.getUsername())) {
			// res = Response.noContent().build();
			// } else {
			// res = Response.created(uriInfo.getAbsolutePath()).build();
			// }
			if (VisitTypeDao.instance.putVisitDetails(visittype) != 0) {
				String result = "visit type updated";
				return Response.status(201).entity(result).build();
			} else {
				return Response.noContent().build();
			}
		}
}
