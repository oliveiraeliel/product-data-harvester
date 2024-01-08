import Product from "@interfaces/product.interface"
import styles from "./styles.module.css"
import { useState } from "react"
import { ModalContent, ProductChart, ProductModal } from ".."
import { formatPrice } from "utils/formatPrice"

export interface CardComponentProps {
    title: string,
    imageUrl: string,
    price: number
}

const CardComponent = (product: Product) => {
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);

    return <div className={styles.cardBody} onClick={() => setIsModalOpen(!isModalOpen)}>
        <div className={styles.productIdentityBox}>
            <img src={product.imageUrl} className={styles.productImage} alt="product's picture" />
            <span className={styles.title}>{product.name}</span>
        </div>
        <span className={styles.price}>
            R$ {product.prices.length != 0 ? formatPrice(product.prices[product.prices.length - 1].price) : "---"}</span>

        <ProductModal isOpen={isModalOpen} onClose={() => { }}>
            <ModalContent {...product} />
        </ProductModal>

    </div>
}

export default CardComponent;