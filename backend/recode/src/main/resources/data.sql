insert into member(oauth_id, oauth_provider)
values ("ys", "KAKAO"),
       ("jy", "KAKAO"),
       ("user3", "KAKAO"),
       ("user4", "KAKAO"),
       ("user5", "KAKAO"),
       ("user6", "KAKAO");

insert into tech_stack(`name`, `tagged_count`, `image_url`)
values ("stack1", 1, "url1"),
       ("stack2", 2, "url2"),
       ("stack3", 3, "url3"),
       ("stack4", 4, "url4");

insert into member_stack
values (1, 1, 1),
       (2, 1, 2),
       (3, 1, 3),
       (4, 2, 1),
       (5, 2, 2),
       (6, 2, 3);

insert into follow
values (1, now(), now(), 1, 2),
       (2, now(), now(), 1, 3),
       (3, now(), now(), 2, 1),
       (4, now(), now(), 2, 4);