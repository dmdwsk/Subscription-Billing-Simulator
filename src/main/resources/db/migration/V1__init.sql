-- V1__init.sql
-- PostgreSQL schema for Subscription Billing Simulator

-- =========================
-- 1) USERS
-- =========================
create table if not exists users (
                                     id            serial primary key,
                                     email         varchar(255) not null,
    password_hash varchar(255) not null,
    role          varchar(100) not null,
    created_at    timestamptz  not null,
    updated_at    timestamptz  not null
    );

create unique index if not exists uk_users_email on users(email);

-- =========================
-- 2) PLANS
-- =========================
create table if not exists plans (
                                     id         serial primary key,
                                     name       varchar(255)  not null,
    period     varchar(20)   not null,      -- MONTHLY / YEARLY
    price      numeric(19,4) not null,
    currency   varchar(3)    not null,      -- EUR / USD ...
    active     boolean       not null default true,
    created_at timestamptz   not null
    );

create index if not exists idx_plans_active on plans(active);

-- =========================
-- 3) SUBSCRIPTIONS
-- =========================
create table if not exists subscriptions (
                                             id                   serial primary key,
                                             owner_id             int          not null,
                                             plan_id              int          not null,
                                             status               varchar(20)  not null,   -- ACTIVE / GRACE / TERMINATED / EXPIRED

    start_date           timestamptz  not null,
    current_period_start timestamptz  not null,
    current_period_end   timestamptz  not null,

    grace_until          timestamptz  null,
    terminated_at        timestamptz  null,

    created_at           timestamptz  not null,
    updated_at           timestamptz  not null,

    constraint fk_sub_owner foreign key (owner_id) references users(id),
    constraint fk_sub_plan  foreign key (plan_id)  references plans(id)
    );

create index if not exists idx_sub_owner  on subscriptions(owner_id);
create index if not exists idx_sub_plan   on subscriptions(plan_id);
create index if not exists idx_sub_status on subscriptions(status);

-- =========================
-- 4) INVOICES
-- =========================
create table if not exists invoices (
                                        id              serial primary key,
                                        subscription_id int          not null,

                                        period_start    timestamptz  not null,
                                        period_end      timestamptz  not null,

                                        amount          numeric(19,4) not null,
    currency        varchar(3)    not null,

    status          varchar(20)  not null,  -- DRAFT / ISSUED / PAID / FAILED
    issued_at       timestamptz  null,
    paid_at         timestamptz  null,

    created_at      timestamptz  not null,

    constraint fk_inv_subscription foreign key (subscription_id) references subscriptions(id),
    constraint uk_invoice_subscription_period unique (subscription_id, period_start, period_end)
    );

create index if not exists idx_inv_subscription on invoices(subscription_id);
create index if not exists idx_inv_status       on invoices(status);

-- =========================
-- 5) TRANSFER REQUESTS
-- =========================
create table if not exists transfer_requests (
                                                 id              serial primary key,
                                                 subscription_id int          not null,

                                                 from_user_id    int          not null,
                                                 to_user_id      int          not null,

                                                 transfer_date   timestamptz  not null,
                                                 status          varchar(20)  not null,  -- REQUESTED / VALIDATED / APPLIED / REJECTED

    reason          varchar(500) null,

    requested_at    timestamptz  not null,
    applied_at      timestamptz  null,

    constraint fk_tr_subscription foreign key (subscription_id) references subscriptions(id),
    constraint fk_tr_from_user    foreign key (from_user_id) references users(id),
    constraint fk_tr_to_user      foreign key (to_user_id) references users(id)
    );

create index if not exists idx_tr_subscription on transfer_requests(subscription_id);
create index if not exists idx_tr_status       on transfer_requests(status);
create index if not exists idx_tr_from_user    on transfer_requests(from_user_id);
create index if not exists idx_tr_to_user      on transfer_requests(to_user_id);

-- =========================
-- 6) SUBSCRIPTION EVENTS
-- =========================
create table if not exists subscription_events (
                                                   id              serial primary key,
                                                   subscription_id int          not null,
                                                   type            varchar(40)  not null,
    payload_json    text         null,
    actor_user_id   int          null,
    created_at      timestamptz  not null,

    constraint fk_se_subscription foreign key (subscription_id) references subscriptions(id),
    constraint fk_se_actor_user   foreign key (actor_user_id) references users(id)
    );

create index if not exists idx_se_subscription on subscription_events(subscription_id);
create index if not exists idx_se_type         on subscription_events(type);
create index if not exists idx_se_created_at   on subscription_events(created_at);
