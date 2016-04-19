package me.geso.genge.config;

import me.geso.genge.friend_list.FriendRepository;
import me.geso.genge.friend_list.MemoryFriendRepository;
import me.geso.genge.storage.InMemoryKeyValueStorage;
import me.geso.genge.storage.KeyValueStorage;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InMemoryConfig {
    @Bean
    @ConditionalOnMissingBean(KeyValueStorage.class)
    public KeyValueStorage<String> inMemoryKeyValueStorage() {
        return new InMemoryKeyValueStorage<>();
    }

    @Bean
    @ConditionalOnMissingBean(FriendRepository.class)
    public FriendRepository friendRepository() {
        return new MemoryFriendRepository();
    }
}
