package com.mitsu.mitsu.repository;

import com.mitsu.mitsu.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m WHERE " +
            "((m.sender.userId = :userId1 AND m.receiver.userId = :userId2) OR " +
            "(m.sender.userId = :userId2 AND m.receiver.userId = :userId1)) " +
            "AND m.isSystem = false " +
            "ORDER BY m.sentAt ASC")
    List<Message> findConversation(@Param("userId1") Long userId1, @Param("userId2") Long userId2);

    List<Message> findByReceiverUserIdAndIsReadFalse(Long receiverId);

    List<Message> findByReceiverUserIdAndIsSystemTrue(Long receiverId);

    @Query("SELECT DISTINCT CASE WHEN m.sender.userId = :userId THEN m.receiver ELSE m.sender END " +
            "FROM Message m WHERE (m.sender.userId = :userId OR m.receiver.userId = :userId) AND m.isSystem = false")
    List<Object> findConversationPartners(@Param("userId") Long userId);
}
