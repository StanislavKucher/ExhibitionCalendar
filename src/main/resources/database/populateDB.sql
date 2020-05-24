INSERT INTO expo2020_db.accounts (first_name, last_name, login, password, phone, role, email)
VALUES ('Stan', 'Kucher', 'Maju', 'XMajuX', '+38(050)111-11-11', 'admin', 'expo2020mock@ukr.net');

-- INSERT INTO expo2020_db.cards (card_number, cvv, balance, expire_month, expire_year, account_id)
-- VALUES ('1234-5678-1234-5678', 123, 10000.00, 12, 2022, 1);

INSERT INTO expo2020_db.cards (card_number, cvv, balance, expire_month, expire_year)
VALUES ('1234-5678-1234-5678', 123, 10000.00, 12, 2022);
