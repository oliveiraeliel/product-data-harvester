import ProductPrice from "./productPrice.interface";

export default interface Product {
    id: string;
    name: string;
    pageUrl: string;
    source: string;
    imageUrl: string;
    productType: string;
    prices: ProductPrice[];
}