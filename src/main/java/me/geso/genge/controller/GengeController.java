package me.geso.genge.controller;

import com.linecorp.bot.client.LineBotClient;
import com.linecorp.bot.client.exception.LineBotAPIException;
import com.linecorp.bot.model.callback.Message;
import com.linecorp.bot.model.content.AddedAsFriendOperation;
import com.linecorp.bot.model.content.BlockedOperation;
import com.linecorp.bot.model.content.Content;
import com.linecorp.bot.model.content.TextContent;
import com.linecorp.bot.model.profile.UserProfileResponse;
import com.linecorp.bot.spring.boot.annotation.LineBotMessages;
import lombok.extern.slf4j.Slf4j;
import me.geso.genge.friend_list.FriendRepository;
import me.geso.genge.handler.HandlerResponse;
import me.geso.genge.handler.TextContentHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
public class GengeController {
    @Autowired
    private List<TextContentHandler> handlers;
    @Autowired
    private FriendRepository friendRepository;
    @Autowired
    private LineBotClient lineBotClient;

    @RequestMapping("/callback")
    public void callback(@LineBotMessages List<Message> messages) throws LineBotAPIException {
        for (Message message : messages) {
            this.handle(message);
        }
    }

    private void handle(Message message) throws LineBotAPIException {
        Content content = message.getContent();

        if (content instanceof AddedAsFriendOperation) {
            String mid = ((AddedAsFriendOperation) content).getMid();
            UserProfileResponse userProfile = lineBotClient.getUserProfile(Collections.singletonList(mid));
            friendRepository.addedFriend(userProfile.getContacts().get(0));
        }

        if (content instanceof BlockedOperation) {
            String mid = ((BlockedOperation) content).getMid();
            friendRepository.blocked(mid);
        }

        if (content instanceof TextContent) {
            for (TextContentHandler handler : handlers) {
                HandlerResponse told = handler.told((TextContent) content);
                if (told == HandlerResponse.OK) {
                    return;
                }
            }
        }
    }
}
