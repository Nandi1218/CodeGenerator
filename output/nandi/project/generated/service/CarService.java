package nandi.project.generated.service;

import nandi.project.generated.model.Car;
import nandi.project.generated.repository.CarRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> findAll() {
        return repository.findAll();
    }

    public Optional<Car> findById(Long id) {
        return repository.findById(id);
    }

    @Transactional
    public Car save(Car data) {
        return repository.save(data);
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}