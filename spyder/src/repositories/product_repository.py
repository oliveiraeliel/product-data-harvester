from abc import ABC, abstractmethod
from src.entities import Product


class ProductRepository(ABC):
    @abstractmethod
    def insert_or_update_product(self, product: Product) -> None:
        pass

    @abstractmethod
    def update_product(self, product: Product) -> None:
        pass

    @abstractmethod
    def get_all(self) -> list[Product]:
        pass
