package be.g00glen00b.repository;

import be.g00glen00b.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
    @Override
    @Query("SELECT b FROM BlogPost b ORDER BY b.date DESC")
    List<BlogPost> findAll();
}
