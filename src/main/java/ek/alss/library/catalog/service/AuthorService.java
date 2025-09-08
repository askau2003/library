package ek.alss.library.catalog.service;

import ek.alss.library.catalog.dto.AuthorDto;
import ek.alss.library.catalog.dto.Mapper;
import ek.alss.library.catalog.exception.NotFoundException;
import ek.alss.library.catalog.model.Author;
import ek.alss.library.catalog.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public AuthorDto createAuthor(AuthorDto authorDto) {
        Author author = Mapper.toEntity(authorDto);
        author.setId(null);
        return Mapper.toDto(authorRepository.save(author));
    }

    public List<AuthorDto> getAllAuthors() {
        List<Author> authors = authorRepository.findAll();

        if(authors.isEmpty()) {
            throw new NotFoundException("No authors found");
        }

        List<AuthorDto> authorDtos = new ArrayList<>();
        for (var author : authors) {
            authorDtos.add(Mapper.toDto(author));
        }
        return authorDtos;
    }

    public AuthorDto getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            return Mapper.toDto(author.get());
        }
        throw new NotFoundException("Author not found with id: " + id);
    }

    public AuthorDto updateAuthor(Long id, AuthorDto authorDto) {
        Optional<Author> existingAuthor = authorRepository.findById(id);
        if(existingAuthor.isPresent()) {
            Author author = Mapper.toEntity(authorDto);
            Author updatedAuthor = existingAuthor.get();
            updatedAuthor.setId(author.getId());
            updatedAuthor.setName(author.getName());
            return Mapper.toDto(authorRepository.save(updatedAuthor));
        }
        throw new NotFoundException("Author not found with id: " + id);
    }

    public void deleteAuthor(Long id) {
        if (authorRepository.existsById(id)) {
            authorRepository.deleteById(id);
        } else {
            throw new NotFoundException("Author not found with id: " + id);
        }
    }

    public List<AuthorDto> searchAuthors(String name) {
        List<Author> authors = authorRepository.findByNameContaining(name);

        if(authors.isEmpty()) {
            throw new NotFoundException("No authors found with name: " + name);
        }

        List<AuthorDto> authorDtos = new ArrayList<>();
        for (var author : authors) {
            authorDtos.add(Mapper.toDto(author));
        }
        return authorDtos;
    }
}