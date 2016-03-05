package be.g00glen00b.service;

import be.g00glen00b.dto.BlogPostDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BlogPostService {
    List<BlogPostDTO> findAll();

    @Transactional
    BlogPostDTO save(BlogPostDTO dto);

    @Transactional
    BlogPostDTO update(Long id, BlogPostDTO dto);

    @Transactional
    void delete(Long id);
}
