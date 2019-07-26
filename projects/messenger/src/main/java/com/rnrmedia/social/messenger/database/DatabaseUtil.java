package com.rnrmedia.social.messenger.database;

import java.util.Map;
import java.util.HashMap;

import com.rnrmedia.social.messenger.model.Message;
import com.rnrmedia.social.messenger.model.Profile;


public class DatabaseUtil {

    private static Map<Long, Message> messages = new HashMap<Long, Message>();
    private static Map<String, Profile> profiles  = new HashMap<String, Profile>();


    public static Map<Long, Message> getMessages() {
        return messages;
    }

    public static Map<String, Profile> getProfiles() {
        return profiles;
    }
}
