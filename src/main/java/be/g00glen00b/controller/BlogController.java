package be.g00glen00b.controller;

import be.g00glen00b.dto.BlogPostDTO;
import be.g00glen00b.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/blog")
public class BlogController {
    @Autowired
    private BlogPostService service;

    @RequestMapping(method = RequestMethod.GET)
    public List<BlogPostDTO> getBlogPosts() {
        return service.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public BlogPostDTO save(@Valid @RequestBody BlogPostDTO dto) {
        return service.save(dto);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}")
    public BlogPostDTO update(@PathVariable Long id, @Valid @RequestBody BlogPostDTO dto) {
        return service.update(id, dto);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
