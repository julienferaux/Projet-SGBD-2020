create table "Article"
(
    "Titre"       VARCHAR2(500) not null
        constraint ARTICLE_PK
            primary key,
    "Resume"      VARCHAR2(500),
    "TypeArticle" VARCHAR2(500)
);