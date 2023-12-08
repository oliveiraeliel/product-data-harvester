from dataclasses import dataclass


@dataclass
class Product:
    name: str = ''
    price: float = 0
    id: str = ''


@dataclass
class Source:
    url: str = ''
    target_path: str = ''
