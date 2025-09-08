package ek.alss.library.catalog.service;

import ek.alss.library.catalog.dto.SubjectDto;
import ek.alss.library.catalog.dto.Mapper;
import ek.alss.library.catalog.exception.NotFoundException;
import ek.alss.library.catalog.model.Subject;
import ek.alss.library.catalog.repository.SubjectRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public SubjectService(SubjectRepository subjectRepository) {
        this.subjectRepository = subjectRepository;
    }

    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = Mapper.toEntity(subjectDto);
        subject.setId(null);
        return Mapper.toDto(subjectRepository.save(subject));
    }

    public List<SubjectDto> getAllSubjects() {
        List<Subject> subjects = subjectRepository.findAll();

        if (subjects.isEmpty()) {
            throw new NotFoundException("No subjects found");
        }

        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (var subject : subjects) {
            subjectDtos.add(Mapper.toDto(subject));
        }
        return subjectDtos;
    }

    public SubjectDto getSubjectById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);

        if (subject.isEmpty()) {
            throw new NotFoundException("No subjects found with id: " + id);
        }
        return Mapper.toDto(subject.get());
    }

    public SubjectDto updateSubject(Long id, SubjectDto subjectDto) {
        Optional<Subject> existingSubject = subjectRepository.findById(id);
        if (existingSubject.isPresent()) {
            Subject subject = Mapper.toEntity(subjectDto);
            Subject updatedSubject = existingSubject.get();
            updatedSubject.setId(subject.getId());
            updatedSubject.setName(subject.getName());
            return Mapper.toDto(subjectRepository.save(updatedSubject));
        }
        throw new NotFoundException("Subject not found with id: " + id);
    }

    public void deleteSubject(Long id) {
        if (subjectRepository.existsById(id)) {
            subjectRepository.deleteById(id);
        } else {
            throw new NotFoundException("Subject not found with id: " + id);
        }
    }

    public List<SubjectDto> searchSubjects(String name) {
        List<Subject> subjects = subjectRepository.findByNameContaining(name);

        if(subjects.isEmpty()) {
            throw new NotFoundException("No subjects found with name: " + name);
        }

        List<SubjectDto> subjectDtos = new ArrayList<>();
        for (var subject : subjects) {
            subjectDtos.add(Mapper.toDto(subject));
        }
        return subjectDtos;
    }
}
