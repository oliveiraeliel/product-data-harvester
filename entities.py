from dataclasses import dataclass


class Data:
    pass


@dataclass
class Product(Data):
    name: str = ''
    price: float = 0
    id: str = ''
    image_url: str = ''
    page_url: str = ''
    product_type: str = ''


@dataclass
class Source:
    url: str = ''
    target_path: str = ''
