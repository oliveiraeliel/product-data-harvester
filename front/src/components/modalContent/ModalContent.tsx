import { formatPrice } from "utils/formatPrice";
import { ProductChart } from "..";
import styles from "./styles.module.css"
import Product from "@interfaces/product.interface"



const ModalContent: React.FC<Product> = (product: Product) => {
    return (
        <div className={styles.modalContainer}>
            <span>{product.name}</span>
            <div className={styles.modalContent}>
                <div className={styles.modalContentPart}>
                    <ProductChart prices={product.prices} />
                </div>
                <div className={styles.modalContentPart}>
                    <img src={product.imageUrl} alt="product's picture" className={styles.productImage}/>
                    <span> R$ {product.prices.length != 0 ? formatPrice(product.prices[product.prices.length - 1].price) : "---"}</span>
                </div>
            </div >
        </div>);
}

export default ModalContent;