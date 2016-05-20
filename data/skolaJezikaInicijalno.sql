create database skolastanihjezika;

use skolastanihjezika;

create table skola(
	naziv varchar(30),
    adresa varchar(50),
    telefon int,
    email varchar(50),
    internet varchar(100),
    pib int,
    maticni_broj int,
    broj_racuna varchar(50),
    primary key(pib)
);

insert into skola (naziv,adresa,telefon,email,internet,pib,maticni_broj,broj_racuna) values ('AVALON','Petra Lazarevića 45,21000,Novi Sad',021578458,'avalon@avalon.com','www.avalon.skola.com',123456789,1556,'123-547-0566547');
select naziv,adresa,telefon,email,internet,pib,maticni_broj,broj_racuna from skola;
	create table jezici(
	jezik_id varchar(10),
    naziv varchar(30),
    primary key (jezik_id)
);

insert into jezici (jezik_id,naziv) values('eng','Engleski');
insert into jezici (jezik_id,naziv) values('ger','Nemački');
insert into jezici (jezik_id,naziv) values('rus','Ruski');
insert into jezici (jezik_id,naziv) values('nor','Norveški');

create table nivoi(
	nivo_id int,
    nivo_naziv varchar(10),
    primary key(nivo_id)
);

insert into nivoi (nivo_id,nivo_naziv) values(11,'A1');
insert into nivoi (nivo_id,nivo_naziv) values(12,'A2');
insert into nivoi (nivo_id,nivo_naziv) values(21,'B1');
insert into nivoi (nivo_id,nivo_naziv) values(22,'B2');
insert into nivoi (nivo_id,nivo_naziv) values(31,'C1');
insert into nivoi (nivo_id,nivo_naziv) values(32,'C2');

create table nastavnici(
	ime varchar(20),
    prezime varchar(30),
    jmbg int,
    primary key(jmbg)
);

insert into nastavnici (ime,prezime,jmbg) values('Marko','Nikolic',123);
insert into nastavnici (ime,prezime,jmbg) values('Stefan','Jovic',124);

create table ucenici(
	ime varchar(20),
    prezime varchar(30),
    jmbg int,
    primary key(jmbg)
);

insert into ucenici (ime,prezime,jmbg) values('Zoran','Kesic',111);
insert into ucenici (ime,prezime,jmbg) values('Janko','Jelic',222);
insert into ucenici (ime,prezime,jmbg) values('Goran','Gavric',333);
insert into ucenici (ime,prezime,jmbg) values('Ana','Karic',444);

create table kursevi(
	kurs_id int auto_increment,
    skola_pib int,
    jezik varchar(10),
    nivo int,
    cena int,
    nastavnik int,
    primary key(kurs_id),
    foreign key(skola_pib) references skola(pib),
    foreign key(jezik) references jezici(jezik_id),
    foreign key(nivo) references nivoi(nivo_id),
    foreign key(nastavnik) references nastavnici(jmbg)
);

insert into kursevi (skola_pib,jezik,nivo,cena,nastavnik) values(123456789,'eng',11,50000,123);
insert into kursevi (skola_pib,jezik,nivo,cena,nastavnik) values(123456789,'nor',22,60000,124);
insert into kursevi (skola_pib,jezik,nivo,cena,nastavnik) values(123456789,'ger',32,40000,123);






create table pohadjanje(
	kurs_id int,
    ucenik_jmbg int,
    pohadjanje_id int auto_increment,
    primary key(pohadjanje_id),
    foreign key(kurs_id) references kursevi(kurs_id),
    foreign key(ucenik_jmbg) references ucenici(jmbg)
	
);

insert into pohadjanje (kurs_id,ucenik_jmbg) values(1,111);
insert into pohadjanje (kurs_id,ucenik_jmbg) values(2,222);
insert into pohadjanje (kurs_id,ucenik_jmbg) values(3,333);
insert into pohadjanje (kurs_id,ucenik_jmbg) values(2,444);



create table uplate(
	pohadjanje_id int,
	broj_uplatnice int auto_increment,
	datum date,
    kolicina int,
    primary key(broj_uplatnice),
    foreign key(pohadjanje_id) references pohadjanje(pohadjanje_id)
);

insert into uplate (pohadjanje_id,datum,kolicina) values(1,'2015-01-15',2500);
insert into uplate (pohadjanje_id,datum,kolicina) values(2,'2015-06-23',3000);
insert into uplate (pohadjanje_id,datum,kolicina) values(3,'2015-01-04',3200);
insert into uplate (pohadjanje_id,datum,kolicina) values(4,'2015-02-07',5000);
insert into uplate (pohadjanje_id,datum,kolicina) values(2,'2015-02-08',6000);
insert into uplate (pohadjanje_id,datum,kolicina) values(2,'2015-02-08',6000);
insert into uplate (pohadjanje_id,datum,kolicina) values(3,'2015-02-08',2500);






