drop table if exists TestSchema.TestTable;

CREATE TABLE TestTable
(
    id bigint auto_increment,
    testColumn nvarchar(100),
    primary key (id)
);