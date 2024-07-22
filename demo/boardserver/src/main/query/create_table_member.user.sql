DROP TABLE public."member.user";
CREATE TABLE public."member.user" (
    no uuid DEFAULT gen_random_uuid() NOT NULL PRIMARY KEY ,
    id character varying(20) NOT NULL,
    password character varying(128) NOT NULL,
    "isAdmin" boolean DEFAULT false NOT NULL,
    "createdTime" timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    salt character varying(36) NOT NULL,
    UNIQUE (no, id)
);

// DELETE FROM "member.user" WHERE id = 'testadmin'
// INSERT INTO "member.user" VALUES (DEFAULT, 'testadmin', '0000', true, DEFAULT, gen_random_uuid()::varchar(36))
// UPDATE "member.user" SET password='2345' WHERE id='testAdmin';

/* 다중칼럼 업데이트 Sample
update "member.user"
   set
       column1 = data.column1,
       column2 = data.column2
       from
    (values
        (1, 'column1_a', 'column2_a'),
        (2, 'column1_b', 'column2_b')
) as data(id, column1, column2)
   where 1 = 1
     and tb.id = data.id
   */
 */