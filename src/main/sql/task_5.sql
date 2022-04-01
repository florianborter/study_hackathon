/*
    Databases Module
    Tasks 5 (3.x)
 */


-- 3.1
select * from authors where city='San Francisco';

-- 3.2
select count(*) from titles where title like 'S%';

-- 3.3
select avg(price) from titles;

-- 3.4
select date from sales
join stores s on s.stor_id = sales.stor_id
where stor_name='Bookbeat';

-- 3.5
select distinct t.title from salesdetail
join titles t on salesdetail.title_id = t.title_id
join stores s on salesdetail.stor_id = s.stor_id
where stor_name='Bookbeat';

-- 3.6
-- if there are multiple titles that both cost the same amount of money
-- and where this amount is the most expensive amount, it will select only the first title, ordered by title
select price, title from titles
where price=(select max(price) from titles)
order by title
limit 1;

-- 3.7
-- the column type has a fixed length of 12 chars
select title from titles
where price > (select min(price) from titles where type='psychology  ');

-- 3.8
select * from authors
where state not in (select distinct state from stores);

-- check example of state UT - should return no entries
select * from stores where state='UT';

-- 3.9
select distinct authors.city from authors
natural join publishers;

-- 3.10
select title from titles
where type = (select type from titles where title='Net Etiquette');

-- 3.11
select type, count(title) from titles
group by type;

-- 3.12
select type, count(title) from titles
group by type
having count(title) > 2;

-- 3.13
select state, count(*) from authors
group by state
order by count(*) desc;

-- 3.14
-- One may adapt this query to make it easier, if one sees a better way
select publishers.pub_name from titles
join publishers on titles.pub_id = publishers.pub_id
group by titles.pub_id
having count(*) < (
    select avg(c) from (
        select count(*) as 'c' from titles
        join publishers on titles.pub_id = publishers.pub_id
        group by titles.pub_id
        having count(*)
    )
);

