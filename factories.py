from abc import ABC, abstractmethod
from spyder import MagazineLuizaSpyder
import json


class Factory(ABC):
    @abstractmethod
    def get_data(self):
        pass


class MagazineLuizaProducts(Factory):
    def __init__(self) -> None:
        with open('site-info.json') as file:
            data = json.load(file)
        self.data = data

    def get_data(self):
        return MagazineLuizaSpyder(
            base_url=self.data['magalu']['url'],
            target_path=self.data['magalu']['target_paths']
        ).fetch_data()
