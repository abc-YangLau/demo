create table per_account_info
(
    accountNo               char(20)       not null comment '账号'
        primary key,
    num_mob                 char(20)       not null comment '手机号',
    email                   char(20)       null comment '邮箱地址',
    account_name            char(50)       null comment '客户姓名',
    account_nick            char(30)       null comment '账户昵称',
    account_password        char(6)        null comment '密码',
    certificate_type        char(2)        null comment '证据类型',
    certificate_num         char(30)       null comment '证件号',
    account_bal             decimal(18, 2) null comment '账户余额',
    date_tr                 char(16)       null comment '交易日期',
    account_bal_last        decimal(18, 2) null comment '上一日日终余额',
    account_tr_last         char(16)       null comment '上一日交易日期',
    account_tr_before_last  char(16)       null comment '上上一日交易日期
',
    account_bal_before_last decimal(18, 2) null comment '上上日日终余额',
    date_open               char(16)       null comment '账户开立日期',
    chanel_open             char(4)        null comment '开立渠道',
    type_open               char(6)        null comment '开立方式
',
    cust_no                 char(16)       null comment '客户号
',
    date_dbtr_acml          decimal(18, 2) null comment '日借累计交易金额',
    date_crtr_acml          decimal(18, 2) null comment '日贷累计交易金额',
    year_dbtr_acml          decimal(18, 2) null comment '年借累计交易金额',
    year_crtr_acml          decimal(18, 2) null comment '年贷累计交易金额'
)
    comment '个人客户账户信息表';

