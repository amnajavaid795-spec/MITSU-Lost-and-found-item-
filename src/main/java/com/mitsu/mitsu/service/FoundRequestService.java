package com.mitsu.mitsu.service;

import com.mitsu.mitsu.enums.ItemStatus;
import com.mitsu.mitsu.model.FoundRequest;
import com.mitsu.mitsu.model.LostItem;
import com.mitsu.mitsu.model.User;
import com.mitsu.mitsu.repository.FoundRequestRepository;
import com.mitsu.mitsu.repository.LostItemRepository;
import com.mitsu.mitsu.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoundRequestService {

    @Autowired private FoundRequestRepository foundRequestRepository;
    @Autowired private LostItemRepository lostItemRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private UserService userService;
    @Autowired private MessageService messageService;

    public FoundRequest submitFoundRequest(Long itemId, Long finderId, String msg) {
        LostItem item = lostItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Item not found"));
        User finder = userRepository.findById(finderId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoundRequest req = new FoundRequest();
        req.setItem(item);
        req.setFinder(finder);
        req.setMessage(msg);
        req.setStatus("PENDING");
        FoundRequest saved = foundRequestRepository.save(req);

        User owner = item.getReportedBy();
        if (owner != null && !owner.getUserId().equals(finderId)) {
            messageService.sendSystemMessage(owner, item,
                    "🔍 Mitsu: " + finder.getName() + " says they found your lost item \"" +
                            item.getItemName() + "\". Their message: \"" + msg +
                            "\". Go to your posted items to accept or reject this.");
        }

        return saved;
    }

    public FoundRequest acceptFoundRequest(Long requestId) {
        FoundRequest req = foundRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("ACCEPTED");
        foundRequestRepository.save(req);

        LostItem item = req.getItem();
        item.setStatus(ItemStatus.CLAIMED);
        lostItemRepository.save(item);

        userService.addPoints(req.getFinder().getUserId(), 30);

        messageService.sendSystemMessage(req.getFinder(), item,
                "✅ Mitsu: " + item.getReportedBy().getName() + " confirmed that you found their lost item \"" +
                        item.getItemName() + "\"! Thank you for your honesty. +30 points awarded! 🎉");

        return req;
    }

    public FoundRequest rejectFoundRequest(Long requestId) {
        FoundRequest req = foundRequestRepository.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Request not found"));

        req.setStatus("REJECTED");
        foundRequestRepository.save(req);

        messageService.sendSystemMessage(req.getFinder(), req.getItem(),
                "❌ Mitsu: The owner reviewed your found request for \"" + req.getItem().getItemName() +
                        "\" and indicated that the item you found does not match theirs. The item remains listed as lost.");

        return req;
    }

    public List<FoundRequest> getByItem(Long itemId) {
        return foundRequestRepository.findByItemItemId(itemId);
    }

    public List<FoundRequest> getByFinder(Long userId) {
        return foundRequestRepository.findByFinderUserId(userId);
    }
}
