import { useEffect, useState } from 'react';
import Product from '@interfaces/product.interface';
import { CardTableComponent } from '@components/index';

const fetchProducts = async () => {
    const URL = process.env.PRODUCT_API_URL;
    console.log(URL);

    if (!URL) {
        throw new Error('no url provided');
    }
    const res = await fetch(`${URL}api/products`);
    if (!res.ok) {
        throw new Error(':c');
    }
    const rawData = await res.json();
    return rawData.map((item: any) => ({
        id: item.product.id,
        name: item.product.name,
        pageUrl: item.product.pageUrl,
        source: item.product.source,
        imageUrl: item.product.imageUrl,
        productType: item.product.productType,
        rating: item.product.rating,
        reviewCount: item.product.reviewCount,
        prices: item.prices.map((price: any) => ({
            price: price.price,
            date: new Date(price.date)
        })),
    }));
};

const Products = () => {
    const [products, setProducts] = useState<Product[]>([]);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await fetchProducts();
                setProducts(data);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchData();
    }, []);

    return <CardTableComponent products={products} />;
};

const Index = () => {
    return <Products />;
};

export default Index;
