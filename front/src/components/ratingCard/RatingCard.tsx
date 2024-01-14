import { Rating } from "@mui/material"
import styles from "./styles.module.css"



const RatingCard: React.FC<{ rating: number, reviewCount: number }> = ({ rating, reviewCount }) => {
    return <span className={styles.rating}>
        <Rating name="half-rating" defaultValue={rating} precision={0.25} readOnly />
        <div>{reviewCount}</div>
    </span>;
}

export default RatingCard;