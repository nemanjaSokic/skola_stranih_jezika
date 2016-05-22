SELECT kurs_id from kursevi where kursevi.skola_pib = 123456789;
SELECT jezik_id,naziv FROM jezici WHERE jezik_id = 'eng'; 
select * from ucenici;

SELECT uplatnica from uplate join ucenici on uplate.uplatnica = ucenici.uplata where uplate.uplatnica = 1;
SELECT ime,prezime,jmbg, uplata from ucenici;

select pohadjanje.ucenik_jmbg,uplate.broj_uplatnice,uplate.kolicina from pohadjanje 
join uplate on pohadjanje.pohadjanje_id = uplate.pohadjanje_id join ucenici on pohadjanje.ucenik_jmbg = ucenici.jmbg
where ucenici.jmbg = 222;

select datum,kolicina from uplate where broj_uplatnice = '" + uplata + "';

select * from kursevi;
select u.ime,u.prezime,u.jmbg from ucenici u join pohadjanje po on u.jmbg = po.ucenik_jmbg join kursevi k on po.kurs_id = k.kurs_id where k.kurs_id = 5;


select * from kursevi;

select * from pohadjanje where pohadjanje_id = 2;

select pohadjanje_id from pohadjanje where kurs_id = 2 and ucenik_jmbg = 222;
select * from uplate;



select uplate.broj_uplatnice, ku.kurs_id from pohadjanje join uplate on pohadjanje.pohadjanje_id = uplate.pohadjanje_id join kursevi ku on pohadjanje.kurs_id = ku.kurs_id join ucenici on pohadjanje.ucenik_jmbg = ucenici.jmbg where ucenici.jmbg = 444;

select * from uplate;

select kurs_id from pohadjanje where ucenik_jmbg = 222;

select * from uplate join pohadjanje on uplate.pohadjanje_id = pohadjanje.pohadjanje_id;


select ucenici.jmbg,ucenici.ime, ucenici.prezime,k.kurs_id, sum(kolicina) from uplate u join pohadjanje p on u.pohadjanje_id = p.pohadjanje_id
join ucenici on p.ucenik_jmbg = ucenici.jmbg
join kursevi k on p.kurs_id = k.kurs_id where k.kurs_id = 2 and ucenici.jmbg = 222 group by ucenici.jmbg;



