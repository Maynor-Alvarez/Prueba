drop database if exists Prueba;
create database Prueba;
use Prueba;

create table persona(
	idPersona int not null primary key auto_increment,
    nombre varchar(50),
    apellido varchar(50),
    numero int
);


DELIMITER $$ 
create procedure sp_addPersona(p_nombre varchar(50),p_apellido varchar(50),p_numero int)
begin 
insert into persona( nombre, apellido, numero)
values(p_nombre,p_apellido,p_numero);
end $$
DELIMITER ; 

DELIMITER $$ 
create procedure sp_deletePersona(p_idPersona int)
begin 
	delete from Persona
    where (idPersona = p_idPersona);
end $$ 
DELIMITER ;

DELIMITER $$ 
create procedure sp_editPersona(p_idPersona int,p_nombre varchar(50),p_apellido varchar(50),p_numero int)
begin 
	update Persona
    set nombre = p_nombre,
    apellido = p_apellido,
	numero = p_numero
    where idPersona = p_idPersona;
end $$ 
DELIMITER ; 

DELIMITER $$ 
create procedure sp_listPersona()
begin 
	select * from Persona;
end $$ 
DELIMITER ;

DELIMITER $$ 
create procedure sp_findPersona(p_idPersona int)
begin 
select persona.idPersona
from Persona where (idPersona = p_idPersona);
end $$ 
DELIMITER ;