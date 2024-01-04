import { CardComponentProps } from "@/components/card/Card"
import "./globals.css"
import { CardComponent, CardTableComponent } from "@/components"

export default function MyApp() {
    const cards: CardComponentProps[] = [
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
        { title: "Title", imageUrl: "https://www.shutterstock.com/image-photo/example-word-written-on-wooden-260nw-1765482248.jpg", price: 12321 },
    ]
    return <>
        <CardTableComponent cards={cards} />
    </>
}