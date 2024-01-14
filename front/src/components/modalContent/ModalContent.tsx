import { currencyFormater } from "@utils/currencyFormater";
import { ProductChart, RatingCard } from "..";
import styles from "./styles.module.css"
import Product from "@interfaces/product.interface"
import { Button } from "@mui/material";

const ModalContent: React.FC<Product> = (product: Product) => {
    return (
        <div className={styles.modalContainer}>
            <span>{product.name}</span>
            <div className={styles.modalContent}>
                <div className={styles.modalContentPart}>
                    <div className={styles.chartContainer}>
                        <ProductChart prices={product.prices} />
                    </div>
                </div>
                <div className={styles.modalContentPart}>
                    <img src={product.imageUrl} alt="product's picture" className={styles.productImage} />
                    <RatingCard rating={product.rating} reviewCount={product.reviewCount} />
                    <span className={styles.price}>
                        {product.prices.length != 0 ? currencyFormater(product.prices[product.prices.length - 1].price) : "---"}
                    </span>
                    <Button variant="contained" color={"secondary"} href={product.pageUrl}>Visitar</Button>
                </div>
            </div >
        </div>);
}

export default ModalContent;