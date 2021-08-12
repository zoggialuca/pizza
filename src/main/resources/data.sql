insert into pizza(id, name, is_vegetarian) values
(1, 'Margherita', true)
, (2, 'Prosciutto', false)
, (3, 'Marinara', true);

insert into ingredient(id, name, notes) values
(1, 'Tomato', null)
, (2, 'Mozzarella', null)
, (3, 'Ham', null)
, (4, 'Garlic', null);

insert into unit_of_measure(id, name) values
(1, 'g')
, (2, 'Pieces');

insert into pizza_ingredient(pizza, ingredient, quantity, unit_of_measure) values
(1, 1, 100, 1)
, (1, 2, 5, 2)
, (2, 1, 100, 1)
, (2, 2, 5, 2)
, (2, 3, 7, 2)
, (3, 1, 100, 1)
, (3, 2, 5, 2)
, (3, 4, 1, 2);