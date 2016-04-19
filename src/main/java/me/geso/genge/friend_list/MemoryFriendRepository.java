package me.geso.genge.friend_list;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.profile.UserProfileResponseContact;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MemoryFriendRepository implements FriendRepository {
    @Autowired
    private LineBotClient lineBotClient;
    private Map<String, UserProfileResponseContact> contacts;

    public MemoryFriendRepository() {
        this.contacts = new HashMap<>();
    }

    @Override
    public void addedFriend(UserProfileResponseContact contact) throws LineBotAPIException {
        this.contacts.put(contact.getMid(), contact);
    }

    @Override
    public UserProfileResponseContact getContact(String mid) {
        return this.contacts.get(mid);
    }

    @Override
    public void blocked(String mid) {
        contacts.remove(mid);
    }

    @Override
    public Set<String> getMids() {
        return contacts.keySet();
    }
}
