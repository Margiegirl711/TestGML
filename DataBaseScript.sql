CREATE TABLE pokemon ( --General information of Pokemon
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) NOT NULL,
    height NUMBER,
    weight NUMBER,
    image_url VARCHAR2(255)
);

CREATE TABLE type ( --Types of pokemon (water, fire wind, etc )
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL
);

CREATE TABLE pokemon_type ( -- relation between pokemon and types 
    pokemon_id NUMBER,
    type_id NUMBER,
    PRIMARY KEY (pokemon_id, type_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (type_id) REFERENCES type(id) ON DELETE CASCADE
);

CREATE TABLE ability ( --list of abilities 
    id NUMBER PRIMARY KEY,
    name VARCHAR2(100) UNIQUE NOT NULL,
    description varchar(200)
);

CREATE TABLE pokemon_ability ( --relation between pokemon and abilities
    pokemon_id NUMBER,
    ability_id NUMBER,
    PRIMARY KEY (pokemon_id, ability_id),
    FOREIGN KEY (pokemon_id) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (ability_id) REFERENCES ability(id) ON DELETE CASCADE
);
