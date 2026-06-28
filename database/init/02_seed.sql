-- INSERT INTO cubes (color, mood, energy, updated_at)
-- VALUES
-- ('red', 'angry', 80, NOW()),
-- ('blue', 'calm', 60, NOW()),
-- ('green', 'happy', 90, NOW()),
-- ('yellow', 'excited', 70, NOW()),
-- ('purple', 'sleepy', 40, NOW());

-- Clear existing data when reseeding
TRUNCATE TABLE cubes RESTART IDENTITY CASCADE;

-- Generate 100 cubes
INSERT INTO cubes (color, mood, energy, updated_at)
SELECT
    CASE
        WHEN energy <= 30 THEN 'gray'
        WHEN energy <= 70 THEN 'blue'
        ELSE 'green'
    END AS color,

    CASE
        WHEN energy <= 30 THEN 'EXHAUSTED'
        WHEN energy <= 70 THEN 'NEUTRAL'
        ELSE 'ACTIVE'
    END AS mood,

    energy,
    NOW()
FROM (
    SELECT floor(random() * 101)::int AS energy
    FROM generate_series(1, 100)
) t;