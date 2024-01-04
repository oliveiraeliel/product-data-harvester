import React from "react";
import CardComponent, { CardComponentProps } from "../card/Card"
import styles from "./styles.module.css"


interface CardTableComponentProps {
    cards: CardComponentProps[];
}

const renderCardComponents = (cards: CardComponentProps[]) => {
    return cards.map((card, index) => (
        <div className={styles.card}>
            <CardComponent
                key={index}
                imageUrl={card.imageUrl}
                title={card.title}
                price={card.price}
            />
        </div>

    ));
};

const CardTableComponent: React.FC<CardTableComponentProps> = ({ cards }) => {
    return <div className={styles.tableBody}>
        {renderCardComponents(cards)}
    </div>
}

export default CardTableComponent;