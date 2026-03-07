package nandi.project.generated.service;

import nandi.project.generated.model.VideoGame;
import nandi.project.generated.repository.VideoGameRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class VideoGameService {

    private final VideoGameRepository repository;

    public VideoGameService(VideoGameRepository repository) {
        this.repository = repository;
    }

    public List<VideoGame> findAll() {
        return repository.findAll();
    }

    public Optional<VideoGame> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public VideoGame save(VideoGame data) {
        return repository.save(data);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}