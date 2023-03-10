create extension if not exists pgcrypto;

    update "authorization"."Users" set password = crypt(password, gen_salt('bf'));