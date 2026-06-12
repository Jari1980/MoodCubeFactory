CREATE TABLE cubes (
    id SERIAL PRIMARY KEY,
    color VARCHAR(20) NOT NULL,
    mood VARCHAR(20) NOT NULL,
    energy INT NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT NOW()
);

CREATE TABLE event_history (
    id SERIAL PRIMARY KEY,
    cube_id INT,
    event_type VARCHAR(50),
    event_data TEXT,
    created_at TIMESTAMP NOT NULL DEFAULT NOW()
);