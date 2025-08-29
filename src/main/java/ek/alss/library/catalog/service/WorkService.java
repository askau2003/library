package ek.alss.library.catalog.service;

import ek.alss.library.catalog.model.Work;
import ek.alss.library.catalog.repository.WorkRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkService {

    private final WorkRepository workRepository;

    public WorkService(WorkRepository workRepository) {
        this.workRepository = workRepository;
    }

    public Work createWork(Work work) {
        workRepository.save(work);
        return work;
    }

    public List<Work> getAllWorks() {
        return workRepository.findAll();
    }

    public Work getWorkById(Long id) {
        Optional<Work> work = workRepository.findById(id);
        if (work.isPresent()) {
            return work.get();
        }
        throw new RuntimeException("Work not found with id: " + id);
    }

    public Work updateWork(Long id, Work updatedWork) {
        Work work = this.getWorkById(id);
        work.setTitle(updatedWork.getTitle());
        work.setWorkType(updatedWork.getWorkType());
        work.setDetails(updatedWork.getDetails());
        work.setAuthors(updatedWork.getAuthors());
        work.setSubjects(updatedWork.getSubjects());
        return workRepository.save(work);
    }

    public void deleteWork(Long id) {
        Work work = this.getWorkById(id);
        workRepository.delete(work);
    }

    public List<Work> searchWorks(String title) {
        return workRepository.findByTitleContaining(title); // Fra WorkRepository interfacet.
    }
}

