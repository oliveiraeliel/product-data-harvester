import asyncio
from src.factories import MagazineLuizaProducts
from src.request import PostData


async def main():
    publisher = PostData(MagazineLuizaProducts())

    print([await publisher.post_product(product)
           for product in publisher.products])


if __name__ == '__main__':
    asyncio.run(main())
