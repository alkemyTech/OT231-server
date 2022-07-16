insert into public.organizations (organization_id, about_us_text, address, create_timestamp, email,
                                  facebook_url, image, instagram_url, linkedin_url, name,
                                  phone_number, soft_delete, welcome_text)
values (1, 'We are Somos Mas', '742 Evergreen Terrace', null, 'somosmas@org-somosmas.com', null,
        'https://app.s3.com/somosmas.jpg', null, null, 'Somos Mas', '+549115789456', false,
        'Welcome to Somos Mas');

insert into public.categories (category_id, create_timestamp, description, image, name, soft_delete)
values (1, null, 'news', 'https://app.s3.com/news.jpg', 'news', false);

