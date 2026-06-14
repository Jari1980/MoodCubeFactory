package repository;

import config.DatabaseConfig;
import model.Cube;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
}
