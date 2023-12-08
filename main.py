import requests
from bs4 import BeautifulSoup
import re

url = 'https://www.magazineluiza.com.br/celulares-e-smartphones/l/te/'
response = requests.get(url)

if response.status_code == 200:
    soup = BeautifulSoup(response.text, 'html.parser')

    # Use find_all para encontrar todos os elementos <li> com as classes específicas
    product_items = soup.find_all('li', class_='sc-APcvf eJDyHN')

    for item in product_items:
        link = item.find('a')

        # Verifique se o elemento <a> existe antes de acessar o atributo href
        if link:
            href = link.get('href').split('/')
            i = 0
            while href[i] != 'p':
                i += 1
            print(f"Product ID: {href[i + 1]}")
else:
    print(f"Failed to retrieve the page. Status code: {response.status_code}")
