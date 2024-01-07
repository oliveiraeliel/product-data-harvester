/** @type {import('next').NextConfig} */
const nextConfig = {
    env: {
        PRODUCT_API_URL: process.env.PRODUCT_API_URL,
    }
}

module.exports = nextConfig
