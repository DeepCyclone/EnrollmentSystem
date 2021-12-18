create table education_form
(
    e_form_id   bigint unsigned auto_increment
        primary key,
    e_form_name varchar(10) not null,
    constraint e_form_name_UNIQUE
        unique (e_form_name)
);

create table enrollment_status
(
    es_id   bigint unsigned auto_increment
        primary key,
    es_name varchar(18) not null,
    constraint es_name_UNIQUE
        unique (es_name)
);

create table facility
(
    f_id               bigint unsigned auto_increment
        primary key,
    f_name             varchar(70) not null,
    f_is_extraordinary tinyint(1)  not null,
    constraint f_name_UNIQUE
        unique (f_name)
);

create table faculty
(
    f_id                  bigint unsigned auto_increment
        primary key,
    f_name                varchar(15)  null,
    f_budg_admission_plan int          null,
    f_paid_admission_plan int          null,
    f_description         varchar(250) null,
    constraint f_name_UNIQUE
        unique (f_name)
);

create table role
(
    r_id   bigint unsigned auto_increment
        primary key,
    r_name varchar(15) not null,
    constraint r_name_UNIQUE
        unique (r_name)
);

create table subject
(
    s_id   bigint unsigned auto_increment
        primary key,
    s_name varchar(30) not null,
    constraint s_name_UNIQUE
        unique (s_name)
);

create table subject_m2m_faculty
(
    smf_s_id            bigint unsigned not null,
    smf_f_id            bigint unsigned not null,
    smf_subject_urgency int unsigned    not null,
    primary key (smf_s_id, smf_f_id),
    constraint fk_subject_m2m_faculty_faculty1
        foreign key (smf_f_id) references faculty (f_id)
            on update cascade on delete cascade,
    constraint fk_subject_m2m_faculty_subject1
        foreign key (smf_s_id) references subject (s_id)
            on update cascade on delete cascade
);

create table system_information
(
    si_id    bigint unsigned auto_increment
        primary key,
    si_name  varchar(30)  null,
    si_value varchar(100) null,
    constraint system_information_si_name_uindex
        unique (si_name)
);

create table user
(
    u_id       bigint unsigned auto_increment
        primary key,
    u_login    varchar(15)     not null,
    u_password varchar(60)     not null,
    u_email    varchar(25)     not null,
    u_r_id     bigint unsigned not null,
    constraint u_email_UNIQUE
        unique (u_email),
    constraint u_login_UNIQUE
        unique (u_login),
    constraint fk_user_role1
        foreign key (u_r_id) references role (r_id)
            on update cascade on delete cascade
);

create table applicant_enrollment
(
    ae_u_id     bigint unsigned not null,
    ae_f_id     bigint unsigned not null,
    ae_ef_id    bigint unsigned not null,
    ae_es_id    bigint unsigned not null,
    ae_priority int unsigned    null,
    primary key (ae_u_id, ae_f_id, ae_ef_id, ae_es_id),
    constraint fk_applicant_enrollment_enrollment_status1
        foreign key (ae_es_id) references enrollment_status (es_id)
            on update cascade on delete cascade,
    constraint fk_user_m2m_faculty_education_form1
        foreign key (ae_ef_id) references education_form (e_form_id)
            on update cascade on delete cascade,
    constraint fk_user_m2m_faculty_faculty1
        foreign key (ae_f_id) references faculty (f_id)
            on update cascade on delete cascade,
    constraint fk_user_m2m_faculty_user1
        foreign key (ae_u_id) references user (u_id)
            on update cascade on delete cascade
);

create table user_info
(
    ui_u_id       bigint unsigned not null,
    ui_name       varchar(20)     null,
    ui_surname    varchar(20)     null,
    ui_patronymic varchar(20)     null,
    ui_photo      varchar(100)    null,
    ui_address    varchar(50)     null,
    ui_passport   varchar(20)     null,
    constraint ui_passport_UNIQUE
        unique (ui_passport),
    constraint fk_user_info_user
        foreign key (ui_u_id) references user (u_id)
            on update cascade on delete cascade
);

create table facility_m2m_user_info
(
    fmui_f_id    bigint unsigned not null,
    fmui_ui_u_id bigint unsigned not null,
    primary key (fmui_f_id, fmui_ui_u_id),
    constraint fk_facility_m2m_user_info_facility1
        foreign key (fmui_f_id) references facility (f_id)
            on update cascade on delete cascade,
    constraint fk_facility_m2m_user_info_user_info1
        foreign key (fmui_ui_u_id) references user_info (ui_u_id)
            on update cascade on delete cascade
);

create table result
(
    r_s_id    bigint unsigned not null,
    r_ui_u_id bigint unsigned not null,
    r_value   int             null,
    primary key (r_s_id, r_ui_u_id),
    constraint fk_result_subject1
        foreign key (r_s_id) references subject (s_id)
            on update cascade on delete cascade,
    constraint fk_result_user_info1
        foreign key (r_ui_u_id) references user_info (ui_u_id)
            on update cascade on delete cascade
);

create index fk_user_info_user_idx
    on user_info (ui_u_id);


