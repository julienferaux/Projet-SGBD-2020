create or replace trigger question8A
    before insert
    on NOTER
    for each row
declare
    nb number(1);
begin
    select count(*)
    into nb
    from dual
    where :new.EMAIL in (select ECRIRE.EMAIL
                         from ECRIRE
                         where ECRIRE.TITRE = :new.TITRE);
    if nb != 0 then
        RAISE_APPLICATION_ERROR(-20001, 'vous ne pouvez pas noter un article ou vous ete co auteur');
    end if;
end;


create or replace trigger question8B
    before insert or update
    on ANNOTER
    for each row
declare
    c             number(1);
    typeActionVAR varchar2(50);
begin
    if inserting then
        typeActionVAR := 'insertion';
    else
        typeActionVAR := 'update';
    end if;

    select count(*)
    into c
    from user_tables
    where TABLE_NAME = 'LOG_CHERCHEURS';

    if c = 0 then
        EXECUTE IMMEDIATE 'CREATE TABLE LOG_CHERCHEURS
                           (
                               UTILISATEUR VARCHAR2(500) not null, dateJOUR DATE not null, typeAction varchar2(500) not null, constraint PKLOG primary key (UTILISATEUR, dateJOUR, typeAction)
                           )';
    end if;

    insert into LOG_CHERCHEURS values (USER, SYSDATE, typeActionVAR);

end;