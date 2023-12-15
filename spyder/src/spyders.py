import requests
from bs4 import BeautifulSoup
from src.entities import Product
from abc import ABC, abstractmethod


class SpyderInterface(ABC):
    @abstractmethod
    def fetch_products(self) -> list[Product]:
        pass


class MagazineLuizaSpyder(SpyderInterface):
    def __init__(self, base_url: str, target_path: dict):
        self.base_url = base_url
        self.target_path: dict = target_path
        self.source = "magazine-luiza"

    def fetch_products(self) -> list[Product]:
        products: Product = []
        for product_type, target in self.target_path.items():
            i = 1
            while True:
                try:
                    products = products + self.__fetch__(product_type, target, i)
                    i += 1
                except:
                    break
        return products

    def __fetch__(self, product_type: str, target: str, page=1) -> list[Product]:
        url = f'{self.base_url}/{target}/?page={page}'
        response = requests.get(url)
        if response.status_code == 200:
            soup = BeautifulSoup(response.text, 'html.parser')
            product_items = soup.find_all('li', class_='sc-APcvf eJDyHN')
            products: list[Product] = []
            for item in product_items:
                product = Product()
                href = item.find('a', class_='sc-eBMEME uPWog sc-gppfCo egZavq sc-gppfCo egZavq').get('href')
                product.page_url = f"{self.base_url}/{href}"
                product.price = float(item.find('p', 'sc-kpDqfm eCPtRw sc-hoLEA kXWuGr').text.split()[1].replace('.', '').replace(',', '.'))
                product.name = item.find('h2', 'sc-eWzREE uaEbk').text
                product.image_url = item.find('img', class_='sc-cWSHoV bLJsBf').get('src')
                product.product_type = product_type
                product.source = self.source
                products.append(product)
            return products
        else:
            raise Exception(f"Failed to retrieve the page. Status code: {response.status_code}. Url: {url}")