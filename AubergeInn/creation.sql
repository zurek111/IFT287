DROP TABLE Client CASCADE;
CREATE TABLE Client(
	idClient INTEGER,
	prenom   VARCHAR(255) NOT NULL,
	nom      VARCHAR(255) NOT NULL,
	age      INTEGER      NOT NULL CHECK(age >= 0),
	
	CONSTRAINT pk_idClient PRIMARY KEY (idClient)
);

DROP TABLE Chambre CASCADE;
CREATE TABLE Chambre(
	idChambre INTEGER,
	nom       VARCHAR(255) NOT NULL,
	typeLit   VARCHAR(255) NOT NULL,
	prix      INTEGER      NOT NULL CHECK(prix >= 0),

	CONSTRAINT pk_idChambre PRIMARY KEY (idChambre)
);

DROP TABLE Commodite CASCADE;
CREATE TABLE Commodite(
	idCommodite INTEGER,
	description VARCHAR(255) NOT NULL,
	prix        INTEGER      NOT NULL CHECK(prix >= 0),
	
	CONSTRAINT pk_idCommodite PRIMARY KEY (idCommodite)
);

DROP TABLE CommoditeOfferte CASCADE;
CREATE TABLE CommoditeOfferte(
	idCommoditeOfferte SERIAL,
	idChambre          INTEGER NOT NULL,
	idCommodite        INTEGER NOT NULL,
	
	CONSTRAINT pk_idCommoditeOfferte   PRIMARY KEY (idCommoditeOfferte),
	CONSTRAINT uk_idChambreIdCommodite UNIQUE      (idChambre, idCommodite),
	CONSTRAINT fk_idChambre            FOREIGN KEY (idChambre) REFERENCES Chambre,
	CONSTRAINT fk_idCommodite          FOREIGN KEY (idCommodite) REFERENCES Commodite
);

DROP TABLE Reservation CASCADE;
CREATE TABLE Reservation(
	idReservation SERIAL,
	idClient      INTEGER NOT NULL,
	idChambre     INTEGER NOT NULL,
	dateDebut     DATE    NOT NULL,
	dateFin       DATE    NOT NULL,
	prixTotal     INTEGER NOT NULL CHECK(prixTotal >= 0),
	
	CONSTRAINT pk_idReservation      PRIMARY KEY (idReservation),
	CONSTRAINT uk_idChambreDateDebut UNIQUE (idChambre, dateDebut),
	CONSTRAINT fk_idChambre          FOREIGN KEY (idChambre) REFERENCES Chambre,
	CONSTRAINT fk_idClient           FOREIGN KEY (idClient)  REFERENCES Client
);