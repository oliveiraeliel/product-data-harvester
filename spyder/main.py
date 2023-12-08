from src.factories import MagazineLuizaProducts
from src.services.product_service import ProductService
from src.db.sqlite.product_repository_sqlite import ProductRepositorySqlite


def main():
    db = ProductRepositorySqlite('src/db/sqlite/sqlite.db')
    ProductService(product_repository=db,
                   spyder_factory=MagazineLuizaProducts()).save()
    db.conn.close()


if __name__ == '__main__':
    main()
