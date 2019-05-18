package com.tw.beach.splitwise.service;

import com.tw.beach.splitwise.entity.Friend;
import com.tw.beach.splitwise.repository.FriendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FriendService {
    private FriendRepository friendRepository = null;

    @Autowired
    FriendService(FriendRepository friendRepository) throws ServiceLayerException {
        this.friendRepository = friendRepository;
    }


    public Friend create(Friend friend) throws ServiceLayerException {
        try {
            Friend savedFriend = friendRepository.save(friend);
            return savedFriend;
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public Friend findFriend(Long friendId) throws ServiceLayerException {
        try {
            Friend friend = friendRepository
                    .findById(friendId)
                    .orElseThrow(() -> new RuntimeException("Friend not found on :: " + friendId));
            return friend;
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public List<Friend> findAll() throws ServiceLayerException {
        try {
            return friendRepository.findAll();
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public Friend delete(Long friendId) throws ServiceLayerException {
        try {
            Optional<Friend> friend = friendRepository.findById(friendId);
            friendRepository.delete(friend.get());
            return friend.get();
        } catch (Exception ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }
}
