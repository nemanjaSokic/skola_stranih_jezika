SELECT kurs_id from kursevi where kursevi.skola_pib = 123456789;
SELECT jezik_id,naziv FROM jezici WHERE jezik_id = 'eng'; 
select * from ucenici;

SELECT uplatnica from uplate join ucenici on uplate.uplatnica = ucenici.uplata where uplate.uplatnica = 1;
SELECT ime,prezime,jmbg, uplata from ucenici;