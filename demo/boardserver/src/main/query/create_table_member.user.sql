DROP TABLE public."member.user";
CREATE TABLE public."member.user" (
    no uuid DEFAULT gen_random_uuid() NOT NULL PRIMARY KEY ,
    id character varying(20) NOT NULL,
    password character varying(128) NOT NULL,
    "isAdmin" boolean DEFAULT false NOT NULL,
    "createdTime" date DEFAULT now() NOT NULL,
    salt character varying(36) NOT NULL,
    UNIQUE (no, id)
);

// DELETE FROM "member.user" WHERE id = 'admin'
// INSERT INTO "member.user" VALUES (DEFAULT, 'admin', '0000', true, DEFAULT, gen_random_uuid()::varchar(36))