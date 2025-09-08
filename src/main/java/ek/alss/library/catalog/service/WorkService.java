package ek.alss.library.catalog.service;

import ek.alss.library.catalog.dto.Mapper;
import ek.alss.library.catalog.dto.WorkDto;
import ek.alss.library.catalog.exception.NotFoundException;
import ek.alss.library.catalog.model.Work;
import ek.alss.library.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public WorkDto createWork(WorkDto workDto) {
        Work work = Mapper.toEntity(workDto);
        work.setId(null);
        return Mapper.toDto(workRepository.save(work));
    }

    public List<WorkDto> getAllWorks() {
        List<Work> works = workRepository.findAll();

        if (works.isEmpty()) {
            throw new NotFoundException("No works found");
        }

        List<WorkDto> workDtos = new ArrayList<>();
        for (var work : works) {
            workDtos.add(Mapper.toDto(work));
        }
        return workDtos;
    }

    public WorkDto getWorkById(Long id) {
        Optional<Work> work = workRepository.findById(id);

        if (work.isEmpty()) {
            throw new NotFoundException("No works found with id: " + id);
        }
        return Mapper.toDto(work.get());
    }

    public WorkDto updateWork(Long id, WorkDto workDto) {
        Optional<Work> existingWork = workRepository.findById(id);
        if (existingWork.isPresent()) {
            Work work = Mapper.toEntity(workDto);
            Work updatedWork = existingWork.get();
            updatedWork.setTitle(work.getTitle());
            updatedWork.setWorkType(work.getWorkType());
            updatedWork.setDetails(work.getDetails());
            updatedWork.setAuthors(work.getAuthors());
            updatedWork.setSubjects(work.getSubjects());
            return Mapper.toDto(workRepository.save(updatedWork));
        }
        throw new NotFoundException("Work not found with id: " + id);
    }

    public void deleteWork(Long id) {
        if (workRepository.existsById(id)) {
            workRepository.deleteById(id);
        } else {
            throw new NotFoundException("Work not found with id: " + id);
        }
    }

    public List<WorkDto> searchWorks(String title) {
        List<Work> works = workRepository.findByTitleContaining(title);

        if (works.isEmpty()) {
            throw new NotFoundException("No works found with title: " + title);
        }

        List<WorkDto> workDtos = new ArrayList<>();
        for (var work : works) {
            workDtos.add(Mapper.toDto(work));
        }
        return workDtos;
    }
}