import requests
from bs4 import BeautifulSoup
from entities import Product, Source


class Spyder(Source):
    def fetch_product(self) -> list[Product]:
        url = f'{self.url}/{self.target_path}'
        response = requests.get(url)

        if response.status_code == 200:
            soup = BeautifulSoup(response.text, 'html.parser')

            product_items = soup.find_all('li', class_='sc-APcvf eJDyHN')
            products: list[Product] = []
            for item in product_items:
                href = item.find('a', class_='sc-eBMEME uPWog sc-gppfCo egZavq sc-gppfCo egZavq').get('href').split('/')
                i = 0
                while href[i] != 'p':
                    i += 1
                product = Product()
                product.id = href[i + 1]
                product.price = float(item.find('p', 'sc-kpDqfm eCPtRw sc-hoLEA kXWuGr').text.split()[1].replace('.', '').replace(',', '.'))
                product.name = item.find('h2', 'sc-eWzREE uaEbk').text
                products.append(product)
            return products
        else:
            raise Exception(f"Failed to retrieve the page. Status code: {response.status_code}")
