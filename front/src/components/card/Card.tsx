import Product from "@interfaces/product.interface";
import styles from "./styles.module.css";
import { useState, useRef, useEffect } from "react";
import { ModalContent, ProductModal } from "..";
import { formatPrice } from "utils/formatPrice";

export interface CardComponentProps {
    title: string;
    imageUrl: string;
    price: number;
}

const CardComponent = (product: Product) => {
    const [isModalOpen, setIsModalOpen] = useState<boolean>(false);
    const modalRef = useRef<HTMLDivElement>(null);

    useEffect(() => {
        const handleClickOutside = (event: MouseEvent) => {
            if (modalRef.current && !modalRef.current.contains(event.target as Node)) {
                setIsModalOpen(false);
            }
        };

        document.addEventListener("mousedown", handleClickOutside);

        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
        };
    }, []);

    return (
        <div className={styles.cardBody} onClick={() => setIsModalOpen(true)}>
            <div className={styles.productIdentityBox}>
                <img src={product.imageUrl} className={styles.productImage} alt="product's picture" />
                <span className={styles.title}>{product.name}</span>
            </div>
            <span className={styles.price}>
                R$ {product.prices.length !== 0 ? formatPrice(product.prices[product.prices.length - 1].price) : "---"}
            </span>

            <ProductModal isOpen={isModalOpen} onClose={() => setIsModalOpen(false)}>
                <div ref={modalRef} style={{ width: '100%', height: '100%' }}>
                    <ModalContent {...product} />
                </div>
            </ProductModal>
        </div>
    );
};

export default CardComponent;
