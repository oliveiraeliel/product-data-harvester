import ProductPrice from '@interfaces/productPrice.interface';
import React from 'react';
import dynamic from "next/dynamic";
import styles from "./styles.module.css";
import { LineChart } from '@mui/x-charts';
import { currencyFormater } from '@utils/currencyFormater';
const ApexChart = dynamic(() => import("react-apexcharts"), { ssr: false });

interface ProductChartProps {
    prices: ProductPrice[];
}

const dateFormatter = (date: Date) => {

    return date.toLocaleDateString('pt-BR', {
        month: 'numeric',
        day: 'numeric',
        year: '2-digit'
    })
};

const ProductChart: React.FC<ProductChartProps> = ({ prices }) => {
    return (
        <LineChart
            xAxis={[{
                scaleType: "time",
                data: prices.map((price) => (
                    price.date
                )),
                tickMinStep: 3600 * 1000 * 24,
                valueFormatter: dateFormatter
            }]}
            series={[
                {
                    curve: "stepAfter",
                    data: prices.map((price) => (price.price)),
                    valueFormatter: currencyFormater
                }
            ]}
        />
    )
};


export default ProductChart;