--Se crea una vista materializada ya que no es frecuente que se creen nuevos pokemones entonces, es mucho màs rapido el acceso a la consulta.
--La vista es materializada ya que se actualiza a demanda, si hay una nueva generaciòn de pokemones la consulta se actualizara una vez 
CREATE MATERIALIZED VIEW pokemon_report
BUILD IMMEDIATE
REFRESH ON DEMAND
AS
SELECT 
    p.id, 
    p.name AS pokemon_name, 
    p.height, 
    p.weight, 
    p.image_url, 
    t.name AS type_name, 
    a.name AS ability_name, 
    m.name AS move_name, 
    m.power AS move_power, 
    m.damage_area AS move_damage_area, 
    e.evolved_pokemon_id, 
    ep.name AS evolved_pokemon_name
FROM pokemon p
LEFT JOIN pokemon_type pt ON p.id = pt.pokemon_id
LEFT JOIN type t ON pt.type_id = t.id
LEFT JOIN pokemon_ability pa ON p.id = pa.pokemon_id
LEFT JOIN ability a ON pa.ability_id = a.id
LEFT JOIN pokemon_move pm ON p.id = pm.pokemon_id
LEFT JOIN move m ON pm.move_id = m.id
LEFT JOIN pokemon_evolution e ON p.id = e.base_pokemon_id
LEFT JOIN pokemon ep ON e.evolved_pokemon_id = ep.id;

--Para obtener los datos, se crea el sigiente query 


SELECT * FROM pokemon_report
WHERE (pokemon_name = '&pokemon_name' OR '&pokemon_name' IS NULL)
AND (type_name = '&type_name' OR '&type_name' IS NULL)
AND (ability_name = '&ability_name' OR '&ability_name' IS NULL)
AND (move_name = '&move_name' OR '&move_name' IS NULL);


MYSQL 

CREATE TABLE `Pokeapi`.pokemon_report (
    id INT PRIMARY KEY,
    pokemon_name VARCHAR(255),
    height INT,
    weight INT,
    image_url VARCHAR(500),
    type_name VARCHAR(255),
    ability_name VARCHAR(255),
    move_name VARCHAR(255),
    move_power INT,
    move_damage_area VARCHAR(255),
    evolved_pokemon_id INT,
    evolved_pokemon_name VARCHAR(255)
);

CREATE PROCEDURE `Pokeapi`.refresh_pokemon_report()
BEGIN
    DELETE FROM `Pokeapi`.pokemon_report;
    
    INSERT INTO `Pokeapi`.pokemon_report (
        id, pokemon_name, height, weight, image_url, type_name, ability_name, 
        move_name, move_power, move_damage_area, evolved_pokemon_id, evolved_pokemon_name
    )
    SELECT 
        p.id, 
        p.name AS pokemon_name, 
        p.height, 
        p.weight, 
        p.image_url, 
        t.name AS type_name, 
        a.name AS ability_name, 
        m.name AS move_name, 
        m.power AS move_power, 
        m.damage_area AS move_damage_area, 
        e.evolved_pokemon_id, 
        ep.name AS evolved_pokemon_name
    FROM `Pokeapi`.pokemon p
    LEFT JOIN pokemon_type pt ON p.id = pt.pokemon_id
    LEFT JOIN type t ON pt.type_id = t.id
    LEFT JOIN pokemon_ability pa ON p.id = pa.pokemon_id
    LEFT JOIN ability a ON pa.ability_id = a.id
    LEFT JOIN pokemon_move pm ON p.id = pm.pokemon_id
    LEFT JOIN move m ON pm.move_id = m.id
    LEFT JOIN pokemon_evolution e ON p.id = e.base_pokemon_id
    LEFT JOIN pokemon ep ON e.evolved_pokemon_id = ep.id;
END;

SELECT * FROM pokemon_report
WHERE (pokemon_name = COALESCE(NULLIF('', ''), pokemon_name))
AND (type_name = COALESCE(NULLIF('Fire', ''), type_name))
AND (ability_name = COALESCE(NULLIF('', ''), ability_name))
AND (move_name = COALESCE(NULLIF('', ''), move_name));


