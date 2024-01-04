import styles from "./styles.module.css"

export interface CardComponentProps {
    title: string,
    imageUrl: string,
    price: number
}

const CardComponent = ({ imageUrl, price, title }: CardComponentProps) => {
    const formatPrice = (price: number) => {
        return price.toLocaleString('pt-BR', { minimumFractionDigits: 2, maximumFractionDigits: 2 })
    }

    return <div className={styles.cardBody}>
        <div className={styles.productIdentityBox}>
            <img src={imageUrl} className={styles.productImage} />
            <span className={styles.title}>{title}</span>
        </div>
        <span className={styles.price}>R$ {formatPrice(price)}</span>
    </div>
}

export default CardComponent;