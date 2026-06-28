package repository;

import config.DatabaseConfig;
import model.Cube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PostgresCubeRepository implements CubeRepository{

    @Override
    public List<Cube> getAll() {
        List<Cube> cubes = new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM cubes
                ORDER BY id
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
                 while (rs.next()) {
                     cubes.add(
                             new Cube(
                                     rs.getInt("id"),
                                     rs.getString("color"),
                                     rs.getString("mood"),
                                     rs.getInt("energy"),
                                     rs.getTimestamp("updated_at")
                             )
                     );
                 }
        } catch(Exception e){
                 throw new RuntimeException(e);
        }

        return cubes;
    }

    @Override
    public Optional<Cube> getById(int id) {

        String sql =
                """
                SELECT *
                FROM cubes
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(
                            new Cube(
                                    rs.getInt("id"),
                                    rs.getString("color"),
                                    rs.getString("mood"),
                                    rs.getInt("energy"),
                                    rs.getTimestamp("updated_at")
                            )
                    );
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Cube update(Cube cube) {

        String sql =
                """
                UPDATE cubes
                SET color = ?,
                    mood = ?,
                    energy = ?,
                    updated_at = NOW()
                WHERE id = ?
                """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cube.getColor());
            stmt.setString(2, cube.getMood());
            stmt.setInt(3, cube.getEnergy());
            stmt.setInt(4, cube.getId());

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return getById(cube.getId())
                .orElseThrow();
    }

    @Override
    public void resetFactory() {

        String sql = """
                UPDATE cubes c
                SET
                    energy = t.energy,
                    mood = CASE
                        WHEN t.energy <= 30 THEN 'EXHAUSTED'
                        WHEN t.energy <= 70 THEN 'NEUTRAL'
                        ELSE 'ACTIVE'
                    END,
                    color = CASE
                        WHEN t.energy <= 30 THEN 'gray'
                        WHEN t.energy <= 70 THEN 'blue'
                        ELSE 'green'
                    END,
                    updated_at = NOW()
                FROM (
                    SELECT id, floor(random() * 101)::int AS energy
                    FROM cubes
                ) t
                WHERE c.id = t.id;
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException("Failed to reset factory", e);
        }
    }

    @Override
    public void rainbowStorm() {

        String sql = """
        UPDATE cubes
        SET
            color = (ARRAY['green','blue','yellow','red','purple'])
                [floor(random() * 5 + 1)],
            updated_at = NOW()
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void drainEnergy() {

        String sql = """
        UPDATE cubes
        SET
            energy = GREATEST(energy - 20, 0),
            mood = CASE
                WHEN GREATEST(energy - 20, 0) <= 30 THEN 'EXHAUSTED'
                WHEN GREATEST(energy - 20, 0) <= 70 THEN 'NEUTRAL'
                ELSE 'ACTIVE'
            END,
            color = CASE
                WHEN GREATEST(energy - 20, 0) <= 30 THEN 'gray'
                WHEN GREATEST(energy - 20, 0) <= 70 THEN 'blue'
                ELSE 'green'
            END,
            updated_at = NOW()
        """;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
