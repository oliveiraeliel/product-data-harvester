from abc import ABC, abstractmethod
from src.spyders import MagazineLuizaSpyder
import json


class Factory(ABC):
    @abstractmethod
    def get_products(self):
        pass


class MagazineLuizaProducts(Factory):
    def __init__(self) -> None:
        with open('site-info.json') as file:
            data = json.load(file)
        self.data = data

    def get_products(self):
        return MagazineLuizaSpyder(
            base_url=self.data['magalu']['url'],
            target_path=self.data['magalu']['target_paths']
        ).fetch_products()
