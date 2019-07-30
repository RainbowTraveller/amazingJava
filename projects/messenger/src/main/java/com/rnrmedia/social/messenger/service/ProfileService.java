package com.rnrmedia.social.messenger.service;

import java.util.List;
import java.util.LinkedList;
import java.util.Map;

import com.rnrmedia.social.messenger.database.DatabaseUtil;
import com.rnrmedia.social.messenger.model.Profile;

public class ProfileService {

    private Map<String,Profile> profiles = DatabaseUtil.getProfiles();


    public ProfileService() {
        profiles.put("Holmes", new Profile(1L, "Baker", "Sherlock", "Holmes"));
        profiles.put("Bro", new Profile(2L, "Ebinburgh", "Mycroft", "Holmes"));
        profiles.put("Enemy", new Profile(3L, "Cardiff", "Moriarty", "Debrah"));
    }

    public List<Profile> getAllProfiles() {
        return new LinkedList<Profile>(profiles.values());
    }


    public Profile getProfile(String profileName) {
        return profiles.get(profileName);

    }

    public Profile addProfile(Profile profile) {
        profile.setId(profiles.size() + 1);
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile updateProfile(Profile profile) {
        if(profile.getProfileName().isEmpty()) {
            return null;
        }
        profiles.put(profile.getProfileName(), profile);
        return profile;
    }

    public Profile removeProfile( String profileName) {
        return profiles.remove( profileName );
    }
}
