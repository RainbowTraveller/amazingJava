import java.util.Calendar;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Calendar;
import java.util.Date;

@Path("dater")
public class DateReturningResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Date getDate() {
        return Calendar.getInstance().getTime();
    }
}

