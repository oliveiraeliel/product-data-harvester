import { useEffect, useState } from 'react';
import Product from '@interfaces/product.interface';
import { CardTableComponent } from '@components/index';
import { useSearchParams } from 'next/navigation';
import Page from '@interfaces/apiResponse.interface';


const instanciateDate = (date: string) => {
    const [ano, mes, dia] = date.split('-').map(Number);
    const d = new Date(ano, mes - 1, dia, 0, 0, 0);
    return d;
}

const bindProduct = (item: any): Product => {
    return {
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
            date: instanciateDate(price.date)
        })),
    }
}

const fetchProducts = async (p: number, size: number) => {
    const URL = process.env.PRODUCT_API_URL;
    console.log(URL);

    if (!URL) {
        throw new Error('no url provided');
    }
    const res = await fetch(`${URL}api/products?page=${p}&size=${size}`);
    if (!res.ok) {
        throw new Error(':c');
    }

    const page: Page<Product> = await res.json();

    for (let i = 0; i < page.content.length; i++) {
        page.content[i] = bindProduct(page.content[i])
    }
    return page
};

const Products: React.FC<{ p: number, size: number }> = ({ p, size }) => {
    const [page, setPage] = useState<Page<Product> | null>(null);

    useEffect(() => {
        const fetchData = async () => {
            try {
                const data = await fetchProducts(p, size);
                setPage(data);
            } catch (error) {
                console.error('Error fetching products:', error);
            }
        };

        fetchData();
    }, []);

    return <CardTableComponent page={page} />;
};

const Index = () => {
    const [page, setPage] = useState<number>(0);
    const searchParams = useSearchParams()

    useEffect(()=>{
        const pParam = searchParams.get("p")
        console.log(pParam);

        if (pParam) {
            setPage(parseInt(pParam))
        }
    }, [])

    return <Products p={page} size={1000} />
};



export default Index;
