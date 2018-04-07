# library
Book Management Application

## DataBase

- books table

| Column name     | description                 |
|:----------- |:-------------------- |
| id          | ブックID                   |
| author      | 著者名               |
| book_name   | 本の名前             |
| created_at  | 追加日時             |
| delete_flag | 論理削除フラグ       |
| number      | 蔵書数               |
| updated_at  | 変更日時             |
| image_url   | 画像URL              |
| genre       | ジャンル             |
| publisher   | 出版社               |
| user_id     | 本を追加したユーザID |

- users table

| Column name | description    |
|:----------- |:-------------- |
| id          | ユーザID       |
| name        | ユーザ名       |
| book_id     | 追加した本のID |

## 
