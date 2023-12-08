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
        href = item.find('a', class_='sc-eBMEME uPWog sc-gppfCo egZavq sc-gppfCo egZavq').get('href').split('/')
        i = 0
        while href[i] != 'p':
            i += 1
        print(f"Product ID: {href[i + 1]}")
        print(f"Price: {item.find('p', 'sc-kpDqfm eCPtRw sc-hoLEA kXWuGr').text}")
        print(f"Name: {item.find('h2', 'sc-eWzREE uaEbk').text}")
else:
    print(f"Failed to retrieve the page. Status code: {response.status_code}")
