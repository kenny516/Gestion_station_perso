-- creation de pompiste
CREATE SEQUENCE pompeiste_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE POMPISTE
(
    ID     varchar(255) PRIMARY KEY,
    NOM    varchar(255),
    PRENOM varchar(255),
    SEXE   varchar(1),
    DATY   date default TRUNC(SYSDATE),
    IDUSER varchar(255)
);
INSERT INTO POMPISTE (ID, NOM, PRENOM, SEXE, DATY, IDUSER)
VALUES (1, 'RASOLO', 'Tahiry', 'M', TRUNC(SYSDATE), 1);

-- creation de pompe
CREATE SEQUENCE pompe_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE POMPE
(
    ID      varchar(255) PRIMARY KEY,
    LIBELLE varchar(255)
);
INSERT INTO POMPE (ID, LIBELLE)
VALUES (1, 'Pompe 1');

-- creation de compteur_perso
CREATE SEQUENCE compteur_perso_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE COMPTEUR_PERSO
(
    ID         varchar(255) PRIMARY KEY ,
    IDPOMPISTE varchar(255),
    IDPOMPE    varchar(255),
    IDCOMPTEUR varchar(255),
    DATY       date default TRUNC(SYSDATE),
    IDUSER     varchar(255),
    CONSTRAINT fk_pompiste FOREIGN KEY (IDPOMPISTE) REFERENCES POMPISTE (ID),
    CONSTRAINT fk_pompe FOREIGN KEY (IDPOMPE) REFERENCES POMPE (ID)
);
INSERT INTO COMPTEUR_PERSO (ID, IDPOMPISTE, IDPOMPE, IDCOMPTEUR, DATY, IDUSER)
VALUES (1, 1, 1, 1, TRUNC(SYSDATE), 1);

--Produit
CREATE SEQUENCE produit_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE PRODUIT(
    ID varchar(255) PRIMARY KEY,
    nom varchar(255),
    PRIX_ACHAT float,
    PRIX_VENTE float
);
INSERT INTO PRODUIT (ID, nom, PRIX_ACHAT, PRIX_VENTE) VALUES (1, 'Essence', 1000, 1200);

--fournisseur
CREATE SEQUENCE fournisseur_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE FOURNISSEUR(
    ID varchar(255) PRIMARY KEY,
    nom varchar(255)
);
INSERT INTO FOURNISSEUR (ID, nom) VALUES (1, 'Total');

-- Achat produit avec fournisseur
CREATE SEQUENCE achat_produit_seq
    START WITH 1
    INCREMENT BY 1
    NOCACHE
    NOCYCLE;

CREATE TABLE ACHAT_PRODUIT(
    ID varchar(255) PRIMARY KEY,
    IDPRODUIT varchar(255),
    IDFOURNISSEUR varchar(255),
    QUANTITE int,
    DATY date default TRUNC(SYSDATE),
    IDUSER varchar(255),
    CONSTRAINT fk_produit FOREIGN KEY (IDPRODUIT) REFERENCES PRODUIT (ID),
    CONSTRAINT fk_fournisseur FOREIGN KEY (IDFOURNISSEUR) REFERENCES FOURNISSEUR (ID)
);
INSERT INTO ACHAT_PRODUIT (ID, IDPRODUIT, IDFOURNISSEUR, QUANTITE, DATY, IDUSER) VALUES (1, 1, 1, 100, TRUNC(SYSDATE), 1);
