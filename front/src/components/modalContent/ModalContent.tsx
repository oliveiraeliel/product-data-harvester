import { formatPrice } from "utils/formatPrice";
import { ProductChart, RatingCard } from "..";
import styles from "./styles.module.css"
import Product from "@interfaces/product.interface"
import { Button } from "@mui/material";
import Rating from '@mui/material/Rating'

const ModalContent: React.FC<Product> = (product: Product) => {
    return (
        <div className={styles.modalContainer}>
            <span>{product.name}</span>
            <div className={styles.modalContent}>
                <div className={styles.modalContentPart}>
                    <ProductChart prices={product.prices} />
                </div>
                <div className={styles.modalContentPart}>
                    <img src={product.imageUrl} alt="product's picture" className={styles.productImage} />
                    <RatingCard rating={product.rating} reviewCount={product.reviewCount} />
                    <span className={styles.price}>
                        R$ {product.prices.length != 0 ? formatPrice(product.prices[product.prices.length - 1].price) : "---"}
                    </span>
                    <Button variant="contained" color={"secondary"} href={product.pageUrl}>Visitar</Button>
                </div>
            </div >
        </div>);
}

export default ModalContent;