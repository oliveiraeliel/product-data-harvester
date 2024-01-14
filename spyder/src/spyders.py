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
                    print(e)
                    break
        return products

    def __fetch__(self, products: list[Product],
                  product_type: str, url: str) -> None:
        response = self.session.get(url)
        response.raise_for_status()

        soup = BeautifulSoup(response.text, 'html.parser')
        items = soup.find('div', {'data-testid': 'mod-productlist'}) \
                    .find('div', {'data-testid': 'product-list'}) \
                    .find('ul', {'data-testid': 'list'}) \
                    .find_all("li")

        for item in items:
            href = item.find('a').get('href')
            product_page_url = f"{self.base_url}/{href}"
            product = self.__fetch_product_page__(
                product_page_url, product_type)
            products.append(product)

    def __fetch_product_page__(self, url: str, product_type: str) -> Product:
        response = self.session.get(url)
        response.raise_for_status()

        product = Product()
        soup = BeautifulSoup(response.text, 'html.parser')
        infos_tag_identifier = {'data-testid': 'jsonld-script',
                                'type': 'application/ld+json'}
        infos = json.loads(soup.find('script', infos_tag_identifier).text)
        product.name = infos['name']
        product.price = float(infos['offers']['price'])
        product.page_url = infos['offers']['url']
        image_url = infos['image'].split("/")
        image_url[3] = "2000x2000"
        product.image_url = "/".join(image_url)
        product.product_type = product_type
        product.source = self.source
        return product
