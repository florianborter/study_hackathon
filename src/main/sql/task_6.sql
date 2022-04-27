/*
    Databases Module
    Tasks 6 (1.x and 2.x)
 */

-- 1.1
update instructor
set salary=salary*1.1
where dept_name='Comp. Sci.';

-- 1.2
delete from department
where dept_name not in (select distinct dept_name from course);

-- 1.3
insert into instructor
select id, name, dept_name, 30000 from student where tot_cred>100;

-- 2.1
select title from titles
where price < 20;

-- Möglichkeit: Aufgrund des Typs der Kolonne price (money), welcher nicht exakt rechnen wird.
-- Lokal werden aber alle 12 Titel korrekt aufgelistet

-- 2.2
select au_lname, pub_name from authors
natural join titleauthor
natural join titles
natural join publishers;

-- Aufgrund des natural join werden alle gleichnamigen Kolonnen miteinander verglichen,
-- also wird beispielsweise die Kolonne City von den Tabellen authors und publishers verglichen,
-- so werden nur die Autoren dargestellt, die in der gleichen City wohnen wie die Publisher vertreten sind.
select distinct au_lname, pub_name from authors
join titleauthor on authors.au_id = titleauthor.au_id
join titles on titleauthor.title_id = titles.title_id
join publishers on titles.pub_id = publishers.pub_id;


-- 2.3
select pub_id, count(title_id) as numtitles from titles
where type like 'psychology%'
group by pub_id
having numtitles <= 2;

-- Die Abfrage liefert die Publisher, die 1-2 titel mit type "psychology" herausgebracht haben

select pub_id, count(type) as numtitles from titles
where pub_id not in (
    select pub_id from titles
    where type like 'psychology%'
    group by pub_id
    having count(*) > 2
)
group by pub_id;
