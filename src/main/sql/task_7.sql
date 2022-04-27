/*
    Databases Module
    Tasks 7 (1.x and 2.x)
 */

-- 1.1
select authors.au_id, au_lname, stores.stor_id, sum(qty * titles.price) as pt from authors
join titleauthor on authors.au_id = titleauthor.au_id
join salesdetail on titleauthor.title_id = salesdetail.title_id
join titles on titleauthor.title_id = titles.title_id
join stores on salesdetail.stor_id = stores.stor_id
group by authors.au_id, stores .stor_id
order by pt desc;

-- 1.2
select au_id from authors
where authors.au_id not in (
    select authors.au_id from authors
    join titleauthor on authors.au_id = titleauthor.au_id
    join titles on titleauthor.title_id = titles.title_id
    where type like 'psychology%'
    group by authors.au_id, titles.type
    having count(title) >= (
        select count(*) from authors
        join titleauthor on authors.au_id = titleauthor.au_id
        join titles on titleauthor.title_id = titles.title_id
        where type like 'psychology%' and authors.au_fname = 'Livia' and authors.au_lname = 'Karsen'
        group by authors.au_id, titles.type
    )
);

-- 1.3
select t, royalty, (titles.price * titles.total_sales * royalty / 100) as tantieme from roysched
left join (
    select sum(salesdetail.qty) as x, salesdetail.title_id as t from salesdetail
    group by salesdetail.title_id
) on roysched.title_id = t
join titles on titles.title_id = t
where lorange < x and x < roysched.hirange;

-- 1.4
select * from
(select sum(salesdetail.qty) as q, salesdetail.title_id as x from salesdetail
group by salesdetail.title_id)
join titles on titles.title_id = x
where total_sales != q;

-- 1.5
select * from titles
join salesdetail s on titles.title_id = s.title_id
join stores s2 on s.stor_id = s2.stor_id
group by s.stor_id
having count(distinct titles.type) = (select count( distinct titles.type) from titles)

-- 2.1
select t.course_id, section.time_slot_id, i.name, section.room_number, ts.* from section
join teaches t on section.course_id = t.course_id and section.sec_id = t.sec_id and section.semester = t.semester and section.year = t.year
join instructor i on t.ID = i.ID
join time_slot ts on section.time_slot_id = ts.time_slot_id
group by ts.day, ts.start_hr, ts.end_hr, ts.start_min, ts.end_min, i.name
having count(*) > 1;

-- 2.2
create trigger schedule_checker
   before insert ON section
begin
    select
        case
            when (new.time_slot_id, new.room_number) in (
                select section.time_slot_id, section.room_number from section
                join teaches t on section.course_id = t.course_id and section.sec_id = t.sec_id and section.semester = t.semester and section.year = t.year
                join instructor i on t.ID = i.ID
                join time_slot ts on section.time_slot_id = ts.time_slot_id
                group by ts.day, ts.start_hr, ts.end_hr, ts.start_min, ts.end_min, i.name)
                then
                RAISE (ABORT,'Invalid email address')
            end;
end;