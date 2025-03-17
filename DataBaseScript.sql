CREATE TABLE pokemon (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    altura NUMBER,
    peso NUMBER,
    url_imagen VARCHAR2(255)
);

CREATE TABLE tipo (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) UNIQUE NOT NULL
);

CREATE TABLE pokemon_tipo (
    id_pokemon NUMBER,
    id_tipo NUMBER,
    PRIMARY KEY (id_pokemon, id_tipo),
    FOREIGN KEY (id_pokemon) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (id_tipo) REFERENCES tipo(id) ON DELETE CASCADE
);

CREATE TABLE habilidad (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(100) UNIQUE NOT NULL
);

CREATE TABLE pokemon_habilidad (
    id_pokemon NUMBER,
    id_habilidad NUMBER,
    PRIMARY KEY (id_pokemon, id_habilidad),
    FOREIGN KEY (id_pokemon) REFERENCES pokemon(id) ON DELETE CASCADE,
    FOREIGN KEY (id_habilidad) REFERENCES habilidad(id) ON DELETE CASCADE
);

/
