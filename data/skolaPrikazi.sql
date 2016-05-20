SELECT kurs_id from kursevi where kursevi.skola_pib = 123456789;
SELECT jezik_id,naziv FROM jezici WHERE jezik_id = 'eng'; 
select * from ucenici;

SELECT uplatnica from uplate join ucenici on uplate.uplatnica = ucenici.uplata where uplate.uplatnica = 1;
SELECT ime,prezime,jmbg, uplata from ucenici;

select pohadjanje.ucenik_jmbg,uplate.broj_uplatnice,uplate.kolicina from pohadjanje 
join uplate on pohadjanje.pohadjanje_id = uplate.pohadjanje_id join ucenici on pohadjanje.ucenik_jmbg = ucenici.jmbg
where ucenici.jmbg = 222;

select datum,kolicina from uplate where broj_uplatnice = '" + uplata + "';

select * from jezici;
