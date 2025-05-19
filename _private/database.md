# 📦 项目交易平台数据库结构文档

---

## 🔹 Table: `user_account`

| 字段名            | 类型        | 约束            | 说明                     |
| -------------- | --------- | ------------- | ---------------------- |
| user\_id       | SERIAL    | PK            | 用户ID，主键                |
| username       | VARCHAR   | NOT NULL      | 用户名                    |
| email          | VARCHAR   | UNIQUE        | 邮箱地址                   |
| password\_hash | VARCHAR   | NOT NULL      | 密码哈希                   |
| created\_at    | TIMESTAMP | DEFAULT now() | 注册时间                   |
| status         | VARCHAR   |               | 账号状态                   |
| role           | VARCHAR   |               | 角色（buyer/seller/admin） |
| avatar\_url    | TEXT      |               | 头像地址                   |

---

## 🔹 Table: `cart_cart`

| 字段名         | 类型        | 约束                  | 说明   |
| ----------- | --------- | ------------------- | ---- |
| cart\_id    | SERIAL    | PK                  | 主键   |
| user\_id    | INT       | FK → user(user\_id) | 所属用户 |
| created\_at | TIMESTAMP | DEFAULT now()       | 创建时间 |

---

## 🔹 Table: `cart_cart_item`

| 字段名            | 类型        | 约束                  | 说明    |
| -------------- | --------- | ------------------- | ----- |
| cart\_item\_id | SERIAL    | PK                  | 主键    |
| cart\_id       | INT       | FK → cart(cart\_id) | 所属购物车 |
| item\_id       | INT       | FK → item(item\_id) | 关联项目  |
| quantity       | INT       | DEFAULT 1           | 购买数量  |
| added\_at      | TIMESTAMP | DEFAULT now()       | 添加时间  |

---

## 🔹 Table: `item_item`

| 字段名            | 类型        | 约束                          | 说明            |
| -------------- | --------- | --------------------------- | ------------- |
| item\_id       | SERIAL    | PK                          | 项目ID          |
| seller\_id     | INT       | FK → user(user\_id)         | 发布者           |
| title          | VARCHAR   | NOT NULL                    | 项目标题          |
| description    | TEXT      |                             | 项目描述          |
| base\_price    | NUMERIC   |                             | 基础价格          |
| price          | NUMERIC   |                             | 实际价格          |
| category\_id   | INT       | FK → category(category\_id) | 所属分类          |
| created\_at    | TIMESTAMP | DEFAULT now()               | 创建时间          |
| thumbnail\_url | TEXT      |                             | 缩略图           |
| type           | VARCHAR   |                             | 项目类型（模板/API等） |
| status         | VARCHAR   |                             | 状态（草稿/上架等）    |
| start\_date    | DATE      |                             | 项目服务开始时间      |
| end\_date      | DATE      |                             | 项目服务结束时间      |

---

## 🔹 Table: `order_order`

| 字段名          | 类型        | 约束                  | 说明         |
| ------------ | --------- | ------------------- | ---------- |
| order\_id    | SERIAL    | PK                  | 订单主键       |
| buyer\_id    | INT       | FK → user(user\_id) | 买家         |
| item\_id     | INT       | FK → item(item\_id) | 所购买项目      |
| total\_price | NUMERIC   |                     | 订单总金额      |
| order\_time  | TIMESTAMP | DEFAULT now()       | 下单时间       |
| status       | VARCHAR   |                     | 订单状态（待支付等） |

---

## 🔹 Table: `payment_record`

| 字段名             | 类型        | 约束                    | 说明             |
| --------------- | --------- | --------------------- | -------------- |
| payment\_id     | SERIAL    | PK                    | 支付记录主键         |
| order\_id       | INT       | FK → order(order\_id) | 对应订单           |
| payment\_status | VARCHAR   |                       | 支付状态（已付/失败）    |
| payment\_method | VARCHAR   |                       | 支付方式（微信/支付宝）   |
| payment\_type   | VARCHAR   |                       | 支付类型（定金/尾款/运维） |
| paid\_at        | TIMESTAMP |                       | 支付时间           |
| amount          | NUMERIC   |                       | 支付金额           |

---

## 🔹 Table: `service_assignment`

| 字段名                     | 类型      | 约束                  | 说明        |
| ----------------------- | ------- | ------------------- | --------- |
| service\_assignment\_id | SERIAL  | PK                  | 主键        |
| item\_id                | INT     | FK → item(item\_id) | 服务对应的项目   |
| seller\_id              | INT     | FK → user(user\_id) | 被指派的服务人员  |
| time\_available         | VARCHAR |                     | 可服务时间或交付期 |

---

## 🔹 Table: `meta_category`

| 字段名          | 类型      | 约束 | 说明   |
| ------------ | ------- | -- | ---- |
| category\_id | SERIAL  | PK | 主键   |
| name         | VARCHAR |    | 类别名称 |
| description  | TEXT    |    | 类别描述 |

---

## 🔹 Table: `meta_tag`

| 字段名     | 类型      | 约束     | 说明   |
| ------- | ------- | ------ | ---- |
| tag\_id | SERIAL  | PK     | 主键   |
| name    | VARCHAR | UNIQUE | 标签名称 |

---

## 🔹 Table: `meta_item_tag`（多对多中间表）

| 字段名            | 类型                  | 约束                  | 说明     |
|----------------|---------------------| ------------------- | ------ |
| meta\_item\_id | SERIAL              | PK                  | 主键    |
| item\_id       | INT                 | FK → item(item\_id) | 所属项目   |
| tag\_id        | INT                 | FK → tag(tag\_id)   | 关联标签   |
| 组合主键           | `(item_id, tag_id)` | PK                  | 防止重复关联 |

---

## 🔗 表关系汇总（外键 & 多对多）

| 表 A                    | 表 B                 | 类型   | 外键字段 / 中间表                     |
| ---------------------- | ------------------- | ---- | ------------------------------ |
| user\_account          | cart\_cart          | 1\:N | `cart_cart.user_id`            |
| cart\_cart             | cart\_cart\_item    | 1\:N | `cart_cart_item.cart_id`       |
| item\_item             | cart\_cart\_item    | 1\:N | `cart_cart_item.item_id`       |
| user\_account          | order\_order        | 1\:N | `order_order.buyer_id`         |
| item\_item             | order\_order        | 1\:N | `order_order.item_id`          |
| order\_order           | payment\_record     | 1\:N | `payment_record.order_id`      |
| meta\_category         | item\_item          | 1\:N | `item_item.category_id`        |
| user\_account          | item\_item          | 1\:N | `item_item.seller_id`          |
| item\_item             | service\_assignment | 1\:N | `service_assignment.item_id`   |
| user\_account          | service\_assignment | 1\:N | `service_assignment.seller_id` |
| item\_item             | meta\_item\_tag     | 1\:N | `meta_item_tag.item_id`        |
| meta\_tag              | meta\_item\_tag     | 1\:N | `meta_item_tag.tag_id`         |
| item\_item ⬌ meta\_tag | 多对多                 | N\:N | 中间表：`meta_item_tag`            |
