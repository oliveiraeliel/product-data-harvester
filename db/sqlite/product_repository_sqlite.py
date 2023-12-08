import sqlite3
from entities import Product
from repositories.product_repository import ProductRepository


class ProductRepositorySqlite(ProductRepository):
    def __init__(self, db_path):
        self.db_path = db_path
        self.conn = sqlite3.connect(db_path)
        self.cursor = self.conn.cursor()
        try:
            self.cursor.execute(
                '''CREATE TABLE IF NOT EXISTS Products (
                    id VARCHAR(20),
                    name VARCHAR(150) NOT NULL,
                    page_url VARCHAR(255) NOT NULL,
                    image_url VARCHAR(255),
                    price FLOAT NOT NULL,
                    product_type VARCHAR(50) NOT NULL,
                    created_at DATE DEFAULT (DATE('now')),
                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                    PRIMARY KEY(id, created_at)
                );
                '''
            )
            self.conn.commit()
        except Exception as e:
            print(str(e))

    def insert_or_update_product(self, product: Product):
        try:
            self.cursor.execute(
                '''INSERT INTO Products (id, name, page_url, image_url,
                price, product_type) VALUES (?, ?, ?, ?, ?, ?)''',
                (product.id, product.name, product.page_url, product.image_url,
                 product.price, product.product_type)
            )
            self.conn.commit()
            print("Product inserted successfully!")
        except:
            self.update_product(product)

    def update_product(self, product: Product) -> None:
        try:
            self.cursor.execute(
                '''UPDATE Products SET name=?, page_url=?, image_url=?,
                price=?, product_type=?, updated_at=CURRENT_TIMESTAMP
                WHERE id=?''',
                (product.name, product.page_url, product.image_url,
                 product.price, product.product_type, product.id)
            )
            self.conn.commit()
            print("Product updated successfully!")
        except Exception as e:
            print(f"Error updating product: {str(e)}")

    def get_all(self) -> list[Product]:
        try:
            self.cursor.execute("SELECT * FROM Products")
            rows = self.cursor.fetchall()
            products = []
            for row in rows:
                product = Product(
                    id=row[0],
                    name=row[1],
                    page_url=row[2],
                    image_url=row[3],
                    price=row[4],
                    product_type=row[5]
                )
                products.append(product)
            return products
        except Exception as e:
            print(f"Error retrieving products: {str(e)}")
            return []
