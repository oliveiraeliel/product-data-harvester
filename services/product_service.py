from factories import Factory
from entities import Product
from repositories.product_repository import ProductRepository


class ProductService:
    def __init__(self, product_repository: ProductRepository, spyder_factory: Factory) -> None:
        self.products: list[Product] = spyder_factory.get_data()
        self.product_repository: ProductRepository = product_repository

    def save(self):
        for product in self.products:
            self.product_repository.insert_or_update_product(product)
