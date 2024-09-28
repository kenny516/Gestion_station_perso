CREATE TABLE COMPTEUR_PERSO(
    ID varchar(255),
    DATY date default TRUNC(SYSDATE),
    HEURE  varchar(10),
    COMPTEUR NUMBER,
    IDPOMPISTE NUMBER,
    IDPOMPE varchar(255)
);
// nb create a sequence