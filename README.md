# Hibernate Classic

以「經典」XML 設定方式使用 Hibernate ORM 建立資料庫連線並進行 CRUD 的**教學範例專案**。

本專案刻意不使用 Spring、JPA `persistence.xml` 或 Entity annotation，而是直接以原生 Hibernate API（`Configuration` → `SessionFactory` → `Session`）搭配 `hibernate.cfg.xml` 與 `*.hbm.xml` mapping 檔，展示 Hibernate 最基礎的運作方式。資料庫使用 in-memory H2，不需另外安裝或啟動任何服務即可執行。

## 專案類型

| 項目 | 說明 |
| --- | --- |
| 類型 | 單一模組 Maven 專案、Console 應用程式（無 Web、無 Spring） |
| 用途 | 學習／示範 Hibernate 原生 API 與 XML mapping |
| 資料庫 | H2 in-memory（`MODE=MYSQL`），程式結束即消失 |
| 測試 | 無 |

## 工具列表

- `JDK 25`
- `Maven`
- `Hibernate ORM 7.0.0.Final`
- `H2 2.4.240`
- `Lombok 1.18.42`
- ~~`MySQL`~~（舊版做法，已改用 H2）

## 專案結構

```
src/main/java/idv/module
├── HibernateClassicApplication.java   # 程式進入點，示範 CRUD 流程
├── config/HibernateConfig.java        # 建立並持有唯一的 SessionFactory
└── entity/Product.java                # 商品 POJO（Lombok @Data，無 JPA annotation）

src/main/resources
├── hibernate.cfg.xml                  # Hibernate 主設定：連線、dialect、DDL 策略、mapping 清單
├── product.hbm.xml                    # Product 與 product table 的欄位對應
└── db/product_data.sql                # 啟動時匯入的 45 筆種子資料
```

## 運作方式

1. `HibernateConfig` 載入時以 `new Configuration().configure()` 讀取 classpath 下的 `hibernate.cfg.xml`，建立 `SessionFactory`。
2. 因為設定了 `hbm2ddl.auto=create-drop`，Hibernate 會依 `product.hbm.xml` 自動建立 `product` table，程式關閉時再刪除。
3. 建表後透過 `hbm2ddl.import_files` 執行 `db/product_data.sql`，匯入種子資料。
4. `HibernateClassicApplication` 在同一個 `Session` 與 `Transaction` 中依序示範：

   | 步驟 | 使用的 API |
   | --- | --- |
   | 查詢全部商品 | `session.createNativeQuery(...)` |
   | 依 id 隨機查詢一筆 | `session.find(...)` |
   | 新增測試商品 | `session.persist(...)` |
   | 更新測試商品 | `session.merge(...)` |
   | 刪除測試商品 | `session.remove(...)` |

5. 最後 `transaction.commit()` 並呼叫 `HibernateConfig.shutdown()` 關閉 `SessionFactory`。若未 commit，交易會 rollback。

執行時會因 `show_sql=true` 在 console 印出 Hibernate 產生的 SQL，方便對照每個操作實際送出的語句。

## 資料表 `product`

| 欄位 | Java 屬性 | 說明 |
| --- | --- | --- |
| `id` | `id` | 主鍵，`native` 產生策略 |
| `en_name` | `enName` | 英文名稱，長度 60、not null、unique |
| `zh_name` | `zhName` | 中文名稱 |
| `price` | `price` | 價格 |
| `release_date` | `releaseDate` | 上架日期 |
| `edit_date` | `editDate` | 修改日期 |

## 啟動方式

**方式一：IDE**

直接在 IDE（例如 IntelliJ IDEA）執行 `idv.module.HibernateClassicApplication` 的 main 方法即可。

**方式二：Maven 命令列**

```bash
mvn compile exec:java -Dexec.mainClass=idv.module.HibernateClassicApplication
```

> 在 PowerShell 中請將 `-D` 參數加上引號：`"-Dexec.mainClass=idv.module.HibernateClassicApplication"`。

> `HibernateClassicApplication` 使用 Java 25 的 instance main method（`void main()`），請確認 IDE 與專案 SDK 設定為 JDK 25。

## 擴充說明

- 新增或修改 `Product` 欄位時，需同步修改 `product.hbm.xml`。
- 新增 Entity 時，需建立對應的 `*.hbm.xml`，並在 `hibernate.cfg.xml` 加入 `<mapping resource="..."/>`。
- 範例中的隨機查詢以 `1..總筆數` 產生 id，修改 `product_data.sql` 時請保持 id 從 1 開始連續。
