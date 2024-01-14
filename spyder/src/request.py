from src.factories import Factory
from src.entities import Product
import aiohttp
from dotenv import load_dotenv
import os


class PostData:
    def __init__(self, factory: Factory) -> None:
        self.products: list[Product] = factory.get_products()
        load_dotenv()
        self.url = os.getenv("PRODUCTS_API_URL")

    async def post_product(self, product: Product):
        async with aiohttp.ClientSession() as session:
            payload = {
                "name": product.name,
                "source": product.source,
                "imageUrl": product.image_url,
                "pageUrl": product.page_url,
                "price": product.price,
                "productType": product.product_type
            }
            url = self.url + "/api/products"
            async with session.post(url, json=payload) as response:
                return await response.json()
