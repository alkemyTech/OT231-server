insert into organizations (organization_id, about_us_text, address, create_timestamp, email,
                                  facebook_url, image, instagram_url, linkedin_url, name,
                                  phone_number, soft_delete, welcome_text)
values (1, 'We are Somos Mas', '742 Evergreen Terrace', null, 'somosmas@org-somosmas.com',
		'www.facebook.com/Somos_Mas', 'https://alkemy-java-ot231.s3.amazonaws.com/somosmas.png',
		'www.instagram.com/SomosMas', null, 'Somos Mas', '+5491160112988', false,
		'All set! Now you have full access to our web page');

insert into categories (category_id, create_timestamp, description, image, name, soft_delete)
values (1, null, 'news', 'https://app.s3.com/news.jpg', 'news', false);

insert into news (news_id, content, create_timestamp, image, name, soft_delete, category_id)
values (1, 'This is the news content...', null,
        'https://alkemy-java-ot231.s3.amazonaws.com/workshop.jpg', 'Last Week Workshop', false, 1);

insert into members (member_id, name, facebook_url, instagram_url, linkedin_url, image,
                            description, create_timestamp, soft_delete)
values
(1, 'Maria Iraola', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/miraola.jpg','Presidenta', null, false),
(2, 'Marita Gomez', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/mgomez.jpeg', 'Fundadora', null, false),
(3, 'Miriam Rodriguez', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/mrodriguez.jpg', 'Terapista ocupacional', null, false),
(4, 'Cecilia Mendez', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/cmendez.jpeg', 'Psicopedagoga', null, false),
(5, 'Mario Fuentes', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/#', 'Psicologo', null, false),
(6, 'Rodrigo Fuente', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/rfuente.jpg', 'Contador', null, false),
(7, 'Maria Garcia', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/mgarcia.jpg', 'Profesora de Artes Dramaticas', null, false),
(8, 'Marco Fernandez', null, null, null,
'https://alkemy-java-ot231.s3.amazonaws.com/mfernandez.jpg', 'Profesor de Educacion Fisica', null, false);

