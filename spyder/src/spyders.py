import requests
from bs4 import BeautifulSoup
from src.entities import Product, Data
from abc import ABC, abstractmethod


class SpyderInterface(ABC):
    @abstractmethod
    def fetch_data(self) -> list[Data]:
        pass


class MagazineLuizaSpyder(SpyderInterface):
    def __init__(self, base_url: str, target_path: dict):
        self.base_url = base_url
        self.target_path: dict = target_path

    def fetch_data(self) -> list[Data]:
        products: Product = []
        for product_type, target in self.target_path.items():
            products = products + self.__fetch__(product_type, target)
        return products

    def __fetch__(self, product_type: str, target: str) -> list[Data]:
        url = f'{self.base_url}/{target}'
        response = requests.get(url)

        if response.status_code == 200:
            soup = BeautifulSoup(response.text, 'html.parser')
            product_items = soup.find_all('li', class_='sc-APcvf eJDyHN')
            products: list[Product] = []
            for item in product_items:
                product = Product()
                href = item.find('a', class_='sc-eBMEME uPWog sc-gppfCo egZavq sc-gppfCo egZavq').get('href')
                product.page_url = f"{self.base_url}/{href}"
                href = href.split("/")
                i = 0
                while href[i] != 'p':
                    i += 1
                product.id = href[i + 1]
                product.price = float(item.find('p', 'sc-kpDqfm eCPtRw sc-hoLEA kXWuGr').text.split()[1].replace('.', '').replace(',', '.'))
                product.name = item.find('h2', 'sc-eWzREE uaEbk').text
                product.image_url = item.find('img', class_='sc-cWSHoV bLJsBf').get('src')
                product.product_type = product_type
                products.append(product)
            return products
        else:
            raise Exception(f"Failed to retrieve the page. Status code: {response.status_code}. Url: {url}")
