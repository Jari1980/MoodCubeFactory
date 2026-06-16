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
INSERT INTO cubes (
    color,
    mood,
    energy,
    updated_at
)
SELECT
    (ARRAY['green', 'blue', 'yellow', 'red', 'purple'])
        [floor(random() * 5 + 1)],
    (ARRAY['balanced', 'calm', 'unstable', 'angry', 'chaotic'])
        [floor(random() * 5 + 1)],
    floor(random() * 101)::int,
    NOW()
FROM generate_series(1, 100);