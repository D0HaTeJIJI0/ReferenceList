--Insert data into Employee table
insert into Employee (name, age, married)
values ('Maksim Sergeevich Skavish', 25, false);

insert into Employee (name, age, married)
values ('Vladimir Olegovich Zmachinski', 30, true);

insert into Employee (name, age, married)
values ('Konstantin Nikolaevich Kapitonov', 62, true);

insert into Employee (name, age, married)
values ('Evgeniy Konstantinovich Hryhoryk', 23, false);

insert into Employee (name, age, married)
values ('Alexander Vladimirovich Pokataev', 29, false);

insert into Employee (name, age, married)
values ('Alexei Grigorevich Kushnerov', 24, false);

insert into Employee (name, age, married)
values ('Ekaterina Vladimirovna Truhan', 35, true);

insert into Employee (name, age, married)
values ('Andrei Alexandrovich Gaiko', 24, false);

insert into Employee (name, age, married)
values ('Olga Alexandrovna Kvasha', 30, false);

insert into Employee (name, age, married)
values ('Anna Alexandrovna Ivanchikova', 23, false);

--Set boss_id column for rows in Employee table
update Employee
set boss_id = (select id from Employee where name='Vladimir Olegovich Zmachinski')
where name='Maksim Sergeevich Skavish'
   or name='Alexander Vladimirovich Pokataev'
   or name='Anna Alexandrovna Ivanchikova';

update Employee
set boss_id = (select id from Employee where name='Ekaterina Vladimirovna Truhan')
where name='Olga Alexandrovna Kvasha'
   or name='Andrei Alexandrovich Gaiko';

update Employee
set boss_id = (select id from Employee where name='Konstantin Nikolaevich Kapitonov')
where name='Vladimir Olegovich Zmachinski'
   or name='Ekaterina Vladimirovna Truhan'
   or name='Evgeniy Konstantinovich Hryhoryk'
   or name='Alexei Grigorevich Kushnerov';

--Insert data into Rule table
insert into Rule (name, text, create_date)
values ('Правила безопасного поведения во время зимних каникул', '1. Соблюдай правила дорожного движения.

2. Соблюдай правила пожарной безопасности и обращения с электроприборами.

3. Соблюдай правила поведения в общественных местах.

4. Соблюдай правила личной безопасности на улице.

5. Остерегайся гололёда, во избежание падений и получения травм.

6. Соблюдай правила безопасности на льду.

7. Соблюдай правила безопасности при обращении с животными.

8. Не играй с острыми, колющими и режущими, легковоспламеняющимися и взрывоопасными предметами.

9. Не употребляй лекарственные препараты без назначения врача, наркотики, спиртные напитки, не кури и не нюхай токсические вещества.

10. Не играй вблизи зданий, с крыш которых свисает снег и лёд.', parsedatetime('17-09-2012 18:09:53', 'dd-MM-yyyy hh:mm:ss'));

insert into Rule (name, text, create_date)
values ('Правила поведения во время летних каникул', '1. Соблюдай правила дорожного движения.

2. Соблюдай правила пожарной безопасности и обращения с электроприборами.

3. Соблюдай правила поведения в общественных местах.

4. Соблюдай правила личной безопасности на улице.

5. Соблюдай правила безопасного поведения на воде летом.

6. Соблюдай правила безопасного поведения в лесу.

7. Соблюдай правила безопасности при обращении с животными.

8. Не играй с острыми, колющими и режущими, легковоспламеняющимися и взрывоопасными предметами.

9. Не употребляй лекарственные препараты без назначения врача, наркотики, спиртные напитки, не кури и не нюхай токсические вещества.', parsedatetime('25-12-2014 17:10:46', 'dd-MM-yyyy hh:mm:ss'));

insert into Rule (name, text, create_date)
values ('Общие правила техники безопасности на уроках производственного обучения', ' Приступать к обучению и работе с инструментами могут только обучающиеся, которые ознакомились с правилами техники безопасности на уроках производственного обучения

 Брать инструменты, использовать их, а также пользоваться станками можно только с разрешения мастера производственного обучения

 Приступая к урокам производственного обучения, обучающийся должен предварительно надеть все необходимые для каждого конкретного случая средства защиты (халат, головной убор, рукавицы, защитные очки и т.д.)

 Запрещено выполнять задания сломанным или поврежденным инструментом

 Рабочее место надо держать в чистоте, инструменты класть на место, мусор вовремя убирать

 На уроках производственного обучения запрещено использовать любой инвентарь не по прямому назначению, запрещено направлять острые части на других обучающихся или бросать инструменты

 Инструменты всегда надо держать так, чтобы самая опасная часть (острая) была направлена от себя

 В случае получения травмы немедленно прекратить работу и сообщить об этом мастеру производственного обучения

 По окончанию урока производственного обучения сдать весь инструмент, и привести в порядок рабочие места', parsedatetime('17-09-2020 09:23:00', 'dd-MM-yyyy hh:mm:ss'));

