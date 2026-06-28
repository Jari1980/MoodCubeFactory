package repository;

import model.Cube;

import java.util.List;
import java.util.Optional;

public interface CubeRepository {
    List<Cube> getAll();
    Optional<Cube> getById(int id);
    Cube update(Cube cube);
    void resetFactory();
}
