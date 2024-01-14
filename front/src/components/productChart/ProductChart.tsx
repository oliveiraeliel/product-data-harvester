import ProductPrice from '@interfaces/productPrice.interface';
import React from 'react';
import dynamic from "next/dynamic";
import styles from "./styles.module.css";
const ApexChart = dynamic(() => import("react-apexcharts"), { ssr: false });

interface ProductChartProps {
    prices: ProductPrice[];
}

const ProductChart: React.FC<ProductChartProps> = ({ prices }) => {
    const option = {
        chart: {
            id: 'apexchart-example',
            foreColor: "dark"
        },
        xaxis: {
            show: false,
            type: 'datetime',
            labels: {
                datetimeFormatter: {
                    year: 'yyyy',
                    month: 'MM',
                    day: 'dd',
                },
            }
        },
        subtitle: {
            text: "Preço (R$)",
            offsetY: 0,
            offsetX: 14
        },
        markers: {
            size: 4,
            strokeWidth: 2,
            strokeColor: "#ffd"
        },
        tooltip: {
            enambled: true,
            theme: 'dark'
        },
        toolbar: {
            show: false,
        },
        grid: {
            show: true
        }
    }

    const data = prices.map((price) => ({
        x: price.date, y: price.price
    }))

    const series = [{
        name: 'Preço',
        data
    }];
    return (
        <ApexChart type="line" options={option} series={series} className={styles.customChart} />

    )
};


export default ProductChart;