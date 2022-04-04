CREATE DATABASE `master` DEFAULT CHARSET=utf8mb4;

USE `master`;

CREATE TABLE product
    (
        id INTEGER UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品流水號',
        en_name VARCHAR(255) COMMENT '商品名稱(英文)' COLLATE utf8mb4_unicode_ci,
        zh_name VARCHAR(60) COMMENT '商品名稱(中文)' COLLATE utf8mb4_unicode_ci,
        price DECIMAL(8,2) COMMENT '售價',
        release_date DATE COMMENT '發售日',
        edit_date DATETIME COMMENT '最後更新日期',
        PRIMARY KEY (id)
    )
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 DEFAULT COLLATE=utf8mb4_unicode_ci;

INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (1, 'Bicycle808', '單車牌808系列', 100, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (2, 'Tally-Ho Circle Backs', 'Tally-Ho 圓背', 150, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (3, 'Tally-Ho Fan Backs', 'Tally-Ho 扇背', 150, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (4, 'Bee', '蜜蜂牌', 120, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (5, 'Guardian Angel', '守護天使', 450, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (6, 'Centurion', '百夫長', 540, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (7, 'Propaganda', '空戰', 570, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (8, 'Stingers', '蜂刺', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (9, 'Monarchs', '君王', 600, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (10, 'JAQK', '葡萄酒', 450, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (11, 'Rebels', '叛軍', 500, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (12, 'White Monarchs', '白君王', 600, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (13, 'Rarebit Copper', '兔子銅色', 400, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (14, 'deck ONE - Industrial', '工業', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (15, 'Aristocrats', '貴族', 400, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (16, 'Steampunks', '蒸氣龐克', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (17, 'Archangels', '大天使', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (18, 'Artisans', '工匠', 450, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (19, 'Animal Kingdom', '動物王國', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (20, 'Love Me', '情人節', 380, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (21, 'Medallions', '大獎章', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (22, 'CharityWater', '慈善水藍', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (23, 'Contraband', '禁品', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (24, 'Nomad', '紐約酒店', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (25, 'TYCOON', '企業大亨', 400, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (26, 'UNION ', '聯邦', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (27, 'Citizens', '公民', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (28, 'NPH', '聯名合作', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (29, 'Ghost', '幽靈', 380, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (30, 'ShadowMaster', '暗影大師', 380, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (31, 'Black Tiger', '黑老虎', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (32, 'Vintage1800', '復古', 250, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (33, 'Viper Fan Backs', '毒蛇 扇背', 400, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (34, 'Arcane', '迷霧', 280, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (35, 'Artifice', '詭計', 400, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (36, 'Infinity', '無限', 300, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (37, 'Fathom', '藍色天湖', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (38, 'Ignite', '點燃', 350, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (39, '52Proof', '禁忌之酒 - 威士忌', 900, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (40, 'ABSINTHE ', '禁忌之酒 - 苦艾酒', 900, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (41, 'MOONSHINE', '禁忌之酒 - 月光', 900, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (42, 'WHITE WOLF', '禁忌之酒 - 伏特加', 900, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (43, 'RebellionRum', '禁忌之酒 - 朗姆', 900, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (44, 'Disparos Tequila', '禁忌之酒 - 龍舌蘭', 900, CURDATE(), SYSDATE());
INSERT INTO product (id, en_name, zh_name, price, release_date, edit_date) VALUES (45, 'Prohibition 6 Deck', '禁忌之酒套裝', 4990, CURDATE(), SYSDATE());