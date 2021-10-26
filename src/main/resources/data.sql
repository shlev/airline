-- init 20 random destination
insert into Destination (id, country, city, longitude, latitude) values (1, 'United States', 'Indianapolis', -86.19, 39.67);
insert into Destination (id, country, city, longitude, latitude) values (2, 'Indonesia', 'Nunsena', 124.4319309, -9.7934897);
insert into Destination (id, country, city, longitude, latitude) values (3, 'Philippines', 'Maasin', 124.841371, 10.132503);
insert into Destination (id, country, city, longitude, latitude) values (4, 'Portugal', 'Algarvia', -25.231895, 37.8450241);
insert into Destination (id, country, city, longitude, latitude) values (5, 'Belarus', 'Ivatsevichy', 25.3346543, 52.7085795);
insert into Destination (id, country, city, longitude, latitude) values (6, 'China', 'Shuangpu', 113.459749, 23.106401);
insert into Destination (id, country, city, longitude, latitude) values (7, 'Poland', 'Przelewice', 15.0760167, 53.1042303);
insert into Destination (id, country, city, longitude, latitude) values (8, 'Philippines', 'Balayan', 120.7285858, 13.9686682);
insert into Destination (id, country, city, longitude, latitude) values (9, 'China', 'Wa’erma', 101.730195, 32.903037);
insert into Destination (id, country, city, longitude, latitude) values (10, 'Canada', 'Fox Creek', -116.80238, 54.40007);
insert into Destination (id, country, city, longitude, latitude) values (11, 'South Korea', 'Gimcheon', 128.1135947, 36.1398393);
insert into Destination (id, country, city, longitude, latitude) values (12, 'France', 'Besançon', 5.9675963, 47.2215999);
insert into Destination (id, country, city, longitude, latitude) values (13, 'Czech Republic', 'Zdíkov', 13.6889288, 49.0648694);
insert into Destination (id, country, city, longitude, latitude) values (14, 'Indonesia', 'Jambuir Timur', 114.3304, -7.1532);
insert into Destination (id, country, city, longitude, latitude) values (15, 'China', 'Beicang', 116.4073963, 39.9041999);
insert into Destination (id, country, city, longitude, latitude) values (16, 'United States', 'Springfield', -89.65, 39.8);
insert into Destination (id, country, city, longitude, latitude) values (17, 'China', 'Xinghua', 119.85254, 32.910459);
insert into Destination (id, country, city, longitude, latitude) values (18, 'Reunion', 'Saint-Denis', 55.5243352, -20.9304923);
insert into Destination (id, country, city, longitude, latitude) values (19, 'Burkina Faso', 'Koupéla', -0.3513021, 12.180204);
insert into Destination (id, country, city, longitude, latitude) values (20, 'Russia', 'Zemlyansk', 38.7334, 51.9041);

-- init Airlines
--
insert into Airline (id, name, balance, home_base_id) values (1, 'El Al', 11931679, 2);
insert into Airline (id, name, balance, home_base_id) values (2, 'Turkish Airlines', 15304029, 15);
insert into Airline (id, name, balance, home_base_id) values (3, 'Emirates Airlines', 60294618, 16);
insert into Airline (id, name, balance, home_base_id) values (4, 'Singapore Airlines', 35374924, 9);

update destination set home_base_airline_id=1 where id=2;
update destination set home_base_airline_id=2 where id=15;
update destination set home_base_airline_id=3 where id=16;
update destination set home_base_airline_id=4 where id=9;


