

CREATE  TABLE IF NOT EXISTS elemento (
  id IDENTITY ,
  elemento varchar(30)(Normal,Fire,Fighting,Water,Flying,Grass,Poison,Electric,Ground,Psychic,Rock,Ice,Bug,Dragon,Ghost,Dark,Steel,  ) NOT NULL );


CREATE  TABLE IF NOT EXISTS ataque (
  id IDENTITY ,
  nome VARCHAR(45) NOT NULL ,
  atk INTEGER NOT NULL ,
  elemento INTEGER NOT NULL ,
  FOREIGN KEY (elemento) REFERENCES elemento (id));


CREATE  TABLE IF NOT EXISTS pokemon (
  id INTEGER NOT NULL ,
  nome VARCHAR(45) NOT NULL ,
  raridade INTEGER NOT NULL ,
  atkBase INTEGER NOT NULL ,
  defBase INTEGER NOT NULL ,
  spdBase INTEGER NOT NULL ,
  hpBase INTEGER NOT NULL ,
  baseExp INTEGER NOT NULL ,
  lvlQueEvolui INTEGER NULL DEFAULT NULL ,
  idAtaque INTEGER NOT NULL ,
  elementoPrimario INTEGER NOT NULL ,
  elementoSecundario INTEGER NULL ,
  PRIMARY KEY (id),
    FOREIGN KEY (idAtaque) REFERENCES ataque (id),
    FOREIGN KEY (elementoPrimario) REFERENCES elemento (id ),
    FOREIGN KEY (elementoSecundario) REFERENCES elemento(id));


CREATE  TABLE IF NOT EXISTS evolucaoporpedra (
  id INTEGER NOT NULL ,
  idPokemon INTEGER NOT NULL ,
  idEvolucao INTEGER NOT NULL ,
  elemento INTEGER NOT NULL ,
  PRIMARY KEY (id, idPokemon, idEvolucao, elemento) ,
    FOREIGN KEY (idPokemon )REFERENCES pokemon (id ),
    FOREIGN KEY (idEvolucao ) REFERENCES pokemon (id ),
    FOREIGN KEY (elemento ) REFERENCES elemento (id ));



CREATE  TABLE IF NOT EXISTS jogador (
  id IDENTITY ,
  nome VARCHAR(45) NOT NULL );




CREATE  TABLE IF NOT EXISTS pokemonderrotado (
  id INTEGER NOT NULL IDENTITY ,
  vezesDerrotado INTEGER NOT NULL DEFAULT 0 ,
  idPokemon INTEGER NOT NULL ,
    FOREIGN KEY (idPokemon )REFERENCES pokemon (id ));



CREATE  TABLE IF NOT EXISTS pokemonliberado (
  idJogador INTEGER NOT NULL ,
  idPokemon INTEGER NOT NULL ,
  lvlQueChegou INTEGER NULL DEFAULT 0 ,
  faseQueChegou INTEGER NULL DEFAULT 0 ,
  inimigosDerrotados INTEGER NULL DEFAULT 0 ,
  vezesQueZerouOJogo INTEGER NULL DEFAULT 0 ,
  vezesDerrotasParaNPC INTEGER NULL DEFAULT 0 ,
  totalDanoCausado INTEGER NULL DEFAULT 0 ,
  atk INTEGER NOT NULL ,
  def INTEGER NOT NULL ,
  spd INTEGER NOT NULL ,
  hp INTEGER NOT NULL ,
  lvl INTEGER NULL DEFAULT 1 ,
  exp INTEGER NULL DEFAULT 0 ,
  PRIMARY KEY (idJogador, idPokemon) ,
    FOREIGN KEY (idJogador ) REFERENCES jogador (id ),
    FOREIGN KEY (idPokemon ) REFERENCES pokemon (id ));



CREATE  TABLE IF NOT EXISTS experiencia (
  lvl INTEGER NOT NULL ,
  exp INTEGER NOT NULL ,
  PRIMARY KEY (lvl) );




CREATE  TABLE IF NOT EXISTS bonusDeElemento (
  elemento INTEGER NOT NULL ,
  elementoMultiplicador INTEGER NOT NULL ,
  multiplicador DOUBLE NOT NULL ,
  PRIMARY KEY (elemento, elementoMultiplicador) ,
    FOREIGN KEY (elemento ) REFERENCES elemento (id),
    FOREIGN KEY (elementoMultiplicador ) REFERENCES elemento (id));


