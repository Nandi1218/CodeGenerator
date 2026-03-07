package nandi.project.generated.service;

import nandi.project.generated.model.User;
import nandi.project.generated.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public User save(User data) {
        return repository.save(data);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}