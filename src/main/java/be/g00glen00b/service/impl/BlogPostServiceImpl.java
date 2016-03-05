package be.g00glen00b.service.impl;

import be.g00glen00b.dto.BlogPostDTO;
import be.g00glen00b.entity.BlogPost;
import be.g00glen00b.repository.BlogPostRepository;
import be.g00glen00b.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BlogPostServiceImpl implements BlogPostService {
    @Autowired
    private BlogPostRepository repository;

    @Override
    public List<BlogPostDTO> findAll() {
        return repository.findAll().stream().map(this::map).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public BlogPostDTO save(BlogPostDTO dto) {
        final BlogPost newEntity = new BlogPost(null, dto.getTitle(), dto.getText(), LocalDate.now());
        final BlogPost entity = repository.saveAndFlush(newEntity);
        return map(entity);
    }

    @Override
    @Transactional
    public BlogPostDTO update(Long id, BlogPostDTO dto) {
        final BlogPost entity = findOneSafe(id);
        entity.setText(dto.getText());
        entity.setTitle(dto.getTitle());
        return map(entity);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        final BlogPost entity = findOneSafe(id);
        repository.delete(entity);
    }

    private BlogPost findOneSafe(Long id) {
        final BlogPost entity = repository.findOne(id);
        if (entity != null) {
            return entity;
        } else {
            throw new IllegalArgumentException("No valid blogpost given");
        }
    }

    private BlogPostDTO map(BlogPost entity) {
        return new BlogPostDTO(entity.getId(), entity.getTitle(), entity.getText(), entity.getDate());
    }
}
