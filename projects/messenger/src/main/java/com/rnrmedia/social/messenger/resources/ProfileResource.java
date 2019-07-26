package com.rnrmedia.social.messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import com.rnrmedia.social.messenger.service.ProfileService;

@Path("/profiles")
@Consumes(MetaType.APPLICATION_JSON)
@Produces(MetaType.APPLICATION_JSON)
public class ProfileResource {
    private ProfileService profileService = new ProfileService();

    @GET
    public List<Profile> getProfiles() {
        profileService.getAllProfiles();
    }

    @GET
    @Path("/{profileName}")
    public Profile getProfile(@PathParam("profileName") String profileName) {
        return profileService.getProfile(profileNam);
    }

}
