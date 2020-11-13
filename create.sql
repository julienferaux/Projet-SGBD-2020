create table Article
(
    Titre       VARCHAR2(500) not null
        constraint ARTICLE_PK
            primary key,
    Resume      VARCHAR2(500),
    TypeArticle VARCHAR2(500)
);
create table Chercheur
(
    Email       VARCHAR2(500) not null
        constraint CHERCHEUR_PK
            primary key,
    NomChercheur      VARCHAR2(500),
    PrenomChercheur VARCHAR2(500),
    UrlChercheur VARCHAR2(500)
);
create table Laboratoire
(
    NomLabo      VARCHAR2(500) not null
        constraint LABORATOIRE_PK
            primary key,
    SigleLabo     VARCHAR2(500),
    AdresseLabo VARCHAR2(500),
    UrlLabo VARCHAR2(500)
);
create table Support
(
    NomSupport       VARCHAR2(500) not null
        constraint SUPPORT_PK
            primary key,
    TypeSupport      VARCHAR2(500)
);
create table Annotation
(
    Libelle       VARCHAR2(500) not null
        constraint ANNOTATION_PK
            primary key
);
create table Ecrire
(
    Titre       VARCHAR2(500) not null,
    Email       VARCHAR2(500) not null,

    constraint ECRIRE_PK primary key(Titre, Email),
    constraint TITRE_FK foreign key(Titre) references Article(Titre),
    constraint EMAIL_FK foreign key(Email) references Chercheur(Email)
);
create table Publier
(
    Titre       VARCHAR2(500) not null,
    NomSupport       VARCHAR2(500) not null,
    Annee_Publication NUMBER(38),

    constraint PUBLIER_PK primary key(Titre, NomSupport),
    constraint TITREPUBLIER_FK foreign key(Titre) references Article(Titre),
    constraint NOMSUPPORTPUBLIER_FK foreign key(NomSupport) references Support(NomSupport)
);
create table Travailler
(
    Email       VARCHAR2(500) not null,
    NomLabo       VARCHAR2(500) not null,

    constraint TRAVAILLER_PK primary key(Email, NomLabo),
    constraint EMAIL_FK foreign key(Email) references Chercheur(Email),
    constraint NOMLABO_FK foreign key(NomLabo) references Laboratoire(NomLabo)
);
