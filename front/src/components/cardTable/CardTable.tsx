import React from "react";
import CardComponent, { CardComponentProps } from "../card/Card"
import styles from "./styles.module.css"
import { randomUUID } from "crypto";
import Product from "@interfaces/product.interface";


interface CardTableProps {
    products: Product[];
}

const CardComponents = ({products}: CardTableProps) => {
    return products.map((product: Product) => (
        <div className={styles.card}>
            <CardComponent
                {...product}
                key={product.id}
            />
        </div>
    ));
};

const CardTableComponent: React.FC<CardTableProps> = ({products}: CardTableProps) => {
    return <div className={styles.tableBody}>
        <CardComponents products={products}/>
    </div>
}

export default CardTableComponent;