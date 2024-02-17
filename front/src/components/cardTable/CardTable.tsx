import React from "react";
import CardComponent, { CardComponentProps } from "../card/Card"
import styles from "./styles.module.css"
import { randomUUID } from "crypto";
import Product from "@interfaces/product.interface";
import Page from "@interfaces/apiResponse.interface";


const CardComponents: React.FC<{ products: Product[] }> = ({ products }) => {
    return products.map((product: Product) => (
        <div className={styles.card}
            key={product.id}>
            <CardComponent
                {...product}
            />
        </div>
    ));
};

const CardTableComponent: React.FC<{ page: Page<Product> | null }> = ({ page }) => {
    return <div className={styles.tableBody}>
        <CardComponents products={page ? page.content : []} />
    </div>
}

export default CardTableComponent;