package com.tw.beach.splitwise.web;

import com.tw.beach.splitwise.entity.Friend;
import com.tw.beach.splitwise.service.FriendService;
import com.tw.beach.splitwise.service.ServiceLayerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FriendController {
    private FriendService friendService = null;

    @Autowired
    FriendController(FriendService friendService) {
        this.friendService = friendService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/friends")
    public Friend createFriend(@RequestBody Friend friend) throws ServiceLayerException {
        return friendService.create(friend);
    }

    @GetMapping("/friends/{id}")
    public Friend fetchFriendById(@PathVariable(value = "id") Long friendId) throws ServiceLayerException {
        Friend friend = friendService.findFriend(friendId);
        return friend;
    }

    @GetMapping("/friends")
    public List<Friend> fetchAllFriends() throws ServiceLayerException {
        return friendService.findAll();
    }

    @DeleteMapping("friends/{id}")
    public Friend deleteFriendbyId(@PathVariable(value = "id") Long friendId) throws ServiceLayerException {
        return friendService.delete(friendId);
    }
}