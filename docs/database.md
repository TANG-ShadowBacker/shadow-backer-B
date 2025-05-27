

# 📦 项目交易平台数据库结构文档

---

## 🔹 Table: `user_account`

| 字段名            | 类型        | 约束                             | 说明                     |
| -------------- | --------- | ------------------------------ | ---------------------- |
| user_id        | UUID      | PK DEFAULT gen_random_uuid()   | 用户ID，主键                |
| username       | VARCHAR   | NOT NULL                       | 用户名                    |
| email          | VARCHAR   | UNIQUE                         | 邮箱地址                   |
| password_hash  | VARCHAR   | NOT NULL                       | 密码哈希                   |
| created_at     | TIMESTAMP | DEFAULT now()                  | 注册时间                   |
| status         | VARCHAR   |                                | 账号状态                   |
| role           | VARCHAR   |                                | 角色（buyer/seller/admin） |
| avatar_url     | TEXT      |                                | 头像地址                   |


---

## 🔹 Table: `cart_cart`


| 字段名      | 类型        | 约束                             | 说明     |
| --------- | --------- | ------------------------------ | ------ |
| cart_id   | UUID      | PK DEFAULT gen_random_uuid()   | 主键     |
| user_id   | UUID      | FK → user_account(user_id)     | 所属用户   |
| created_at| TIMESTAMP | DEFAULT now()                  | 创建时间   |

---

## 🔹 Table: `cart_cart_item`


| 字段名       | 类型        | 约束                             | 说明     |
| ---------- | --------- | ------------------------------ | ------ |
| cart_item_id | UUID    | PK DEFAULT gen_random_uuid()   | 主键     |
| cart_id      | UUID    | FK → cart_cart(cart_id)        | 所属购物车  |
| item_id      | UUID    | FK → item_item(item_id)        | 关联项目   |
| quantity     | INT     | DEFAULT 1                      | 购买数量   |
| added_at     | TIMESTAMP | DEFAULT now()                | 添加时间   |

---

## 🔹 Table: `item_item`

| 字段名        | 类型        | 约束                                | 说明              |
| ------------ | --------- | --------------------------------- | --------------- |
| item_id      | UUID      | PK DEFAULT gen_random_uuid()      | 项目ID            |
| seller_id    | UUID      | FK → user_account(user_id)        | 发布者             |
| title        | VARCHAR   | NOT NULL                          | 项目标题            |
| description  | TEXT      |                                   | 项目描述            |
| base_price   | NUMERIC   |                                   | 基础价格            |
| price        | NUMERIC   |                                   | 实际价格            |
| category_id  | UUID      | FK → meta_category(category_id)   | 所属分类            |
| created_at   | TIMESTAMP | DEFAULT now()                     | 创建时间            |
| thumbnail_url| TEXT      |                                   | 缩略图             |
| type         | VARCHAR   |                                   | 项目类型（模板/API等） |
| status       | VARCHAR   |                                   | 状态（草稿/上架等）    |
| start_date   | DATE      |                                   | 项目服务开始时间        |
| end_date     | DATE      |                                   | 项目服务结束时间        |

---

## 🔹 Table: `order_order`


| 字段名      | 类型        | 约束                             | 说明         |
| ---------- | --------- | ------------------------------ | ---------- |
| order_id   | UUID      | PK DEFAULT gen_random_uuid()   | 订单主键       |
| buyer_id   | UUID      | FK → user_account(user_id)     | 买家         |
| item_id    | UUID      | FK → item_item(item_id)        | 所购买项目      |
| total_price| NUMERIC   |                                | 订单总金额      |
| order_time | TIMESTAMP | DEFAULT now()                  | 下单时间       |
| status     | VARCHAR   |                                | 订单状态（待支付等） |

---

## 🔹 Table: `payment_record`


| 字段名         | 类型        | 约束                             | 说明             |
| ------------- | --------- | ------------------------------ | -------------- |
| payment_id    | UUID      | PK DEFAULT gen_random_uuid()   | 支付记录主键         |
| order_id      | UUID      | FK → order_order(order_id)     | 对应订单           |
| payment_status| VARCHAR   |                                | 支付状态（已付/失败）    |
| payment_method| VARCHAR   |                                | 支付方式（微信/支付宝）   |
| payment_type  | VARCHAR   |                                | 支付类型（定金/尾款/运维） |
| paid_at       | TIMESTAMP |                                | 支付时间           |
| amount        | NUMERIC   |                                | 支付金额           |

---

## 🔹 Table: `service_assignment`


| 字段名               | 类型    | 约束                             | 说明        |
| ------------------ | ------- | ------------------------------ | --------- |
| service_assignment_id | UUID  | PK DEFAULT gen_random_uuid()   | 主键        |
| item_id              | UUID  | FK → item_item(item_id)        | 服务对应的项目   |
| seller_id            | UUID  | FK → user_account(user_id)     | 被指派的服务人员  |
| time_available       | VARCHAR |                                | 可服务时间或交付期 |

---

## 🔹 Table: `meta_category`


| 字段名      | 类型    | 约束                             | 说明     |
| ---------- | ------- | ------------------------------ | ------ |
| category_id| UUID    | PK DEFAULT gen_random_uuid()   | 主键     |
| name       | VARCHAR |                                | 类别名称   |
| description| TEXT    |                                | 类别描述   |

---

## 🔹 Table: `meta_tag`


| 字段名  | 类型    | 约束                             | 说明   |
| ------ | ------- | ------------------------------ | ---- |
| tag_id | UUID    | PK DEFAULT gen_random_uuid()   | 主键   |
| name   | VARCHAR | UNIQUE                         | 标签名称 |

---

## 🔹 Table: `item_tag`

| 字段名       | 类型  | 说明             |
|------------|------|----------------|
| item_tag_id| UUID | 主键，唯一标识记录     |
| item_id    | UUID | 外键，关联 `item_item(item_id)` |
| tag_id     | UUID | 外键，关联 `meta_tag(tag_id)`  |

🔒 **表级约束：**
- `UNIQUE (item_id, tag_id)`：防止同一个项目关联重复标签。

---

## 🔗 表关系汇总（外键 & 多对多）

| 表 A           | 表 B               | 类型   | 外键字段 / 中间表               |
|----------------|--------------------|--------|--------------------------|
| user_account   | cart_cart          | 1:N    | `cart_cart.user_id`      |
| cart_cart      | cart_cart_item     | 1:N    | `cart_cart_item.cart_id` |
| item_item      | cart_cart_item     | 1:N    | `cart_cart_item.item_id` |
| user_account   | order_order        | 1:N    | `order_order.buyer_id`   |
| item_item      | order_order        | 1:N    | `order_order.item_id`    |
| order_order    | payment_record     | 1:N    | `payment_record.order_id`|
| meta_category  | item_item          | 1:N    | `item_item.category_id`  |
| user_account   | item_item          | 1:N    | `item_item.seller_id`    |
| item_item      | service_assignment | 1:N    | `service_assignment.item_id` |
| user_account   | service_assignment | 1:N    | `service_assignment.seller_id` |
| item_item      | item_tag           | 1:N    | `item_tag.item_id`       |
| meta_tag       | item_tag           | 1:N    | `item_tag.tag_id`        |
| item_item ⬌ meta_tag | 多对多         | N:N    | 中间表：`item_tag`       |

