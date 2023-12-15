from dataclasses import dataclass


@dataclass
class Product:
    name: str = ''
    page_url: str = ''
    source: str = ''
    image_url: str = ''
    price: float = 0
    product_type: str = ''
