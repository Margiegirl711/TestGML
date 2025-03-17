CREATE TABLE pokemon (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    height NUMBER,
    weight NUMBER,
    image_url VARCHAR2(255),
    evolution_stage NUMBER DEFAULT 1
);

CREATE TABLE type (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL
);

CREATE TABLE pokemon_type (
    pokemon_id NUMBER,
    type_id NUMBER,
    PRIMARY KEY (pokemon_id, type_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES type(id) ON DELETE CASCADE
);

CREATE TABLE ability (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL,
    description VARCHAR2(255)
);

CREATE TABLE pokemon_ability (
    pokemon_id NUMBER,
    ability_id NUMBER,
    PRIMARY KEY (pokemon_id, ability_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE
);

CREATE TABLE move (
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL,
    type_id NUMBER,
    power NUMBER,
    accuracy NUMBER,
    times_of_use NUMBER, 
    damage_area VARCHAR2(50), 
    description VARCHAR2(255),
    FOREIGN KEY (type_id) REFERENCES type(id) ON DELETE CASCADE
);

CREATE TABLE pokemon_move (
    pokemon_id NUMBER,
    move_id NUMBER,
    PRIMARY KEY (pokemon_id, move_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (move_id) REFERENCES move(id) ON DELETE CASCADE
);

CREATE TABLE pokemon_evolution (
    base_pokemon_id NUMBER,
    evolved_pokemon_id NUMBER,
    evolution_trigger VARCHAR2(100),
    level_required NUMBER,
    PRIMARY KEY (base_pokemon_id, evolved_pokemon_id),
    FOREIGN KEY (base_pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (evolved_pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE
);
