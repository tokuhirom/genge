package me.geso.genge.friend_list;

import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.profile.UserProfileResponseContact;

import java.util.Set;

public interface FriendRepository {
    void addedFriend(UserProfileResponseContact contact) throws LineBotAPIException;

    UserProfileResponseContact getContact(String mid);

    void blocked(String mid);

    Set<String> getMids();
}
