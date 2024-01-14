import requests
from bs4 import BeautifulSoup
from src.entities import Product
from abc import ABC, abstractmethod
import json


class SpyderInterface(ABC):
    @abstractmethod
    def fetch_products(self) -> list[Product]:
        pass


class MagazineLuizaSpyder(SpyderInterface):
    def __init__(self, base_url: str, target_path: dict):
        self.base_url = base_url
        self.target_path: dict = target_path
        self.source = "magazine-luiza"
        self.session = requests.Session()

    def fetch_products(self) -> list[Product]:
        products: Product = []

        for product_type, target in self.target_path.items():
            i = 1
            while True:
                try:
                    url = f'{self.base_url}/{target}/?page={i}'
                    self.__fetch__(products, product_type, url)
                    i += 1
                except Exception as e:
                    print(f"{len(products)} products found")
                    print(e)
                    break
        return products

    def __fetch__(self, products: list[Product],
                  product_type: str, url: str) -> None:
        response = self.session.get(url)
        response.raise_for_status()

        soup = BeautifulSoup(response.text, 'html.parser')
        infos = soup.find('div', {'data-testid': 'mod-productlist'}) \
                    .find('div', {'data-testid': 'product-list'}) \
                    .find('script', {
                        'data-testid': 'jsonld-script',
                        'type': 'application/ld+json'
                    }).text
        products_infos = json.loads(infos)

        for product_infos in products_infos['@graph']:
            product = Product()
            product.name = product_infos['name']
            product.price = float(product_infos['offers']['price'])
            product.page_url = product_infos['offers']['url']
            image_url = product_infos['image'].split("/")
            image_url[3] = "2000x2000"
            product.image_url = "/".join(image_url)
            product.product_type = product_type
            product.source = self.source
            products.append(product)
