package com.example.webapplicationboot.repository;

import com.example.webapplicationboot.entity.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LikeRepository extends JpaRepository<Like, Long> {
    List<Like> findAllByPostId(long postId);
}
